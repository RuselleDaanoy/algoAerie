package com.example;

import javax.swing.*;
import java.awt.*;

public class DisplayPane extends JFrame {

    private JPanel outputPanel;
    private JPanel mainPanel;
    private KnapsackOrient knapsackOrient;

    public DisplayPane(KnapsackOrient knapsackOrient) {
        this.knapsackOrient = knapsackOrient;
        initComponents();
    }

    private void initComponents() {
        setTitle("Display Pane");
        setSize(new Dimension(1280, 720));

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize mainPanel
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(10, 20, 30));
        setContentPane(mainPanel);

        // Add label "Problem Output" on top of main panel
        JLabel problemOutputLabel = new JLabel("Problem Output");
        problemOutputLabel.setFont(new Font("Arial", Font.BOLD, 20));
        problemOutputLabel.setForeground(Color.WHITE);
        problemOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        problemOutputLabel.setBounds(0, 10, 1280, 30);
        mainPanel.add(problemOutputLabel);

        // Create output panel
        outputPanel = new JPanel();
        outputPanel.setBounds(320, 50, 935, 620);
        outputPanel.setBackground(new Color(255, 255, 225));
        outputPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
        mainPanel.add(outputPanel);

        // Add components to main panel
        mainPanel.add(createSideBar(new String[]{"Knapsack", "Selection Sort", "Travelling Salesman Problem", "String Matching", "EXIT!!!"}));
    }

    private JPanel createSideBar(String[] values) {
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBounds(10, 50, 300, 620);
        sideBarPanel.setBackground(new Color(50, 50, 50));

        for (String value : values) {
            RoundedButtonPanel buttonPanel = new RoundedButtonPanel(value);
            buttonPanel.addActionListener(e -> {
                String buttonText = ((RoundedButtonPanel) e.getSource()).getButtonText();
                handleButtonClick(buttonText);
            });
            sideBarPanel.add(buttonPanel);
        }

        return sideBarPanel;
    }

    private void handleButtonClick(String buttonText) {
        outputPanel.removeAll();
        if (buttonText.equals("Knapsack")) {
            // Get the main panel of KnapsackOrient
            JPanel knapsackPanel = knapsackOrient.getMainPanel();
            
            // Set the layout of the outputPanel to BorderLayout
            outputPanel.setLayout(new BorderLayout());
            
            // Add the main panel of KnapsackOrient to the outputPanel
            outputPanel.add(knapsackPanel, BorderLayout.CENTER);
            
            // Repaint and revalidate the outputPanel
            outputPanel.revalidate();
            outputPanel.repaint();
        } else if (buttonText.equals("EXIT!!!")) {
            JOptionPane.showMessageDialog(this, "Exiting...");
            System.exit(0);
        } else {
            outputPanel.add(new JLabel("Output for: " + buttonText), BorderLayout.CENTER);
            outputPanel.revalidate();
            outputPanel.repaint();
        }
    }

    public static void main(String[] args) {
        KnapsackOrient knapsackOrient = new KnapsackOrient();
        SwingUtilities.invokeLater(() -> {
            new DisplayPane(knapsackOrient).setVisible(true);
        });
    }
}
