
        package Klasses;

        import Algoritmes.BestFit;
        import Algoritmes.FirstFit;
        import Algoritmes.TSP.*;
        import GUI.Order;
        import GUI.Window;
        import Panels.BoxPanel;
        import Panels.SortingLinePanel;
        import Panels.TurningPanel;
        import Robots.Robot;

        import java.awt.geom.Point2D;
        import java.util.ArrayList;
        import java.util.Timer;
        import java.util.TimerTask;

        import static Database.Database.updateOrder;

public class OrderManager {
    ///<summary>
    /// this script will manage getting the order
    /// and sending the commands to the robot using the selected algorithms
    ///</summary>

    private Window window;
    private Order order;
    private Order oldOrder;
    private Robot robot;

    private int index = 0;
    private ArrayList<Product> products;
    private ArrayList<Point2D>points2D;

    private boolean checkUp() {
        // check if a comport is selected
        String comport = window.getComportOrder();
        if (comport.equalsIgnoreCase("COMPORT") || order == null) {
            return false;
        } else {
            return true;
        }
    }

    public OrderManager(Window window) {
        // start by getting the currently selected order, if it returns NULL wait a second before checking again.
        // then get the optimal path to get the items out of the rack.

        // go over the path steps one by one to push all the items out.
        // ones an item is pushed out put it in the packing line
        // with every step you give the robot wait for it to response with "TRUE" to contineu to the next item
        // if it returns "FALSE" try the command again, if it continues to return "FALSE" show an error message.

        this.window = window;
        this.robot = this.window.orderRobot;
        checkOrder();

        Timer myTimer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                order = window.getSelectedOrder();
                if (!(points2D == null)) {
                    if (index == points2D.size() && !(order.equals(oldOrder)) && !(index == 0)) {
                        newPath(points2D);
                        //checkOrder();
                    }
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask, 0l, 1000);

    }
    private void setOrder(Order order) {
        this.order = order;
    }

    private void checkOrder() {
        window.setOrderIndex();
        Timer myTimer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                order = window.getSelectedOrder();
                oldOrder = order;
                if (order != null) {
                    myTimer.cancel();
                    updateOrderLine();
                    setOrder(order);
                    window.currentGrid.setPoints(getPath());
                    doPath(getPath());
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask, 0l, 1000);
    }

    // get the path data and send it to the algorithm script
    private ArrayList<Point2D> getPath() {
        products = new ArrayList<>();
        ArrayList<Point2D> cities = new ArrayList<>();
        for (Object[] item : order.getItems()) {
            float x = ((int) item[3] % 5) + 1;
            float y = (float) Math.ceil((int) item[3] / 5) + 1;
            cities.add(new Point2D.Float(x, y));

            Product product = new Product((String) item[1], (int) item[3]);
            product.setWeight((int) item[4]);
            products.add(product);
        }
        
        TSPAlgorithm tspAlgorithm = window.tspAlgorithm;
        switch (window.getAlgoritmOrder()) {
            case "Nearest Insertion":
                tspAlgorithm = new NearestInsertion(cities);
                break;
            case "2-Opt":
                tspAlgorithm = new TwoOpt(cities);
                break;
            case "Nearest Neighbour":
                tspAlgorithm = new NearestNeighbour(cities);
                break;
            case "Nearest Insertion + 2-Opt":
                tspAlgorithm = new OwnChoice(cities);
                break;
            default:
                System.out.println("No algorithm selected");
                break;
        }
        tspAlgorithm.setPoints(cities);
        for (Point2D point : tspAlgorithm.getPoints()) {
            System.out.println("X = " + point.getX() + ", Y = " + point.getY());
        }
        cities.add(0, new Point2D.Double(1.0, 1.0));
        tspAlgorithm.setPoints(cities);
        tspAlgorithm.alternate();
        tspAlgorithm.addPoint();
        return tspAlgorithm.getPoints();

        // do a check if the order is posible with the current items in the rack

        // ones you have the path display this on the window with a set function;
    }

