package Klasses;

import java.util.ArrayList;

public class Box {
    private ArrayList<Product> products = new ArrayList<>();
    private int id;
    private static int quantity;
    public static double maxWeight = 10;
    private double weight = 0;



    public double getWeight() {
        return weight;
    }

    public Box(){
        this(new ArrayList<>());
    }

    public Box(ArrayList<Product> products){
        setProducts(products);
        id = quantity;
        quantity ++;
    }

    public void setProducts(ArrayList<Product> products) {
        for(int i = 0;  i < products.size(); i++){
            weight += products.get(i).getWeight();
        }
        this.products = products;
    }
    public boolean addProduct(Product product){
        if(weight + product.getWeight() > maxWeight){
            return false;
        }else{
            weight+= product.getWeight();
            products.add(product);
            return true;
        }
    }

    @Override
    public String toString() {
        return "Box " + id;
    }
}