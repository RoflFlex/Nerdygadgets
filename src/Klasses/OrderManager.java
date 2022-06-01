package Klasses;

import Algoritmes.TSP.*;
import GUI.Order;
import GUI.Window;
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
    private Robot robot;

    private int index = 0;

    private boolean checkUp(){
        // check if a comport is selected
        String comport = window.getComportOrder();
        if (comport.equalsIgnoreCase("COMPORT") || order == null){
            return false;
        }else{
            return true;
        }
    }

    public OrderManager(Window window){
        // start by getting the currently selected order, if it returns NULL wait a second before checking again.
        // then get the optimal path to get the items out of the rack.

        // go over the path steps one by one to push all the items out.
        // ones an item is pushed out put it in the packing line
        // with every step you give the robot wait for it to response with "TRUE" to contineu to the next item
        // if it returns "FALSE" try the command again, if it continues to return "FALSE" show an error message.

        this.window = window;
        this.robot = this.window.orderRobot;
        checkOrder();
    }

    private void setOrder(Order order){
        this.order = order;
    }

    private void checkOrder(){
        Timer myTimer = new Timer ();
        TimerTask myTask = new TimerTask () {
            @Override
            public void run () {
                order = window.getSelectedOrder();
                if (checkUp()){
                    myTimer.cancel();
                    setOrder(order);
                    window.currentGrid.setPoints(getPath());
                    doPath(getPath());
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask , 0l, 1000);
    }

    // get the path data and send it to the algorithm script
    private ArrayList<Point2D> getPath(){
        ArrayList<Point2D> cities = new ArrayList<Point2D>();
        for (Object[] item : order.getItems()){
            float x = ((int)item[3] % 5) + 1;
            float y = (float) Math.ceil((int)item[3] / 5) +  1;
            cities.add(new Point2D.Float(x, y));
        }
        cities.add(0,new Point2D.Double(1.0,1.0));
        TSPAlgorithm tspAlgorithm = window.tspAlgorithm;
        switch(window.getAlgoritmOrder()){
            case"Nearest Insertion":
                tspAlgorithm = new NearestInsertion(cities);
                break;
            case"2-Opt":
                tspAlgorithm = new TwoOpt(cities);
                break;
            case"Nearest Neighbour":
                tspAlgorithm = new NearestNeighbour(cities);
                break;
            case"Nearest Insertion + 2-Opt":
                tspAlgorithm = new OwnChoice(cities);
                break;
            default:
                System.out.println("No algorithm selected");
                break;
        }
        tspAlgorithm.setPoints(cities);
        for(Point2D point : tspAlgorithm.getPoints()){
            System.out.println("X = " + point.getX() + ", Y = " + point.getY());
        }

        tspAlgorithm.setPoints(cities);
        tspAlgorithm.alternate();
        tspAlgorithm.addPoint();
        return tspAlgorithm.getPoints();

        // do a check if the order is posible with the current items in the rack

        // ones you have the path display this on the window with a set function;
    }

    private void doPath(ArrayList<Point2D> points){
        String information = (int)points.get(index).getX() + "," + (int)points.get(index).getY();
        window.currentGrid.setPoints(points);
        index++;
        robot.sendInformation(information);
        Timer myTimer = new Timer ();
        TimerTask myTask = new TimerTask () {
            @Override
            public void run () {
                int x = (int)points.get(index).getX();
                int y = (int)points.get(index).getY();
                String information = x + "," + y;
                System.out.println(information);
                //robot.sendInformation(information);
                String response = robot.getText(4);
                response = response.trim();
//                response = robot.getText().substring(response.length()-5);
                System.out.println(response);
                if (response.equalsIgnoreCase("ue")) {
                    information = (int)points.get(index).getX() + "," + (int)points.get(index).getY();
                    robot.sendInformation(information);
                    window.currentGrid.nextPoint();
                    index++;
                    // send this ONE order to the window for the packing aplication

                }
                else if (response.equalsIgnoreCase(("se"))) robot.sendInformation(information);
                //if (index == points.size() - 1) robot.sendInformation(information);
                if (index >= points.size()) {
//                    window.sortingLinePanel.remove(0);
                    myTimer.cancel();
                    updateDatabase();
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask , 0l, 1000);
    }

    private void updateDatabase(){
        // update de order in database to set it as completed
        //updateOrder(Integer.toString(order.getOrderID())); // careful with this statement
    }
}
