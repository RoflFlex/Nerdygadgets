package Klasses;

public class Product {
    private String name;
    private int productId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
