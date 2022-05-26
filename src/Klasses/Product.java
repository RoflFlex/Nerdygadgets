package Klasses;

public class Product {
    private String name;
    private int productId;
    private boolean willBePacked;

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
    public Product(String name, int itemRackID){
        this.name = name;
        this.productId = itemRackID;
    }

    public void setEmpty() {
        setProductId(0);
        name = "-----";
        willBePacked = false;
    }
}
