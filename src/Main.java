import GUI.Window;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args){
        // Call all starting scripts here
        Window window = new Window("HMI-applicatie");
        for (int i = 0; i < 25; i++) {
            window.disabledButtons(i);
        }
        Database.getOrders(window);
        new ItemRack(window);

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

        new OrderManager(window);
    }
}
