package Panels;

import javax.swing.*;
import Klasses.Box;
import Klasses.Product;

import java.awt.*;

public class BoxPanel extends JPanel {
    private Box box;
    private Color color = Color.white;
    private final int width = 80;
    private final int height = 80;

    public BoxPanel(Box box){
        setPreferredSize(new Dimension(width,height));
        setBox(box);
        setColor();
    }



    private void setColor(){
        double lowBorder = Box.maxWeight * 0.25;
        double highBorder = Box.maxWeight * 0.75;
        if(lowBorder > box.getWeight() && box.getWeight() > 0){
            color = Color.green;
        }else if ( box.getWeight() > lowBorder && box.getWeight() < highBorder){
            color = Color.BLUE;
        }else if(box.getWeight() > highBorder && box.getWeight()<=Box.maxWeight){
            color = Color.RED;
        }else{
            color = Color.white;
        }

    }

    public void addItem(Product product){
        box.addProduct(product);
        setColor();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setColor();
        setBackground(color);
        if(color == Color.WHITE){
            g.setColor(Color.black);
            g.drawRect(0,0,getWidth()-1,getHeight()-1);
        }else{
            g.setColor(color);
            g.fillRect(0,0,getWidth()-1,getHeight()-1);
        }
        g.setColor(Color.black);
        drawStringMiddleOfPanel(box.toString(),g);

    }

    public void addProduct(Product product){
        box.addProduct(product);
        setColor();
        repaint();
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

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
        setColor();
        repaint();
    }
}
