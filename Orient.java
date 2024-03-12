package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Orient extends JFrame {
    private JPanel mainPanel;
    private JPanel borderPanel;

    public Orient() {
        setTitle("Chip | Problem Orientation");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        borderPanel = new JPanel(new BorderLayout());
        borderPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        borderPanel.setBackground(new Color(10, 20, 30));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); 
        mainPanel.setBackground(new Color(255, 255, 225));

        JLabel titleLabel = new JLabel("Problem Orientation");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // 4 panels and spacing
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10)); 
        topPanel.setBackground(new Color(255, 255, 225)); 

        // labels na nag hohold ng 4 text
        JLabel label1 = createGrayLabel("The Knapsack Problem", Color.BLACK);
        JLabel label2 = createGrayLabel("Selection Sorting", Color.BLACK);
        JLabel label3 = createGrayLabel("Travelling Salesman Problem", Color.BLACK);
        JLabel label4 = createGrayLabel("String Matching for Address", Color.BLACK);

        topPanel.add(label1);
        topPanel.add(label2);
        topPanel.add(label3);
        topPanel.add(label4);

        mainPanel.add(topPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout()); 
        bottomPanel.setBackground(new Color(255, 255, 225)); 

        // Create and add JLabel for the fifth panel
        JLabel label5 = createGrayLabel("pompompurinexit", Color.BLACK);
        label5.setPreferredSize(new Dimension(1200, 150)); 
        bottomPanel.add(label5, BorderLayout.CENTER);

        // empty panel para lang magkaspace
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(1200, 10)); // adjust the height para sa space
        emptyPanel.setBackground(new Color(255, 255, 225)); // para parehas ng kulay sa main panel
        bottomPanel.add(emptyPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(255, 255, 225));

        RoundedButtonPanel closeButton = new RoundedButtonPanel("Let's Go!"); // use RoundedButtonPanel instead of JButton
        closeButton.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayHeroSelection(); // call method to display hero selection
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

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        borderPanel.add(mainPanel, BorderLayout.CENTER);

        add(borderPanel);
    }

    // method para iclear yung main panel and display hero selection
    private void displayHeroSelection() {
        clearMainPanel();

        JPanel heroPanel = new JPanel(new GridLayout(2, 2, 5, 5)); // use GridLayout to arrange buttons in a grid
        heroPanel.setBackground(new Color(255, 255, 225)); 

        String[] heroNames = {"Knapsack", "Selection Sort", "Travelling Salesman", "String Matching"};
        for (String heroName : heroNames) {
            RoundedButtonPanel heroButton = new RoundedButtonPanel(heroName); // use RoundedButtonPanel instead of JButton
            heroButton.setFont(new Font("Arial", Font.PLAIN, 20)); 
            heroButton.setActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (heroName.equals("Knapsack")) {
                        // magoopen yung display pane nang nagrurun na yung knapsack
                        DisplayPane displayPane = new DisplayPane();
                        displayPane.setKnapsackUI(); 
                        displayPane.setVisible(true);
                        dispose(); 
                    }
                }
            });
            heroPanel.add(heroButton); 
        }

        mainPanel.add(heroPanel, BorderLayout.CENTER);

        // cr8 label for "Choose your hero!"
        JLabel chooseLabel = new JLabel("Choose your hero!");
        chooseLabel.setFont(new Font("Tahoma", Font.BOLD, 32)); 
        chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(chooseLabel, BorderLayout.NORTH);

        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.setBackground(new Color(255, 255, 225)); 

        RoundedButtonPanel exitButton = new RoundedButtonPanel("EXIT!!!"); // use RoundedButtonPanel instead of JButton
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setPreferredSize(new Dimension(600, 140));
        exitButton.setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle exit action
            }
        });
        
        exitPanel.add(exitButton);

        mainPanel.add(exitPanel, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void clearMainPanel() {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JLabel createGrayLabel(String text, Color textColor) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
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
