package Klasses;

public class Product {
    private String name;
    private int itemRackID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getItemRackID() {
        return itemRackID;
    }

    public void setItemRackID(int itemRackID) {
        this.itemRackID = itemRackID;
    }

    public Product(){
        this("-----");
    }
    public Product(String name){
        this.name = name;
    }
    public Product(String name, int itemRackID){
        this.name = name;
        this.itemRackID = itemRackID;
    }
}
