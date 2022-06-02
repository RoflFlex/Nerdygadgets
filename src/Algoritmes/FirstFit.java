package Algoritmes;

import Algoritmes.TSP.BPPAlgorithm;
import GUI.Order;
import Klasses.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstFit extends BPPAlgorithm {
    public static ArrayList<ArrayList<Product>> bins;
    private static Order order;

    @Override
    public ArrayList<ArrayList<Product>> getBestPlacement(Order order){
        FirstFit.order = order;
        bins = new ArrayList<>();
        int[] weights = new int[order.getItems().size()];
        int index = 0;
        for (Object[] object : order.getItems()){
            weights[index] = (int)object[4];
            index++;
        }
        checkFirstFit(weights, 100);

        return bins;
    }
    public static int checkFirstFit(int[] weightArr, int capacity) {
        int binCount = 1;
        int[] binRemCap = new int[weightArr.length]; // array of bin remaining capacities
        Arrays.fill(binRemCap, capacity); // initialize all bins with a start capacity, but think as if they were all closed except first one
        FirstFit.bins.add(new ArrayList<>());
        int index = 0;
        for (int weight: weightArr) {
            // look for first bin that can accommodate the item
            int i;
            for (i=0; i<binCount; i++) {
                if (binRemCap[i] >= weight) {
                    FirstFit.bins.get(binCount - 1).add(new Product((String)order.getItems().get(index)[1], 0, weight));
                    binRemCap[i] -= weight;
                    break;
                }
            }
            if (i == binCount) { // no open bin can take the item, open a new one
                FirstFit.bins.add(new ArrayList<>());
                binCount++;
                binRemCap[binCount-1] -= weight;
                FirstFit.bins.get(binCount - 1).add(new Product((String)order.getItems().get(index)[1], 0, weight));
            }
            index++;
        }
        return binCount;
    }
}
