package com.example;

import java.util.*;

public class KnapsackSolver {

    static ArrayList<ArrayList<Product>> generateSubsets(Product[] products) {
        ArrayList<ArrayList<Product>> subsets = new ArrayList<>();
        int n = products.length;

        for (int i = 0; i < (1 << n); i++) {
            ArrayList<Product> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(products[j]);
                }
            }
            subsets.add(subset);
        }

        Collections.sort(subsets, (a, b) -> {
            int aSize = a.size();
            int bSize = b.size();
            if (aSize != bSize) {
                return Integer.compare(aSize, bSize);
            }

            for (int i = 0; i < aSize; i++) {
                String aName = a.get(i).name;
                String bName = b.get(i).name;
                int nameCompare = aName.compareTo(bName);
                if (nameCompare != 0) {
                    return nameCompare;
                }
            }
            return 0;
        });

        return subsets;
    }
}
