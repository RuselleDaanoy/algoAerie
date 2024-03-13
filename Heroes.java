package com.example;

import javax.swing.*;
import java.awt.*;

public class Heroes extends JFrame {
    private JPanel mainPanel;
    private JPanel borderPanel;

    public Heroes() {
        setTitle("AlgoAerie");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); 
        borderPanel.setBackground(new Color(10, 20, 30));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setBackground(new Color(255, 255, 225)); 

        displayHeroSelection(); 

        borderPanel.add(mainPanel, BorderLayout.CENTER);

        add(borderPanel);
    }

    private void displayHeroSelection() {

        JLabel chooseLabel = new JLabel("Choose your hero!");
        chooseLabel.setFont(new Font("Tahoma", Font.BOLD, 32)); 
        chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(chooseLabel, BorderLayout.NORTH);

        JPanel heroPanel = new JPanel(new GridLayout(2, 2, 5, 5)); 
        heroPanel.setBackground(new Color(255, 255, 225)); 

        String[] heroNames = {"Knapsack", "Selection Sort", "Travelling Salesman Problem", "String Matching"};
        for (String heroName : heroNames) {
            RoundedButtonPanel heroButton = new RoundedButtonPanel(heroName); 
            heroButton.setFont(new Font("Arial", Font.PLAIN, 20)); 

            heroPanel.add(heroButton);
        }

        mainPanel.add(heroPanel, BorderLayout.CENTER);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.setBackground(new Color(255, 255, 225));

        RoundedButtonPanel exitButton = new RoundedButtonPanel("EXIT!!!");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setPreferredSize(new Dimension(600, 140));

        exitPanel.add(exitButton);

        mainPanel.add(exitPanel, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Heroes heroes = new Heroes();
            heroes.setLocationRelativeTo(null);
            heroes.setVisible(true);
        });
    }
}