    private void doPath(ArrayList<Point2D> points) {
        points2D = points;
        index++;
        String information = (int) points.get(index).getX() + "," + (int) points.get(index).getY();
        window.currentGrid.setPoints(points);
        robot.sendInformation(information);
        System.out.println(information);
        Timer myTimer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                if (!(window.getStop())) {
                    int x = (int) points.get(index).getX();
                    int y = (int) points.get(index).getY();
                    String information = x + "," + y;
                    //robot.sendInformation(information);
                    String response = robot.getText(4);
                    response = response.trim();
//                response = robot.getText().substring(response.length()-5);
                    System.out.println(response);
                    if (response.equalsIgnoreCase("ue") || response.equalsIgnoreCase("rue") || response.equalsIgnoreCase("true")) {
                        SortingLinePanel panel = window.getSortingLinePanel();
                        try {
                            panel.addItem(products.get(index));
                        } catch (Exception E) {}
                        index++;
                        if (index < points.size()) {
                            information = (int) points.get(index).getX() + "," + (int) points.get(index).getY();
                            robot.sendInformation(information);
                            System.out.println(information);
                            window.currentGrid.nextPoint();
                            System.out.println("true gekregen");
                        }
                    } else if (response.equalsIgnoreCase("se") || response.equalsIgnoreCase("lse") || response.equalsIgnoreCase("alse")) {
                        robot.sendInformation(information);
                        System.out.println("false gekregen");
                    }
                    //if (index == points.size() - 1) robot.sendInformation(information);
                    if (index >= points.size()) {
//                    window.sortingLinePanel.remove(0);
                        myTimer.cancel();
                        newPath(points);
                        updateDatabase(false);
                    }
                }
            }
        };
        myTimer.scheduleAtFixedRate(myTask, 0l, 1000);
    }

    private void updateDatabase(boolean test) {
        // update de order in database to set it as completed
        if (test)
            return;

        updateOrder(Integer.toString(order.getOrderID())); // careful with this statement
    }

    private void updateOrderLine() {
        // get the best placement based on the BPP-algorithm
        BPPAlgorithm algorithm = window.bppAlgorithm;
        switch (window.getAlgoritmPacking()) {
            case "Best-Fit" -> algorithm = new BestFit();
            case "First-Fit" -> algorithm = new FirstFit();
            default -> System.out.println("No algorithm selected");
        }
        ArrayList<ArrayList<Product>> products = algorithm.getBestPlacement(order);


        TurningPanel panel = window.getTurningPanel();
        BoxPanel[] boxpanels = panel.getBoxPanels();
        int index = 0;
        for (BoxPanel box : boxpanels) {
            if (index >= products.size())
                break;
            for (Product item : products.get(index)) {
                box.addProduct(item);
            }
            index++;
        }

/*
        Timer myTimer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                TurningPanel tPanel = window.getTurningPanel();
                SortingLinePanel lPanel = window.getSortingLinePanel();
                Product first = lPanel.getFirst();
                if (first == null)
                    return;

                Box box = tPanel.getFrontBox();

                boolean contains = false;
                for (Product product : box.getProducts()){
                    if (product.getProductId() == first.getProductId()){
                        contains = true;
                        break;
                    }
                }

                if (contains){
                    lPanel.deleteFirst();
                    box.addProduct(first);
                }else{
                    tPanel.turnTimes(1);
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask, 0l, 1000);
*/
        //Product product = linePanel.getFirst();
        //System.out.println();


    }
    private void newPath(ArrayList<Point2D> points){
        window.currentGrid.deletePoints();
        updateDatabase(true);
        order = window.getSelectedOrder();
        if (index == points.size() && !(order.equals(oldOrder))) {
            index = 0;
            oldOrder = order;
            checkOrder();
        }
    }
}
