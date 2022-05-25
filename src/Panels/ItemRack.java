package Panels;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import Database.Database;
import Klasses.Product;

public class ItemRack extends JPanel {
    private final int quantity = 25;
    private ProductPanel[] productPanels = new ProductPanel[quantity];
    private int quantityFilled = 0;
    private int width = 400, height = 400;


    public ItemRack(){

        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(5,5));
        setBackground(Color.WHITE);

        ArrayList<ArrayList<String>> itemRack = new ArrayList<>();
        try {
            itemRack = Database.executeQuery("SELECT StellingID,StockItemName FROM nerdygadgets.stockitems\n" +
                    "WHERE StellingID IS NOT NULL;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < quantity; i++){
            productPanels[i] = new ProductPanel(new Product());
            add(productPanels[i]);
        }
        for (ArrayList<String> strings : itemRack) {
            productPanels[Integer.parseInt(strings.get(0))].getProduct().setName(strings.get(1));
            productPanels[Integer.parseInt(strings.get(0))].repaint();
        }

        productPanels[1] = new ProductPanel(new Product("USB", 1));

        for (int i = 0; i < quantity; i++){
            productPanels[i].repaint();
        }

        setVisible(true);
    }

    public Product getProduct(int id){
        if(id >= 0 && id<=24){
            return productPanels[id].getProduct();
        }
        return null;
    }
    public void setEmpty(int i){
        productPanels[i].setEmpty();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0 ; i < quantity; i++){
            productPanels[i].repaint();
        }
    }
    //    public ItemRack(Window window){
//     //   Database.Database waardes ophalen
//        // Kleuren
//        // Disabelen
//        ArrayList<ArrayList<String>> Stellingen = new ArrayList<>();
//        try {
//            Stellingen = Database.executeQuery("SELECT StellingID,StockItemName FROM nerdygadgets.stockitems\n" +
//                    "WHERE StellingID IS NOT NULL;");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Stellingen);
//        for (ArrayList<String> stelling: Stellingen) {
//           // for (String stellingID:stelling) {
//                window.enableButtons(Integer.parseInt(stelling.get(0)),stelling.get(1));
//            //}
//        }
//    }
}
