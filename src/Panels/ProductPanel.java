package Panels;

import Klasses.Product;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {
    protected Product product;
    private int width = 80;
    private int height =80;
    private boolean isChosen = false;
    private boolean willBePacked = false;

    public boolean isChosen() {
        return isChosen;
    }

    public boolean isWillBePacked() {
        return willBePacked;
    }

    public ProductPanel(Product product){
        setPreferredSize(new Dimension(width,height));
        setBackground(Color.white);
        this.product = product;
        setVisible(true);
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.clearRect(0,0,width,height);
        if(isChosen){
            g.setColor(Color.blue);
            g.drawRect(0, 0, width - 1, height - 1);

        }else if(willBePacked){
            g.setColor(Color.green);
            g.drawRect(0, 0, width - 1, height - 1);
        }else{
            g.setColor(Color.black);
            g.drawRect(0,0,width-1,height-1);
        }
        g.setColor(Color.black);
        if(product.getName().length() > 10){
            drawStringMiddleOfPanel(product.getName().substring(0,10)+"...",g);
        }else{
            drawStringMiddleOfPanel(product.getName(),g);
        }
//        System.out.println(product.getName());


    }
    protected void setEmpty(){
        product = new Product();
    }
    private void drawStringMiddleOfPanel(String string, Graphics g) {
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
        xCoordinate = getWidth() / 2 - stringWidth / 2;
        yCoordinate = getHeight() / 2 + stringAccent / 2;

        // draw String
        g.drawString(message2, xCoordinate+1, yCoordinate);
//        currentMessage = message2;  // alternate message
    }
    public void setChosen(){
        if(isChosen){
            isChosen =false;
        }else {
            if (!willBePacked && !product.getName().equals("-----")) {
                isChosen = true;
            }
        }
    }

    public void setWilLBePacked(){
        if(willBePacked){
            willBePacked = false;
        }else {
            if (!isChosen) {
                willBePacked = true;
            }
        }
    }
}
