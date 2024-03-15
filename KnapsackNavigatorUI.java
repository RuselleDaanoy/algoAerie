package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class KnapsackNavigatorUI extends JPanel implements ActionListener {
    private JButton proceedButton;
    private JPanel outputPanel;
    private JTextArea algorithmOutput;
    private JTextField capacityField; // Added capacityField

    public KnapsackNavigatorUI() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(980, 700));

        add(mainPanel(), BorderLayout.CENTER);
    }

    public JPanel mainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(243, 234, 214));

        panel.add(header(), BorderLayout.NORTH);
        panel.add(content(), BorderLayout.CENTER);

        return panel;
    }

    public JPanel header() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(112, 130, 62));

        JLabel title = new JLabel("Knapsack Problem");
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title, BorderLayout.CENTER);

        return panel;
    }

    public JPanel content() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(243, 234, 214));
        panel.setBounds(0, 40, 980, 655);
    
        String htmlContent = "<html><div style='text-align: center;'><font face='Tahoma' size='20' color='black'><b>KNAPSACK:</b></font><br>" +
                "Identify the compatible cargo for the vehicle, adhering to strict weight restrictions. The vehicle can accommodate loads ranging from a minimum of 1 kilogram to a maximum of 15 kilograms.<br>" +
                "<br>" +
                "<table border='1'>" +
                "<tr><td>Product Name</td><td>Weight per Unit (kg)</td><td>Quantity Available</td></tr>" +
                "<tr><td>Canned Goods</td><td>5</td><td>450</td></tr>" +
                "<tr><td>Cooking Oil</td><td>3</td><td>725</td></tr>" +
                "<tr><td>Noodles</td><td>2.5</td><td>375</td></tr>" +
                "<tr><td>Soap</td><td>7</td><td>500</td></tr>" +
                "</table></div></html>";
    
        JLabel content = new JLabel(htmlContent);
        content.setBounds(40, 80, 900, 480); // Adjusted bounds for the content label
        content.setVerticalAlignment(JLabel.TOP);
        content.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(content);
    
        proceedButton = new JButton("Proceed");
        proceedButton.setBounds(440, 500, 100, 30); // Set bounds for the "Proceed" button
        proceedButton.addActionListener(this);
    
        panel.add(proceedButton);
    
        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proceedButton) {
            displayOutputPanel();
        }
    }

    public void displayOutputPanel() {
        outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBackground(new Color(243, 234, 214));
        outputPanel.setPreferredSize(new Dimension(980, 680));

        outputPanel.add(newHeader(), BorderLayout.NORTH);
        outputPanel.add(createKnapsackUIPanel(), BorderLayout.CENTER);

        removeAll();
        add(outputPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public JPanel newHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(112, 130, 62));

        JLabel title = new JLabel("Knapsack Subsets and Solution");
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createKnapsackUIPanel() {
        JPanel knapsackUIPanel = new JPanel(new BorderLayout());

        // Input panel with capacity field
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel capacityLabel = new JLabel("Enter Capacity from (1-15): ");
        capacityField = new JTextField(20);
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

        // Add JTextArea for result below the inputPanel
        algorithmOutput = new JTextArea();
        algorithmOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(algorithmOutput);
        knapsackUIPanel.add(scrollPane, BorderLayout.CENTER);

        return knapsackUIPanel;
    }

    void solveKnapsack() {
        try {
            double capacity = Double.parseDouble(capacityField.getText());
            if (capacity < 1 || capacity > 15) {
                JOptionPane.showMessageDialog(outputPanel, "Capacity must be between 1 and 15.");
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
            JOptionPane.showMessageDialog(outputPanel, "Invalid input. Please enter a number.");
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

            // Check if this subset has higher value than previous max
            if (totalValue > maxTotalValue && totalWeight <= capacity) {
                maxTotalValue = totalValue;
                mostValuableSubsets.clear(); // Clear previous max subsets
                mostValuableSubsets.add(subset);
            } else if (totalValue == maxTotalValue && totalWeight <= capacity) {
                // If this subset has the same highest value as the previous max, add it to the list
                mostValuableSubsets.add(subset);
            }
        }

        // Print the most valuable subsets
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

        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 15);
        algorithmOutput.setFont(font); // Changed to algorithmOutput
        algorithmOutput.setText(table.toString()); // Changed to algorithmOutput
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KnapsackNavigatorUI().setVisible(true);
        });
    }
}
