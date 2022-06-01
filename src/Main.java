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
        window.addOrder("20", "20", 20);
        window.addOrder("13", "13", 13);
        window.addOrder("17", "17", 17);
        window.finishOrder();
        window.newOrder(2);
        window.addOrder("23", "23", 23);
        window.addOrder("2", "2", 2);
        window.addOrder("8", "8", 8);
        window.addOrder("3", "3", 3);
        window.addOrder("18", "18", 18);
        window.addOrder("24", "24", 24);
        window.finishOrder();

//        for (int i = 0; i < 25; i++) {
//            window.disabledButtons(i);
//        }
        Database.getOrders(window);
//        new ItemRack(window);

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
