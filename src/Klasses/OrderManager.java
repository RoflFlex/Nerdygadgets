package Klasses;

import Algoritmes.TSP.*;
import GUI.Order;
import GUI.Window;
import Robots.Robot;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OrderManager {
    ///<summary>
    /// this script will manage getting the order
    /// and sending the commands to the robot using the selected algorithms
    ///</summary>

    private Window window;
    private Order order;
    private Robot robot;

    private int index = 0;

    public OrderManager(Window window, Robot robot){
        // start by getting the currently selected order, if it returns NULL wait a second before checking again.
        // then get the optimal path to get the items out of the rack.

        // go over the path steps one by one to push all the items out.
        // ones an item is pushed out put it in the packing line
        // with every step you give the robot wait for it to response with "TRUE" to contineu to the next item
        // if it returns "FALSE" try the command again, if it continues to return "FALSE" show an error message.

        this.window = window;
        this.robot = robot;
        CheckOrder();
    }

    private void SetOrder(Order order){
        this.order = order;
    }

    private void CheckOrder(){
        Timer myTimer = new Timer ();
        TimerTask myTask = new TimerTask () {
            @Override
            public void run () {
                Order order = window.GetSelectedOrder();
                if (order != null){
                    myTimer.cancel();
                    SetOrder(order);
                    GetPath();
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask , 0l, 1000);
    }

    // get the path data and send it to the algorithm script
    private void GetPath(){
        ArrayList<Point2D> cities = new ArrayList<Point2D>();
        for (Object[] item : order.GetItems()){
            float x = ((int)item[3] % 5) + 1;
            float y = (float) Math.ceil((int)item[3] / 5) +  1;
            cities.add(new Point2D.Float(x, y));
        }
        TSPAlgorithm tspAlgorithm = null;
        switch(window.GetAlgoritmOrder()){
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
                return;
        }

        for(Point2D point : tspAlgorithm.getPoints()){
            System.out.println("X = " + point.getX() + ", Y = " + point.getY());
        }

        // do a check if the order is posible with the current items in the rack

        // ones you have the path display this on the window with a set function;

        //DoPath(tspAlgorithm.getPoints());
    }

    private void DoPath(ArrayList<Point2D> points){
        String information = (int)points.get(index).getX() + "," + (int)points.get(index).getY();
        robot.sendInformation(information);
        Timer myTimer = new Timer ();
        TimerTask myTask = new TimerTask () {
            @Override
            public void run () {
                String information = (int)points.get(index).getX() + "," + (int)points.get(index).getY();
                System.out.println(information);

                String response = robot.getText();
                if (response.equalsIgnoreCase("TRUE")) {
                    index++;
                    information = (int)points.get(index).getX() + "," + (int)points.get(index).getY();
                    robot.sendInformation(information);
                    // send this ONE order to the window for the packing aplication
                }
                else if (response.equalsIgnoreCase(("FALSE")))
                    robot.sendInformation(information);

                if (index >= points.size()) {
                    myTimer.cancel();
                    UpdateDatabase();
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask , 0l, 1000);
    }

    private void UpdateDatabase(){
        // update de order in database to set it as completed
    }
}
