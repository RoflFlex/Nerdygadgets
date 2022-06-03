package Panels;

import Klasses.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SortingLinePanel extends JPanel {
    private ArrayList<ProductPanel> productPanels = new ArrayList<>();

    public SortingLinePanel(){
        setPreferredSize(new Dimension(415, 90));
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(Color.WHITE);
        setAlignmentX(getInsets().right);
    }
    public void addItem(Product product){
        ProductPanel productPanel = new ProductPanel(product);
        productPanels.add(productPanel);
        removeAll();
        for(int i = productPanels.size() -1 ; i >= 0; i --){
            add(productPanels.get(i));
        }
        repaint();
    }

    public Product getFirst(){
        if (productPanels.size() > 0)
            return productPanels.get(0).getProduct();
        else
            return null;
    }

    public void deleteItem(Product product){
        for (int i = 0 ; i < productPanels.size(); i ++){
            if(product == productPanels.get(i).getProduct()){
                productPanels.remove(i);
            }
        }
        repaint();
    }

    public void deleteFirst(){
        remove(productPanels.get(0));
        productPanels.remove(0);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < productPanels.size(); i ++){
            productPanels.get(i).repaint();
        }
    }

}
