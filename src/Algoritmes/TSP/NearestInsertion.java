package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class NearestInsertion {

    protected static ArrayList<Point2D> nearestInsertion(ArrayList<Point2D> cities){
        ArrayList<Point2D> newTour = new ArrayList<>();
        double dist;
        newTour.add(cities.get(0));
        newTour.add(cities.get(1));
        for (int i = 2; i < cities.size(); i++){
            int iToChange =1;
            dist = Double.MAX_VALUE;
//            while (!isUpdated) {
                for (int j = 0; j < newTour.size() - 1; j++) {
                    if (distanceToLine(cities.get(i), newTour.get(j), newTour.get(j + 1)) < dist) {
                        iToChange = j + 1;
                        dist = distanceToLine(cities.get(i), newTour.get(j), newTour.get(j + 1));
                    }
                }
//                ArrayList<Point2D> newTour1 = newTour;
//                ArrayList<Point2D> newTour2 = newTour;
//                newTour1.add(iToChange, cities.get(i));
//                newTour2.add(cities.get(i));
//                if (Length.routeLength(newTour1) <= Length.routeLength(newTour2)) {
//                    newTour = newTour2;
//                    isUpdated = true;
//                } else {
//                    exceptions.add(iToChange);
//                }
//                newTour1.remove(iToChange);
                newTour.add(iToChange, cities.get(i));
//            }

        }

        cities = newTour;
        return cities;
    }

    private static double distanceToLine(Point2D p, Point2D endA, Point2D endZ) {
        /* Geometry here is:
         * - A and B are points on ends of the line
         * - C is a point, distance from which to AB is to be calculated
         * - D is a point on AB, such that CD is perpendicular to AB.
         * We need to find length of CD. Composing a system of equations:
         * - AC squared = AD squared + CD squared (ACD is a right triangle)
         * - BC squared = BD squared + CD squared (BCD is also a right triangle)
         * - AD + BD = AB (D is on AB)
         * it's solution is obvious from the code :-)
         */
        double distance;
        double AC = p.distance(endA);
        double BC = p.distance(endZ);
        double AB = endA.distance(endZ);
        if (AB == (AC + BC)) {
            return 0;
        }
        double ACs = AC * AC;
        double BCs = BC * BC;
        double AD_BD = (ACs - BCs) / AB;
        double AD = (AD_BD + AB) / 2;
        double CDs = ACs - (AD * AD);
        distance = Math.sqrt(CDs);
        double BD = Math.sqrt(Math.pow(BC,2) - Math.pow(distance,2));
        AD = Math.sqrt(Math.pow(AC,2) - Math.pow(distance,2));
        if (AB != (BD + AD)) {
            if(BD - AD > 0){
                distance = p.distance(endA);
            }else {
                distance = p.distance(endZ);
            }
        }
        return distance;
    }

}
