package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TwoOpt extends TSPAlgorithm{

    public TwoOpt(ArrayList<Point2D> points) {
        super(points);

    }

    public TwoOpt(){
        this(null);
    }

    public void alternate() {
        if(getPoints() != null) {
            ArrayList<Point2D> newTour;
            ArrayList<Point2D> cities = getPoints();
            double bestDist = routeLength(cities);
            double newDist;
            int swaps = 1;
//        int improve = 0;
//        int iterations = 0;
//        long comparisons = 0;

            while (swaps != 0) { //loop until no improvements are made.
                swaps = 0;

                //initialise inner/outer loops avoiding adjacent calculations and making use of problem symmetry to half total comparisons.
                for (int i = 1; i < cities.size() - 2; i++) {
                    for (int j = i + 1; j < cities.size() - 1; j++) {
//                    comparisons++;
                        //check distance of line A,B + line C,D against A,C + B,D if there is improvement, call swap method.
                        if ((cities.get(i).distance(cities.get(i - 1)) + cities.get(j + 1).distance(cities.get(j))) >=
                                (cities.get(i).distance(cities.get(j + 1)) + cities.get(i - 1).distance(cities.get(j)))) {

                            newTour = swap(cities, i, j); //pass arraylist and 2 points to be swapped.

                            newDist = routeLength(newTour);

                            if (newDist < bestDist) { //if the swap results in an improved distance, increment counters and update distance/tour
                                cities = newTour;
                                bestDist = newDist;
                                swaps++;
//                            improve++;
                            }
                        }
                    }
                }
//            iterations++;
            }
//        System.out.println("Total comparisons made: " + comparisons);
//        System.out.println("Total improvements made: " + improve);
//        System.out.println("Total iterations made: " + iterations);
            boolean startFounded = false;
            int ind = 0;
            ArrayList<Point2D> po = new ArrayList<>();
            if (cities.get(0).getY() != 1.0 && cities.get(0).getX() != 1.0) {
                for (int i = 0; i < cities.size(); i++) {
                    if (cities.get(i).getY() == 1.0 && cities.get(i).getX() == 1.0 && !startFounded) {
                        startFounded = true;
                        ind = i;
                    }
                    if (startFounded) {
                        po.add(cities.get(i));
                    }
                }
                for (int i = 0; i < ind; i++) {
                    po.add(cities.get(i));
                }
                setPoints(po);
            } else {
                setPoints(cities);
            }
        }
    }

    private ArrayList<Point2D> swap(ArrayList<Point2D> cities, int i, int j) {
        //conducts a 2 opt swap by inverting the order of the points between i and j
        ArrayList<Point2D> newTour = new ArrayList<>();

        //take array up to first point i and add to newTour
        int size = cities.size();
        for (int c = 0; c <= i - 1; c++) {
            newTour.add(cities.get(c));
        }

        //invert order between 2 passed points i and j and add to newTour
        int dec = 0;
        for (int c = i; c <= j; c++) {
            newTour.add(cities.get(j - dec));
            dec++;
        }

        //append array from point j to end to newTour
        for (int c = j + 1; c < size; c++) {
            newTour.add(cities.get(c));
        }

        return newTour;
    }
}