package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

        double startTime = System.currentTimeMillis();
        ArrayList<Point2D> cities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10 ; i++){
            Point2D point = new Point2D.Float(random.nextInt(1,6), random.nextInt(1,6));
            if(!cities.contains(point)){
                cities.add(point);
            }//alter file name here.
        }
        System.out.println(cities);
        ArrayList<Point2D> city = cities;
        TwoOpt opt = new TwoOpt();
        TSPAlgorithm algorithm = new NearestNeighbour(cities);
        System.out.println("Nearest Neighbour: " + algorithm.routeLength());
        algorithm = new TwoOpt(city);
        System.out.println("Two Opt: " + algorithm.routeLength());
        algorithm = new NearestInsertion(cities);
        System.out.println("Nearest Insertion: " + algorithm.routeLength());

        algorithm = new OwnChoice(cities);
        System.out.println("Eigen optie:" + algorithm.routeLength());
    }
}