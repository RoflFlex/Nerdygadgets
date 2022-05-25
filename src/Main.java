import Database.Database;
import GUI.Window;
import Klasses.OrderManager;
import Panels.ItemRack;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args){
        // Call all starting scripts here
        Window window = new Window("HMI-applicatie");

        window.NewOrder(1);
        window.AddOrder("20", "who cares", 20);
        window.AddOrder("13", "do you?", 13);
        window.AddOrder("17", "I don't", 17);
        window.FinishOrder();
        window.NewOrder(2);
        window.AddOrder("20", "who cares", 20);
        window.AddOrder("13", "do you?", 13);
        window.AddOrder("17", "I don't", 17);
        window.AddOrder("20", "who cares", 20);
        window.AddOrder("13", "do you?", 13);
        window.AddOrder("17", "I don't", 17);
        window.FinishOrder();

        for (int i = 0; i < 25; i++) {
//            window.disabledButtons(i);
        }
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

        new OrderManager(window, null); // change this NULL to an instantiated of the class Robot
    }
}
