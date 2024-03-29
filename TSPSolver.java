package com.example;

import java.util.*;

public class tspSolver {

    // Declaring the values given
    private String[] source = {"St. Peter", "St. John", "Lanao", "Maguindanao"};

    private int[][] distance = {
        {0, 300, 150, 200},
        {150, 0, 200, 300}, 
        {100, 120, 0, 200}, 
        {200, 200, 100, 0}
    };

    private String[] shortestPath;
    private int minDistance;
    private ArrayList<String[]> filteredPermutations; // Store filtered permutations
    private ArrayList<Integer> filteredPermutationsTotalDistance; // Store total distance of filtered permutations

    public tspSolver() {
        filteredPermutations = new ArrayList<>();
        filteredPermutationsTotalDistance = new ArrayList<>();
    }

    // Method to solve TSP
    public void solveTSP(String startingPoint) {
        // Initializing variables
        shortestPath = null;
        minDistance = Integer.MAX_VALUE;

        // Calls permute() method to create all possible permutations
        ArrayList<String[]> permutation = permute(source);

        filteredPermutations.clear(); // Clear previous filtered permutations
        filteredPermutationsTotalDistance.clear(); // Clear previous total distances

        for (String[] p : permutation) {
            if (startingPoint.equals(p[0])) {
                filteredPermutations.add(p);
                int totalDistance = calculateTotalDistance(p);
                filteredPermutationsTotalDistance.add(totalDistance);
            }
        }

        // Iterates over each permutation generated (Loop)
        for (int i = 0; i < filteredPermutations.size(); i++) {
            String[] p = filteredPermutations.get(i);
            int totalDistance = filteredPermutationsTotalDistance.get(i);

            // Print out all possible permutations
            for (String location : p) {
                System.out.print(location + " -> ");
            }
            System.out.print(p[0]); // Prints the starting point of each corresponding permutation
            System.out.print(" = " + totalDistance);
            System.out.println();

            // Updating shortest path and distance if the current path is shorter
            if (totalDistance < minDistance) {
                minDistance = totalDistance;
                shortestPath = p;
            }
        }

        // Print optimal path and distance
        System.out.println("\nYour shortest route is: ");
        for (String location : shortestPath) {
            System.out.print(location + " -> ");
        }
        System.out.println(shortestPath[0]); // Prints out the starting point of the shortest path
        System.out.println("\nWith a distance of: " + minDistance);
    }

    // Method to generate all possible permutations
    private ArrayList<String[]> permute(String[] source) {
        // Initializes list to store the permutations
        ArrayList<String[]> permutation = new ArrayList<>();

        // Recursive method to generate permutations
        permuteSolve(source, 0, permutation);

        return permutation;
    }

    // Recursive method to generate permutations
    private void permuteSolve(String[] source, int index, ArrayList<String[]> permutation) {
        // If index reaches the length of source array, adds the current permutation to the list
        if (index == source.length - 1) {
            String[] permutations = source.clone(); // clone() method makes the copy of the data itself
            permutation.add(permutations);
        } else {
            for (int i = index; i < source.length; i++) {
                swap(source, index, i); // Swapping elements
                permuteSolve(source, index + 1, permutation); // Recursive permutation
                swap(source, index, i); // Maintaining original array for next permutations
            }
        }
    }

    // Method to swap elements in array
    private void swap(String[] source, int x, int y) {
        String temp = source[x];
        source[x] = source[y];
        source[y] = temp;
    }

    // Method to get the indices of location in source array
    private int getIndex(String location) {
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals(location)) {
                return i;
            }
        }
        return -1; // Default; or not found
    }

    // Method to calculate the total distance of a permutation
    private int calculateTotalDistance(String[] permutation) {
        int totalDistance = 0;
        for (int i = 0; i < permutation.length - 1; i++) {
            int fromIndex = getIndex(permutation[i]);
            int toIndex = getIndex(permutation[i + 1]);
            totalDistance += distance[fromIndex][toIndex];
        }
        // Add distance from last location back to starting point
        int lastIndex = getIndex(permutation[permutation.length - 1]);
        int startIndex = getIndex(permutation[0]);
        totalDistance += distance[lastIndex][startIndex];
        return totalDistance;
    }

    public String getShortestPathAsString() {
        // Convert shortestPath array to a string
        StringBuilder sb = new StringBuilder();
        for (String location : shortestPath) {
            sb.append(location).append(" -> ");
        }
        sb.append(shortestPath[0]); // Append the starting point
        return sb.toString();
    }

    public int getMinDistance() {
        return minDistance;
    }

    // Method to get filtered permutations
    public ArrayList<String[]> getFilteredPermutations() {
        return filteredPermutations;
    }

    public ArrayList<Integer> getFilteredPermutationsTotalDistance() {
        return filteredPermutationsTotalDistance;
    }

    public static void main(String[] args) {
        tspSolver tsp = new tspSolver();

        Scanner sc = new Scanner(System.in);      

        System.out.println("St. Peter\n" +
                           "St. John\n" +
                           "Lanao\n" +
                           "Maguindanao\n\n");;
        
        System.out.print("Choose your starting point: ");
        String location = sc.nextLine();

        sc.close();

        System.out.println("\nYour starting point will be " + location.trim());
        System.out.println();
        
        tsp.solveTSP(location); // Example: Starting point is "St. Peter"
    }
}
