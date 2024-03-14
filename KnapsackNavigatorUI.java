package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class KnapsackNavigatorUI {

    private JPanel mainPanel;
    private JTextField capacityField;
    private JTextArea resultArea;
    private JButton proceedButton;
    private JPanel knapsackUIPanel; 

    public KnapsackNavigatorUI() {
        initialize();
    }

    private void initialize() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(createTextMessagePanel());
    }

    private JPanel createTextMessagePanel() {
        JPanel textMessagePanel = new JPanel();
        textMessagePanel.setLayout(new BoxLayout(textMessagePanel, BoxLayout.Y_AXIS));
    
        textMessagePanel.add(Box.createVerticalGlue());

        JLabel textMessageLabel = new JLabel("<html><div style='text-align: center;'><font face='Tahoma' size='20' color='black'><b>KNAPSACK:</b></font><br>" +
                "Identify the compatible cargo for the vehicle, adhering to strict weight restrictions. The vehicle can accommodate loads ranging from a minimum of 1 kilogram to a maximum of 15 kilograms.<br>" +
                "<br>" +
                "<table border='1'>" +
                "<tr><td>Product Name</td><td>Weight per Unit (kg)</td><td>Quantity Available</td></tr>" +
                "<tr><td>Canned Goods</td><td>5</td><td>450</td></tr>" +
                "<tr><td>Cooking Oil</td><td>3</td><td>725</td></tr>" +
                "<tr><td>Noodles</td><td>2.5</td><td>375</td></tr>" +
                "<tr><td>Soap</td><td>7</td><td>500</td></tr>" +
                "</table></div></html>");
        textMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textMessagePanel.add(textMessageLabel);

        textMessagePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showKnapsackUIPanel();
            }
        });
        textMessagePanel.add(proceedButton);
    
        textMessagePanel.add(Box.createVerticalGlue());
    
        return textMessagePanel;
    }

    private void showKnapsackUIPanel() {
        mainPanel.removeAll();

        JLabel titleLabel = new JLabel("Knapsack Problem");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
    
        if (knapsackUIPanel == null) {
            knapsackUIPanel = createKnapsackUIPanel();
            mainPanel.add(knapsackUIPanel);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createKnapsackUIPanel() {
        JPanel knapsackUIPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel capacityLabel = new JLabel("Enter Capacity from (1-15): ");
        capacityLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        capacityField = new JTextField(20);
        capacityField.setMaximumSize(new Dimension(280, 25));
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solveKnapsack();
            }
        });
    
        inputPanel.add(capacityLabel);
        inputPanel.add(capacityField);
        inputPanel.add(solveButton);
    
        knapsackUIPanel.add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        knapsackUIPanel.add(scrollPane, BorderLayout.CENTER);
    
        return knapsackUIPanel;
    }
    
    void solveKnapsack() {
        try {
            double capacity = Double.parseDouble(capacityField.getText());
            if (capacity < 1 || capacity > 15) {
                JOptionPane.showMessageDialog(mainPanel, "Capacity must be between 1 and 15.");
                return;
            }

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
    
        ArrayList<ArrayList<Product>> mostValuableSubsets = new ArrayList<>();
        int maxTotalValue = 0;
    
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

            if (totalValue > maxTotalValue && totalWeight <= capacity) {
                maxTotalValue = totalValue;
                mostValuableSubsets.clear();
                mostValuableSubsets.add(subset);
            } else if (totalValue == maxTotalValue && totalWeight <= capacity) {
                mostValuableSubsets.add(subset);
            }
        }

        if (!mostValuableSubsets.isEmpty()) {
            table.append("\n\nMost valuable subsets:\n");
            for (ArrayList<Product> subset : mostValuableSubsets) {
                double totalWeight = calculateTotalWeight(subset);
                table.append("Subset: {");
                for (Product product : subset) {
                    table.append(product.name).append(",");
                }
                table.setLength(table.length() - 1);
                table.append("}\n");
                table.append("Total Weight: ").append(totalWeight).append("\n");
                table.append("Total Value: ").append(maxTotalValue).append("\n\n");
            }
        }
    
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        resultArea.setFont(font);
        resultArea.setText(table.toString());
    }
    

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
