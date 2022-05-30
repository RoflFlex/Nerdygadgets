package Panels;

import javax.swing.*;
import Klasses.Box;
import java.awt.*;
import java.util.ArrayList;

public class TurningPanel extends JPanel {
    private final int quantity = 4;
    private BoxPanel[] boxes = new BoxPanel[quantity];
    private int width = 250, height = 250;
    private final int[] positions = {3,1,5,7};
    public TurningPanel(){
        setPreferredSize(new Dimension(width ,height));
        setLayout(new GridLayout(3,3,8,8));
        setVisible(true);
        for(int i = 0; i < quantity; i++){
            boxes[i] = new BoxPanel(new Box());
        }
        update();

    }

    public void update(){
        for (int i = 0 ; i < 9 ; i ++){
            if(i == 1){
//                boxes[0] = new BoxPanel(new Box());
                add(boxes[1]);
            }else if(i == 3){
//                boxes[3] = new BoxPanel(new Box());
                add(boxes[0]);
            }else
            if(i == 5){
//                boxes[1] = new BoxPanel(new Box());
                add(boxes[2]);
            }else
            if(i == 7){
//                boxes[2] = new BoxPanel(new Box());
                add(boxes[3]);
            }else{
                add(new JLabel());
            }

        }
    }

    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<>();
        for(int i = 0; i < quantity; i ++){
            boxes.add(this.boxes[i].getBox());
        }
        return boxes;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        for(int i = 0; i < quantity; i++){
            this.boxes[i] = new BoxPanel(new Box());
        }
        for(int i = 0; i < boxes.size(); i ++){
            this.boxes[i].setBox(boxes.get(i));
        }
        repaint();
    }

    public void turnTimes(int times){
        for(int i = 0; i < times; i ++ ){
            turn();
        }
    }

    private void turn(){
        for(int i = 0; i < quantity-1; i ++){
            Box box = boxes[i].getBox();
//            if (i == quantity-1){
//                boxes[i].setBox(boxes[i].getBox());
//                boxes[i+1].setBox(box);
//            }else{
                boxes[i].setBox(boxes[i+1].getBox());
                boxes[i+1].setBox(box);

//            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < quantity; i++){
            boxes[i].repaint();
        }
        g.drawOval(0,0, width,height);
    }
}
