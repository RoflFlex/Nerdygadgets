package Algoritmes;

import java.util.ArrayList;

public class BestFit {
    static int bestFit(int weight[], int totalItems, int max)
    {
        int totalBins = 0;

        int []bins = new int[totalItems];
        ArrayList<String> items = new ArrayList<>();

        if (weight.length != 0) {
            totalBins += 1;
            for (int item: weight) {
                for (int bin = 0; bin < totalBins; bin++) {
                    if (item + bins[bin] <= max) {
                        bins[bin] += item;
                        if (bin >= items.size()) {
                            items.add("");
                        }
                        items.set(bin, items.get(bin) + " " + item);
                        break;
                    } else {
                        if (bin == totalBins -1) {
                            totalBins += 1;
                        }
                    }
                }
            }

        }
        for (String item: items) {
            System.out.println(item);
        }
        return totalBins;

    }

    public static void main(String[] args)
    {
        int []weight = {2,8,9,2};
        int totalWeight = 0;
        for (int i = 0; i < weight.length; i++) {
            totalWeight += weight[i];
        }
        int max = 10; //max weigth per bin
        int totalItems = weight.length;
        System.out.print("Je hebt "
                 + bestFit(weight, totalItems, max) + " bins nodig");
    }
}
