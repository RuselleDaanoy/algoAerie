package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectionSort extends JPanel implements ActionListener {
    private JButton proceedButton;
    private JPanel outputPanel;
    private JTextArea algorithmOutput;
    private JComboBox<String> choicesDropdown;

    public SelectionSort() {
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

        JLabel title = new JLabel("Selection Sort Problem");
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

        String htmlContent = "<html>Sort the items according to the following criteria:<br><br>"
                + "<table border='1' style='margin: 0 auto;'>" +
                "<tr><td>Product Name</td><td>Weight per Unit (kg)</td><td>Quantity Available</td></tr>" +
                "<tr><td>Canned Goods</td><td>5</td><td>450</td></tr>" +
                "<tr><td>Cooking Oil</td><td>3</td><td>725</td></tr>" +
                "<tr><td>Noodles</td><td>2.5</td><td>375</td></tr>" +
                "<tr><td>Soap</td><td>7</td><td>500</td></tr>" +
                "</table><br>"
                + "a. Product Name<br>"
                + "b. Weight<br>"
                + "c. Amount<br><br>"
                + "Provide details on how the sorting process should be illustrated.</html>";

        JLabel content = new JLabel(htmlContent);
        content.setBounds(40, 80, 900, 480);
        content.setVerticalAlignment(JLabel.TOP);
        content.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(content);

        proceedButton = new JButton("Proceed");
        proceedButton.setBounds(440, 570, 100, 30);
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
        outputPanel.add(createOutputPanel(), BorderLayout.CENTER);

        removeAll();
        add(outputPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public JPanel newHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(112, 130, 62));

        JLabel title = new JLabel("Selection Sort Solution");
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel chooseLabel = new JLabel("Choose sorting method:");
        inputPanel.add(chooseLabel);

        String[] choices = {"Select choice", "Product Name", "Weight", "Amount"};
        choicesDropdown = new JComboBox<>(choices);
        inputPanel.add(choicesDropdown);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this::actionPerformedSubmit);
        inputPanel.add(submitButton);

        outputPanel.add(inputPanel, BorderLayout.NORTH);

        algorithmOutput = new JTextArea();
        algorithmOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(algorithmOutput);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        return outputPanel;
    }

    private void actionPerformedSubmit(ActionEvent e) {
        if (e.getSource() == proceedButton) {
            displayOutputPanel();
        } else if (e.getSource() instanceof JButton) {
            String selectedChoice = (String) choicesDropdown.getSelectedItem();

            if (selectedChoice.equals("Select choice")) {
                JOptionPane optionPane = new JOptionPane("Please select your option.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setLocation(700, 370);
                dialog.setVisible(true);
                return;
            }

            Product[] products = {
                    new Product("Canned Goods", 5, 450),
                    new Product("Cooking Oil", 3, 725),
                    new Product("Noodles", 2.5, 375),
                    new Product("Soap", 7, 500)
            };

            StringBuilder initialOrder = new StringBuilder("Initial Order of Products:\n");
            for (Product product : products) {
                initialOrder.append(product).append("\n");
            }

            StringBuilder output = new StringBuilder();
            output.append(initialOrder.toString());
            output.append("\nStart : ").append(java.util.Arrays.toString(products)).append("\n");

            for (int i = 0; i < products.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < products.length; j++) {
                    boolean swap = false;
                    switch (selectedChoice) {
                        case "Product Name":
                            swap = products[j].name.compareTo(products[minIndex].name) < 0;
                            break;
                        case "Weight":
                            swap = products[j].weight < products[minIndex].weight;
                            break;
                        case "Amount":
                            swap = products[j].amount < products[minIndex].amount;
                            break;
                    }
                    if (swap) {
                        minIndex = j;
                    }
                }
                Product temp = products[minIndex];
                products[minIndex] = products[i];
                products[i] = temp;
                output.append("Pass ").append(i + 1).append(": ").append(java.util.Arrays.toString(products)).append("\n");
            }

            output.append("\nSorted by ").append(selectedChoice).append(":\n");
            for (Product product : products) {
                output.append(product).append("\n");
            }

            algorithmOutput.setText(output.toString());
            algorithmOutput.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SelectionSort().setVisible(true);
        });
    }
}
