package Algoritmes.TSP;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class OwnChoice extends TSPAlgorithm{
    public OwnChoice(ArrayList<Point2D> points) {
        super(points);
    }


    @Override
    public void alternate() {
        TwoOpt opt = new TwoOpt();
        NearestInsertion insertion = new NearestInsertion(getPoints());
        opt.setPoints(insertion.getPoints());
        opt.alternate();
        setPoints(opt.getPoints());
    }
}
