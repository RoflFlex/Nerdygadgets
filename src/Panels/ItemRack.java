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
    private Product[] products = new Product[quantity];
    private int quantityFilled = 0;
    private final int width = 412;
    private final int height = 412;
    private final int widthProduct = 80;
    private final int heightProduct =80;
    private ArrayList<Point2D> points = new ArrayList<>();
    private ArrayList<Point2D> robotIs = new ArrayList<>();
//    private RoutePanel routePanel = new RoutePanel();


    public ItemRack(){

        setPreferredSize(new Dimension(width,height));
        setLayout(new GridLayout(5,5,2,2));
        setBackground(Color.WHITE);
        update();
//        ArrayList<Point2D> cities = new ArrayList<>();
//        Random random = new Random();
//        cities.add(new Point2D.Double(1.0,1.0));
//        cities.add(new Point2D.Double(5.0,5.0));
//        for (int i = 0; i < 10 ; i++){
//            Point2D point = new Point2D.Float(random.nextInt(1,6), random.nextInt(1,6));
//            if(!cities.contains(point)){
//                cities.add(point);
//            }//alter file name here.
//        }
//        setPoints(cities);

//        productPanels[1] = new ProductPanel(new Product("USB", 1));


        setVisible(true);
    }

    public void setPoints(ArrayList<Point2D> points){
        this.points = points;
        robotCoordinate = points.get(0);
        robotIs.add(points.get(0));
        lineId = 0;
        for(int i = 1; i < points.size()-1; i ++){
            products[(int)((points.get(i).getY()-1)*5 + points.get(i).getX()-1)].setWillBePacked(true);
//            productPanels[(int)((points.get(i).getX()-1)*5 + points.get(i).getY()-1)].setWilLBePacked();
        }
//        robotIsMoving = true;
        repaint();
        nextPoint();
//        routePanel.setPoints(points);
    }

    public void update(){
        ArrayList<ArrayList<String>> itemRack = new ArrayList<>();
        try {
            itemRack = Database.executeQuery("SELECT StellingID,StockItemName,StockItemID,TypicalWeightPerUnit FROM nerdygadgets.stockitems\n" +
                    "WHERE StellingID IS NOT NULL;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < quantity; i++){
            products[i] = new Product();
        }
        for (ArrayList<String> strings : itemRack) {
            products[Integer.parseInt(strings.get(0))].setName(strings.get(1));
            products[Integer.parseInt(strings.get(0))].setProductId(Integer.parseInt(strings.get(2)));
            products[Integer.parseInt(strings.get(0))].setWeight(Double.parseDouble(strings.get(3)));

            repaint();

        }
    }

    public void deletePoints(){
        robotIsMoving = false;
        this.points = new ArrayList<>();
        this.robotIs = new ArrayList<>();
        lineId = 0;
//        last = false;
//        routePanel.deletePoints();
        repaint();
    }

    public void nextPoint(){


//        routePanel.nextPoint();
//        if(points != null && lineId == points.size()){
//            last = true;
//            lineId++;
//        }else
        if(points != null && points.size() <= lineId){
            deletePoints();
        }else
        if(points != null ){

//            robotIsMoving = true;
//
//            robotCoordinate = points.get(lineId);
//            robotIs.add(points.get(lineId));
//            if(lineId != 0 && lineId != points.size()){
//                int id = (int)(points.get(lineId).getX()-1 + (points.get(lineId).getY()-1)*5);
////                products[id].setEmpty();
//            }
            if(lineId >= 1 && lineId < points.size()-1){
                int id = (int)(points.get(lineId).getX()-1 + (points.get(lineId).getY()-1)*5);
                System.out.println(id);
                products[id].setEmpty();

//                products[lineId]
                robotCoordinate = points.get(lineId);
                robotIs.add(points.get(lineId));
            }
            robotIsMoving = true;

            lineId ++;
        }

        repaint();
    }



//    public ArrayList<Product> getChosenProducten(){
//        ArrayList<Product> products = new ArrayList<>();
//
//        for (int i = 0; i < 25; i ++){
//            if(productPanels[i].isChosen()){
//                products.add(productPanels[i].getProduct());
//            }
//        }
//
//        return products;
//    }

    public Product getProduct(int id){
//        if(id >= 0 && id<=24){
//            return productPanels[id].getProduct();
//        }
//        return null;
        if(id >= 0 && id <= 24){
            return  products[id];
        }
        return null;
    }


    public void setEmpty(int i){
        products[i].setEmpty();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,width,height);
        int y = 2 ;
        for(int i = 0 ; i < 5; i++){
            int x = 0;
            for(int j = 0; j < 5;j++){
                Product product = products[i*5 +j];
                if(product.isWillBePacked()){
                    g.setColor(Color.green);
                    g.drawRect(x, y, widthProduct , heightProduct );
                }else{
                    g.setColor(Color.black);
                    g.drawRect(x,y,widthProduct,heightProduct);
                }
                g.setColor(Color.black);
                if(product.getName().length() > 10){
                    drawStringMiddleOfPanel(product.getName().substring(0,10)+"...",g,x,y);
                }else{
                    drawStringMiddleOfPanel(product.getName(),g,x,y);
                }
                x+=heightProduct+2;
            }
            y += widthProduct+2;
//            productPanels[i].repaint();
        }
        if(points.size()>0){

        for(int i = 0; i < points.size()-1; i++) {
            g.setColor(Color.GREEN);
            int startX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(i).getX() - 1));
            int startY = (int) (heightProduct / 2 + (heightProduct + 2) * (points.get(i).getY() - 1)) + 2;
            int endX, endY;
            endX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(i+1).getX() - 1));
            endY = (int) (widthProduct / 2 + (heightProduct + 2) * (points.get(i+1).getY() - 1)) + 2;
