package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class KnapsackNavigatorUI {

    private JPanel mainPanel;
    private JTextField capacityField;
    private JTextArea resultArea;

    public KnapsackNavigatorUI() {
        initialize();
    }

    private void initialize() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel capacityLabel = new JLabel("Enter Capacity from (1-15): ");
        capacityLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        capacityField = new JTextField(5);
        capacityField.setMaximumSize(new Dimension(280, 25));
        JButton solveButton = new JButton("Solve");
        solveButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solveKnapsack();
            }
        });
    
        inputPanel.add(capacityLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        inputPanel.add(capacityField);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(solveButton);
    
        mainPanel.add(inputPanel);
    
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        mainPanel.add(scrollPane);
    }

    void solveKnapsack() {
        try {
            double capacity = Double.parseDouble(capacityField.getText());
            if (capacity < 1 || capacity > 15) {
                JOptionPane.showMessageDialog(mainPanel, "Capacity must be between 1 and 15.");
                return;
            }

            // Define products
            Product[] products = {
                    new Product("Canned Goods", 5, 450),
                    new Product("Cooking Oil", 3, 725),
                    new Product("Noodles", 2.5, 375),
                    new Product("Soap", 7, 500)
            };

            ArrayList<ArrayList<Product>> subsets = KnapsackSolver.generateSubsets(products);
            printTable(subsets, capacity);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Invalid input. Please enter a number.");
        }
    }

    private double calculateTotalWeight(ArrayList<Product> subset) {
        double totalWeight = 0;
        for (Product product : subset) {
            totalWeight += product.weight;
        }
        return totalWeight;
    }

    private int calculateTotalValue(ArrayList<Product> subset) {
        int totalValue = 0;
        for (Product product : subset) {
            totalValue += product.amount;
        }
        return totalValue;
    }

    private void printTable(ArrayList<ArrayList<Product>> subsets, double capacity) {
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-40s%-20s%-20s%n", "Subset", "Total Weight", "Total Value"));

        for (ArrayList<Product> subset : subsets) {
            double totalWeight = calculateTotalWeight(subset);
            int totalValue = calculateTotalValue(subset);
            StringBuilder subsetString = new StringBuilder("{");
            for (Product product : subset) {
                subsetString.append(product.name).append(",");
            }
            if (!subset.isEmpty()) {
                subsetString.setLength(subsetString.length() - 1);
            }
            subsetString.append("}");

            String feasibility = "";
            if (totalWeight > capacity) {
                feasibility = "-not feasible";
            }

            table.append(String.format("%-40s%-20.2f%-20d%s%n", subsetString, totalWeight, totalValue, feasibility));
        }

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        resultArea.setFont(font);
        resultArea.setText(table.toString());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
