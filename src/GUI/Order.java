package GUI;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private ArrayList<Object[]> order;

    public Order(int id) {
        order = new ArrayList<Object[]>();
        orderID = id;
    }

    public void AddItem(String itemName, String itemID, int rackPlacement){
        order.add(new Object[]{orderID, itemName, itemID, rackPlacement});
    }

    public int getOrderID(){
        return orderID;
    }

    public ArrayList<Object[]> GetItems(){
        return order;
    }
}