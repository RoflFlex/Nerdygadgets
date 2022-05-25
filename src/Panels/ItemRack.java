package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import Database.Database;
import Klasses.Product;

public class ItemRack extends JPanel {
    private final int quantity = 25;
    private int lineId = 0;
    private Point2D robotCoordinate;
    private boolean robotIsMoving = false;
    private ProductPanel[] productPanels = new ProductPanel[quantity];
    private int quantityFilled = 0;
    private int width = 415, height = 415;
    private ArrayList<Point2D> points = new ArrayList<>();
//    private RoutePanel routePanel = new RoutePanel();


    public ItemRack(){

        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(5,5,2,2));
        setBackground(Color.WHITE);

        ArrayList<ArrayList<String>> itemRack = new ArrayList<>();
        try {
            itemRack = Database.executeQuery("SELECT StellingID,StockItemName,StockItemID FROM nerdygadgets.stockitems\n" +
                    "WHERE StellingID IS NOT NULL;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < quantity; i++){
            productPanels[i] = new ProductPanel(new Product());
            add(productPanels[i]);

        }
//        add(routePanel, 1);
        for (ArrayList<String> strings : itemRack) {
            productPanels[Integer.parseInt(strings.get(0))].getProduct().setName(strings.get(1));
            productPanels[Integer.parseInt(strings.get(0))].getProduct().setProductId(Integer.parseInt(strings.get(2)));
            productPanels[Integer.parseInt(strings.get(0))].repaint();
//            System.out.println(productPanels[Integer.parseInt(strings.get(0))].getProduct().getProductId());
        }
        ArrayList<Point2D> cities = new ArrayList<>();
        Random random = new Random();
        cities.add(new Point2D.Double(1.0,1.0));
        cities.add(new Point2D.Double(5.0,5.0));
        for (int i = 0; i < 10 ; i++){
            Point2D point = new Point2D.Float(random.nextInt(1,6), random.nextInt(1,6));
            if(!cities.contains(point)){
                cities.add(point);
            }//alter file name here.
        }
        setPoints(cities);

//        productPanels[1] = new ProductPanel(new Product("USB", 1));


        setVisible(true);
    }

    public void setPoints(ArrayList<Point2D> points){
        this.points = points;
        robotCoordinate = points.get(0);
        nextPoint();
//        routePanel.setPoints(points);
    }

    private void deletePoints(){
        robotIsMoving = false;
        setPoints(null);
        lineId = 0;
//        routePanel.deletePoints();
    }

    public void nextPoint(){
//        routePanel.nextPoint();
        if(points != null ){
            robotIsMoving = true;
            lineId ++;
            robotCoordinate = points.get(lineId - 1);
        }else if(points != null && points.size() < lineId){
            deletePoints();
        }
        repaint();
    }



    public ArrayList<Product> getChosenProducten(){
        ArrayList<Product> products = new ArrayList<>();

        for (int i = 0; i < 25; i ++){
            if(productPanels[i].isChosen()){
                products.add(productPanels[i].getProduct());
            }
        }

        return products;
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
        g.clearRect(0,0,width,height);
        for(int i = 0 ; i < quantity; i++){
            productPanels[i].repaint();
        }
//        if(robotIsMoving){
//            g.setColor(Color.RED);
//            g.drawLine((int) (productPanels[0].getWidth()/2+ productPanels[0].getWidth()*(robotCoordinate.getX()-1)),(int)(productPanels[0].getHeight()/2+ productPanels[0].getHeight()*(robotCoordinate.getY()-1)),
//                    (int)(productPanels[0].getWidth()/2+ productPanels[0].getWidth()*(points.get(lineId).getX()-1)), (int)(productPanels[0].getWidth()/2+ productPanels[0].getHeight()*(points.get(lineId).getY()-1)));
//        }
    }

    public ProductPanel[] getProductPanels() {
        return productPanels;
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
