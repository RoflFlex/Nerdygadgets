package Klasses;

public class Product {
    private String name;
    private int productId;
    private boolean willBePacked;
    private double weight ;

    public boolean isWillBePacked() {
        return willBePacked;
    }

    public void setWillBePacked(boolean willBePacked) {
        this.willBePacked = willBePacked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null){
            this.name = "-----";
        }else{
            this.name = name;
        }
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int itemRackID) {
        this.productId = itemRackID;
    }

    public Product(){
        this("-----");
    }
    public Product(String name){
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public Product(String name, int itemRackID, double weight){
        this.name = name;
        this.productId = itemRackID;
        this.weight = weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Product(String name, int itemRackID){
        this(name, itemRackID, 0);
    }

    public void setEmpty() {
        setProductId(0);
        name = "-----";
        willBePacked = false;
        productId = 0;
    }
}
