package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

        double startTime = System.currentTimeMillis();
        ArrayList<Point2D> cities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50 ; i++){
            Point2D point = new Point2D.Float(random.nextInt(1,6), random.nextInt(1,6));
            if(!cities.contains(point)){
                cities.add(point);
            }//alter file name here.
        }
        System.out.println(cities);
        System.out.println(cities.size());
        ArrayList<Point2D> nearestN;
        ArrayList<Point2D> result;
        ArrayList<Point2D> city ;
//        System.out.println(Length.routeLength(cities));

        city = NearestInsertion.nearestInsertion(cities);
        System.out.println(Length.routeLength(city));
        System.out.println(city.size());
        System.out.println(city);

        result = TwoOpt.alternate(cities);
        System.out.println(Length.routeLength(result));
        System.out.println(result.size());
        System.out.println(result);

        nearestN = NearestNeighbour.nearestNeighbour(cities);
        System.out.println(Length.routeLength(nearestN));
        System.out.println(nearestN.size());
        System.out.println(nearestN);

        result = TwoOpt.alternate(city);
        System.out.println(Length.routeLength(result));
        System.out.println(result.size());
        System.out.println(result);

//        city = TwoOpt.alternate(cities);
//        System.out.println(Length.routeLength(city));
//        System.out.println(city.size());
//        System.out.println(city);

//        double length =0 ;
//        System.out.println("Initial tour length is: " + length);
//        double time = System.currentTimeMillis() - startTime;
//        System.out.println("Time taken to initialize is: " + time);
//        System.out.println("Generating Nearest Neighbour Solution...");
//        nearestN = Neighbour.nearest(cities);
//        length = Length.routeLength(nearestN);
//        System.out.println("Nearest neighbour solution complete, distance: " + length);
//        System.out.println("Validating solution...");
//        Validator.validate(nearestN);
//        time = System.currentTimeMillis() - startTime;
//        System.out.println("Time taken for init and Nearest Neighbour: " + time);
//
//        startTime = System.currentTimeMillis();
//        System.out.println("Attempting 2-opt optimisation...");
//        result = TwoOpt.alternate(nearestN);
//        length = Length.routeLength(result);
//        System.out.println("2-opt solution complete, distance: " + length);
//        System.out.println("Validating solution...");
//        Validator.validate(result);
//        time = System.currentTimeMillis() - startTime;
//        System.out.println("Time taken for 2 opt optimisation: " + time);
//        System.out.println("Resulting tour node count: " + result.size());
//        city = TwoOpt.alternate(cities);
//        length = Length.routeLength(city);
//        System.out.println(city);
//        length = Length.routeLength(result);
//        System.out.println("2-opt solution complete, distance: " + length);
//        System.out.println(length);
    }
}