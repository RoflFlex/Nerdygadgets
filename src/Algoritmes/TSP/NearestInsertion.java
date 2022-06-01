package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class NearestInsertion extends TSPAlgorithm{


    public NearestInsertion(ArrayList<Point2D> points) {
        super(points);

    }

    public void alternate(){
        if(getPoints() != null) {
            ArrayList<Point2D> cities = getPoints();
            ArrayList<Point2D> newTour = new ArrayList<>();
            double dist;
            newTour.add(cities.get(0));
            newTour.add(cities.get(1));
            for (int i = 2; i < cities.size(); i++) {
                int iToChange = 1;
                dist = Double.MAX_VALUE;
//
                for (int j = newTour.size(); j >= 1; j--) {
                    ArrayList<Point2D> newTour1 = new ArrayList<>(newTour);
                    newTour1.add(j, cities.get(i));
                    if (this.routeLength(newTour1) < dist) {
                        iToChange = j;
                        dist = this.routeLength(newTour1);
                    }
                }
//
                newTour.add(iToChange, cities.get(i));
//            }

            }

            cities = newTour;
            setPoints(cities);
        }
    }
    

}
