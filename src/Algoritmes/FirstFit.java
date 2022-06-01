package Algoritmes;

import java.util.Arrays;

public class FirstFit {
    public static int checkFirstFit(int[] weightArr, int capacity) {
        int binCount = 1;
        int[] binRemCap = new int[weightArr.length]; // array of bin remaining capacities
        Arrays.fill(binRemCap, capacity); // initialize all bins with a start capacity, but think as if they were all closed except first one
        for (int weight: weightArr) {
            // look for first bin that can accommodate the item
            int i;
            for (i=0; i<binCount; i++) {
                if (binRemCap[i] >= weight) {
                    binRemCap[i] -= weight;
                    break;
                }
            }
            if (i == binCount) { // no open bin can take the item, open a new one
                binCount++;
                binRemCap[binCount-1] -= weight;
            }
        }
        return binCount;
    }
}
