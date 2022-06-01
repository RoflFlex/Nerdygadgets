package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class TSPAlgorithm {
    private ArrayList<Point2D> points;


    protected TSPAlgorithm(ArrayList<Point2D> points){
        this.points = points;


    }


    public ArrayList<Point2D> getPoints() {
        ArrayList<Point2D> result = new ArrayList<>();
        if(points != null) {
            for (Point2D point : points) {
                result.add(point);
            }
        }
        return result;
    }

    public void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }

    public double routeLength(ArrayList<Point2D> cities) {

        //Calculate the length of a TSP route held in an ArrayList as a set of Points
        double result = 0; //Holds the route length
        Point2D prev = cities.get(cities.size() - 1);

        //Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
        for (Point2D city : cities) {

            //Go through each city in turn
            result += city.distance(prev);

            //get distance from the previous city
            prev = city;

            //current city will be the previous city next time
        }
        return result;
    }

    public void addPoint(){
        points.add(new Point2D.Double(1.0,1.0));
    }

    public abstract void alternate();


    public double routeLength() {

        //Calculate the length of a TSP route held in an ArrayList as a set of Points
        double result = 0; //Holds the route length
        Point2D prev = points.get(points.size() - 1);

        //Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
        for (Point2D city : points) {

            //Go through each city in turn
            result += city.distance(prev);

            //get distance from the previous city
            prev = city;

            //current city will be the previous city next time
        }
        return result;
    }
}
