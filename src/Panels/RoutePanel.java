package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class RoutePanel extends JPanel {
    private int lineId = 0;
    private Point2D robotCoordinate;
    private boolean robotIsMoving = false;
    private ArrayList<Point2D> points = new ArrayList<>();
    private int width = 415, height = 415;
    private int productPanelWidth = 80;

    public RoutePanel(){
        setPreferredSize(new Dimension(width,height));
//        setLayout(new GridLayout(5,5,2,2));
        setVisible(true);
    }

    public void setPoints(ArrayList<Point2D> points){
        this.points = points;
        robotCoordinate = points.get(0);
        nextPoint();
    }

    public void deletePoints(){
        robotIsMoving = false;
        setPoints(null);
        lineId = 0;
    }

    public void nextPoint(){
        if(points != null ){
            robotIsMoving = true;
            lineId ++;
            robotCoordinate = points.get(lineId - 1);
        }else if(points != null && points.size() < lineId){
            deletePoints();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(robotIsMoving){
            g.setColor(Color.RED);
            g.drawLine((int) (productPanelWidth+ productPanelWidth*(robotCoordinate.getX()-1)),(int)(productPanelWidth/2+ productPanelWidth*(robotCoordinate.getY()-1)),
                    (int)(productPanelWidth/2+ productPanelWidth*(points.get(lineId).getX()-1)), (int)(productPanelWidth/2+ productPanelWidth*(points.get(lineId).getY()-1)));
        }
    }
}
