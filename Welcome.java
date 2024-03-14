package com.example;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JFrame {
    private JPanel mainPanel;
    private JPanel textPanel;
    private JTextField nameField;
    private JLabel hiLabel;
    private GridBagConstraints gbc;

    public Welcome() {
        setTitle("AlgoAerie");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(10, 20, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        textPanel = new JPanel(new GridBagLayout());
        Color panelColor = new Color(255, 255, 225);
        textPanel.setBackground(panelColor);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); 
        hiLabel = new JLabel("Hi, are you new here?");
        hiLabel.setFont(hiLabel.getFont().deriveFont(45f)); 
        textPanel.add(hiLabel, createGbc()); 
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        gbc.gridy = 1;
        nameField = new JTextField(20);
        nameField.addActionListener(e -> showWelcomeMessage(nameField.getText())); 
        textPanel.add(nameField, createGbc()); 
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        mainPanel.add(textPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void showWelcomeMessage(String name) {
        String[] words = name.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
        }
        String capitalizedString = capitalized.toString().trim();
    
        textPanel.removeAll();
        hiLabel.setText("Welcome, " + capitalizedString + "!"); 
        textPanel.add(hiLabel, createGbc()); 
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false); 
        JLabel descriptionLabel = new JLabel("<html><div style='text-align:center; width:600px; color:rgb(0,0,0);'>We are TechTetra, and this is AlgoAerie.<br><br>It is an innovative and dynamic interactive platform, offering a visual journey through intricate algorithmic challenges and their tailored solutions. Seamlessly blending user-friendly graphics with in-depth problem exploration, AlgoAerie illuminates the intricate tapestry of algorithmic problem-solving like never before.</div></html>");
        descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(18f)); 
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER); 
        textPanel.add(descriptionPanel, createGbc()); 
        textPanel.add(Box.createVerticalStrut(20), createGbc());
    
        JPanel additionalTextPanel = new JPanel(new GridBagLayout());
        additionalTextPanel.setOpaque(false); 
        JLabel additionalTextLabel = new JLabel("<html><span style='background-color: #AB875F; font-style: italic;'>Are you ready to unveil algorithms and craft solutions?</span></html>");
        additionalTextLabel.setFont(additionalTextLabel.getFont().deriveFont(18f)); 
        additionalTextPanel.add(additionalTextLabel, createGbc());
        textPanel.add(additionalTextPanel, createGbc()); 
        textPanel.add(Box.createVerticalStrut(20), createGbc());
    
        RoundedButtonPanel enterButton = new RoundedButtonPanel("Let's Go!");

        enterButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                DisplayPane displayPane = new DisplayPane();
                displayPane.setLocationRelativeTo(null);
                displayPane.setVisible(true);
            });

            dispose();
        });

    
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(255, 255, 225));
        bottomPanel.add(enterButton);
    
        textPanel.add(bottomPanel, createGbc()); 
    
        mainPanel.revalidate(); 
    }
    

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0); 
        return gbc;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Welcome gui = new Welcome();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}