//            if (i == points.size()-1) {
//                startX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(i).getX() - 1));
//                startY = (int) (heightProduct / 2 + (heightProduct + 2) * (points.get(i).getY() - 1)) + 2;
//                endX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(0).getX() - 1));
//                startY = (int) (heightProduct / 2 + (heightProduct + 2) * (points.get(0).getY() - 1)) + 2;
//            }
            g.drawLine(startX, startY, endX, endY);
        }
        }
        if(robotIsMoving){
            g.setColor(Color.RED);
            for(int i = 1; i < robotIs.size();i++){
                int startX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(i-1).getX() - 1));
                int startY = (int) (heightProduct / 2 + (heightProduct + 2) * (points.get(i-1).getY() - 1)) + 2;
                int endX, endY;
                endX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(i).getX() - 1));
                endY = (int) (widthProduct / 2 + (heightProduct + 2) * (points.get(i).getY() - 1)) + 2;
                g.drawLine(startX, startY, endX, endY);
            }
//            int startX = (int) (widthProduct/2+ (widthProduct+2)*(robotCoordinate.getX()-1));
//            int startY = (int)(heightProduct/2+ (heightProduct+2)*(robotCoordinate.getY()-1)) + 2;
//            int endX, endY;
//            if(lineId >= points.size()){
//                endX = widthProduct/2;
//                endY = (widthProduct/2) +2;
//            }else {
//                endX = (int) (widthProduct / 2 + (widthProduct + 2) * (points.get(lineId).getX() - 1));
//                endY = (int) (widthProduct / 2 + (heightProduct + 2) * (points.get(lineId).getY() - 1)) + 2;
//            }
//            g.drawLine(startX, startY, endX, endY);
        }
        if(robotIs.size()>0){
            g.setColor(Color.RED);
            int yRobot = (int)((robotIs.get(robotIs.size()-1).getY()-1)*(heightProduct+2)+5);
            int xRobot = (int)((robotIs.get(robotIs.size()-1).getX()-1)*(heightProduct+2)+3);
            g.drawRect(xRobot,yRobot,widthProduct-6,heightProduct-6);

        }else{
            g.setColor(Color.RED);
            g.drawRect(3,5,widthProduct-6,heightProduct-6);
        }
    }
    private void drawStringMiddleOfPanel(String string, Graphics g,int x, int y) {
        String message2 = string;
        int stringWidth = 0;
        int stringAccent = 0;
        int xCoordinate = 0;
        int yCoordinate = 0;
        // get the FontMetrics for the current font
        FontMetrics fm = g.getFontMetrics();


        /* display new message */
        /* Centering the text */
        // find the center location to display
        stringWidth = fm.stringWidth(message2);
        stringAccent = fm.getAscent();
        // get the position of the leftmost character in the baseline
        xCoordinate = widthProduct/ 2 - stringWidth / 2 + x;
        yCoordinate = heightProduct / 2 + stringAccent / 2 + y;

        // draw String
        g.drawString(message2, xCoordinate+1, yCoordinate);
//        currentMessage = message2;  // alternate message
    }

//    public ProductPanel[] getProductPanels() {
//        return productPanels;
//    }

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
