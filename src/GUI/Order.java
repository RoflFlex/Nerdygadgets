package GUI;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private ArrayList<Object[]> order;

    public Order(int id) {
        order = new ArrayList<Object[]>();
        orderID = id;
    }

    public void addItem(String itemName, String itemID, int rackPlacement, int weigth){
        order.add(new Object[]{orderID, itemName, itemID, rackPlacement, weigth});
    }

    public int getOrderID(){
        return orderID;
    }

    public ArrayList<Object[]> getItems(){
        return order;
    }
}