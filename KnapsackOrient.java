package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KnapsackOrient extends JPanel {
    private JPanel mainPanel;
    private JPanel userInputPanel; // Panel containing user input components
    private JButton proceedButton; // Button to proceed after input
    private KnapsackNavigatorUI navigatorUI;

    public KnapsackOrient() {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'><font face='Tahoma' size='20' color='black'><b>KNAPSACK:</b></font><br>" +
                "Identify the compatible cargo for the vehicle, adhering to strict weight restrictions. The vehicle can accommodate loads ranging from a minimum of 1 kilogram to a maximum of 15 kilograms.<br>" +
                "<br>" +
                "<table border='1'>" +
                "<tr><td>Product Name</td><td>Weight per Unit (kg)</td><td>Quantity Available</td></tr>" +
                "<tr><td>Canned Goods</td><td>5</td><td>450</td></tr>" +
                "<tr><td>Cooking Oil</td><td>3</td><td>725</td></tr>" +
                "<tr><td>Noodles</td><td>2.5</td><td>375</td></tr>" +
                "<tr><td>Soap</td><td>7</td><td>500</td></tr>" +
                "</table></div></html>");
        messagePanel.add(messageLabel, BorderLayout.CENTER);
        mainPanel.add(messagePanel);

        // Add an empty panel for spacing
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(100, 20));
        mainPanel.add(emptyPanel);

        // Initialize the user input panel but keep it hidden initially
        userInputPanel = createUserInputPanel();
        userInputPanel.setVisible(false);
        mainPanel.add(userInputPanel, BorderLayout.CENTER);

        // Add proceed button below the text
        proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the user input panel when clicked
                userInputPanel.setVisible(true);
                // Disable the proceed button
                proceedButton.setEnabled(false);
            }
        });
        mainPanel.add(proceedButton, BorderLayout.SOUTH);

        add(mainPanel);
        
        // Initialize KnapsackNavigatorUI
        navigatorUI = new KnapsackNavigatorUI();
        userInputPanel.add(navigatorUI.getMainPanel());
    }

    // Method to create the user input panel
    private JPanel createUserInputPanel() {
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(new BoxLayout(userInputPanel, BoxLayout.Y_AXIS));
        return userInputPanel;
    }
    
    // Method to get the main panel
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
