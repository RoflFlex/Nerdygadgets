import GUI.Order;
import GUI.Window;

import java.util.Timer;
import java.util.TimerTask;

public class OrderManager {
    ///<summary>
    /// this script will manage getting the order
    /// and sending the commands to the robot using the selected algorithms
    ///</summary>

    private Window window;
    private Order order;

    public OrderManager(Window window){
        // start by getting the currently selected order, if it returns NULL wait a second before checking again.
        // then get the optimal path to get the items out of the rack.

        // go over the path steps one by one to push all the items out.
        // ones an item is pushed out put it in the packing line
        // with every step you give the robot wait for it to response with "TRUE" to contineu to the next item
        // if it returns "FALSE" try the command again, if it continues to return "FALSE" show an error message.

        this.window = window;
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
                    SetOrder(order);
                    GetPath();
                }
            }
        };

        myTimer.scheduleAtFixedRate(myTask , 0l, 1000);
    }

    private void GetPath(){
        //algorithm = new TwoOpt();
    }
}
