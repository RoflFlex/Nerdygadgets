package Algoritmes.TSP;

import GUI.Order;
import Klasses.Product;

import java.util.ArrayList;

public abstract class BPPAlgorithm {
    public abstract ArrayList<ArrayList<Product>> getBestPlacement(Order order);
}