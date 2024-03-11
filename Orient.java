package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Orient extends JFrame {
    private JPanel mainPanel;
    private JPanel borderPanel;

    public Orient() {
        setTitle("Problem Orientation");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create borderPanel
        borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        borderPanel.setBackground(new Color(10, 20, 30));

        // Create mainPanel with BorderLayout and additional border
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Add border around mainPanel
        mainPanel.setBackground(new Color(255, 255, 225)); // Set background color

        // Create and add JLabel for "Problem Orientation"
        JLabel titleLabel = new JLabel("Problem Orientation");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH); // Add titleLabel to the top of mainPanel

        // Create JPanel for holding the 4 panels and spacing
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // GridLayout to arrange panels in a grid
        topPanel.setBackground(new Color(255, 255, 225)); // Set background color

        // Create and add JLabels for the text in 4 boxes
        JLabel label1 = createGrayLabel("The Knapsack Problem", Color.BLACK);
        JLabel label2 = createGrayLabel("Selection Sorting", Color.BLACK);
        JLabel label3 = createGrayLabel("Travelling Salesman Problem", Color.BLACK);
        JLabel label4 = createGrayLabel("String Matching for Address", Color.BLACK);

        // Add labels to the topPanel
        topPanel.add(label1);
        topPanel.add(label2);
        topPanel.add(label3);
        topPanel.add(label4);

        // Add topPanel to mainPanel in the CENTER position
        mainPanel.add(topPanel, BorderLayout.CENTER);

        // Create JPanel for holding the fifth panel
        JPanel bottomPanel = new JPanel(new BorderLayout()); // Use BorderLayout to arrange components vertically
        bottomPanel.setBackground(new Color(255, 255, 225)); // Set background color

        // Create and add JLabel for the fifth panel
        JLabel label5 = createGrayLabel("pompompurinexit", Color.BLACK);
        label5.setPreferredSize(new Dimension(1200, 150)); // Maintain the size of panel 5
        bottomPanel.add(label5, BorderLayout.CENTER);

        // empty panel para lang magkaspace
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(1200, 10)); // adjust the height para sa space
        emptyPanel.setBackground(new Color(255, 255, 225)); // para parehas ng kulay sa main panel
        bottomPanel.add(emptyPanel, BorderLayout.NORTH);

        // Create JPanel for holding the close button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(255, 255, 225));

        // Create and add the close button
        RoundedButtonPanel closeButton = new RoundedButtonPanel("Let's Go!"); // Use RoundedButtonPanel instead of JButton
        closeButton.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHeroSelection(); // Call method to display hero selection
            }
        });
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(UIManager.getColor("control"));
            }
        });
        buttonPanel.add(closeButton);

        // Add buttonPanel to bottomPanel in the SOUTH position
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add bottomPanel to mainPanel in the SOUTH position
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add mainPanel to borderPanel
        borderPanel.add(mainPanel, BorderLayout.CENTER);

        // Add borderPanel to JFrame
        add(borderPanel);
    }

    // Method to clear the main panel and display hero selection
    private void displayHeroSelection() {
        clearMainPanel(); // Clear the main panel

        // Create panel for hero selection
        JPanel heroPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // Use GridLayout to arrange buttons in a grid
        heroPanel.setBackground(new Color(255, 255, 225)); // Set background color

        // Create buttons for hero selection
        String[] heroNames = {"Knapsack", "Selection Sort", "Travelling Salesman", "String Matching"};
        for (String heroName : heroNames) {
            RoundedButtonPanel heroButton = new RoundedButtonPanel(heroName); // Use RoundedButtonPanel instead of JButton
            heroButton.setFont(new Font("Arial", Font.PLAIN, 20)); // Set smaller font size
            heroButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    heroButton.setBackground(Color.ORANGE); // Note: This line will not work directly, you'll need to add a method setBackground to RoundedButtonPanel
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    heroButton.setBackground(UIManager.getColor("control")); // Note: This line will not work directly, you'll need to add a method setBackground to RoundedButtonPanel
                }
            });
            heroPanel.add(heroButton); // Add button to panel
        }

        // Add heroPanel to mainPanel
        mainPanel.add(heroPanel, BorderLayout.CENTER);

        // Create label for "Choose your hero!"
        JLabel chooseLabel = new JLabel("Choose your hero!");
        chooseLabel.setFont(new Font("Tahoma", Font.BOLD, 32)); // Adjust font size to match the panel title
        chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add label and hero selection panel to mainPanel
        mainPanel.add(chooseLabel, BorderLayout.NORTH);

        // Create panel for the exit button
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.setBackground(new Color(255, 255, 225)); // Set background color

        // Create and add the exit button
        RoundedButtonPanel exitButton = new RoundedButtonPanel("EXIT!!!"); // Use RoundedButtonPanel instead of JButton
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setPreferredSize(new Dimension(600, 140)); // Set preferred size for the exit button
        exitButton.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle exit action
            }
        });
        exitPanel.add(exitButton);

        // Add exitPanel to mainPanel in the SOUTH position
        mainPanel.add(exitPanel, BorderLayout.SOUTH);


        // Repaint mainPanel
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Helper method to clear the main panel
    private void clearMainPanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Helper method to create gray JLabel with centered text
    private JLabel createGrayLabel(String text, Color textColor) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Orient orient = new Orient();
            orient.setLocationRelativeTo(null);
            orient.setVisible(true);
        });
    }
}
