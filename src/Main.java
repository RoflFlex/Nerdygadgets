import Database.Database;
import GUI.Window;
import Klasses.OrderManager;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args){
        // Call all starting scripts here
        Window window = new Window("HMI-applicatie");

        window.newOrder(1);
        window.addOrder("20", "10", 20, 10);
        window.addOrder("13", "2", 13, 2);
        window.addOrder("17", "5", 17, 5);
        window.finishOrder();
        window.newOrder(2);
        window.addOrder("23", "3", 23, 3);
        window.addOrder("2", "20", 2, 20);
        window.addOrder("8", "1", 8, 1);
        window.addOrder("3", "54", 3, 54);
        window.addOrder("18", "9", 18, 9);
        window.addOrder("24", "100", 24, 100);
        window.finishOrder();

        Database.getOrders(window);

        // timer for checking the database
        Timer myTimer = new Timer ();
        TimerTask myTask = new TimerTask () {
            @Override
            public void run () {
                // Checking if the database has new orders
                Database.getOrders(window);
            }
        };

        myTimer.scheduleAtFixedRate(myTask , 0l, 10000);

        new OrderManager(window); // change this NULL to an instantiated of the class Robot
    }
}
