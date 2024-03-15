package com.example;

import javax.swing.*;
import java.awt.*;

public class LaunchPad extends JFrame {
    private JPanel mainPanel;
    private JPanel textPanel;
    private JTextField nameField;
    private JLabel hiLabel;
    private GridBagConstraints gbc;

    public LaunchPad() {
        setTitle("AlgoAerie");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(61, 96, 68));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        textPanel = new JPanel(new GridBagLayout());
        Color panelColor = new Color(243, 234, 214);
        textPanel.setBackground(panelColor);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); 

        hiLabel = new JLabel("Hi! Are you new here?");
        hiLabel.setForeground(new Color(0, 0, 0));
        hiLabel.setFont(hiLabel.getFont().deriveFont(60f));
        textPanel.add(hiLabel, gbc);
        textPanel.add(Box.createVerticalStrut(10), gbc);

        JLabel nameLabel = new JLabel("<html><i>May we know your name?</i></html>");
        nameLabel.setForeground(new Color(0, 0, 0));
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.ITALIC, 19f)); 
        gbc.gridy = 1; 
        textPanel.add(nameLabel, gbc); 
        textPanel.add(Box.createVerticalStrut(20), gbc);

        nameField = new JTextField(20);
        nameField.addActionListener(e -> showLaunchPadMessage(nameField.getText())); 
        gbc.gridy = 2; 
        textPanel.add(nameField, gbc); 
        textPanel.add(Box.createVerticalStrut(20), gbc);

        mainPanel.add(textPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void showLaunchPadMessage(String name) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
        JLabel descriptionLabel = new JLabel("<html><div style='text-align:center; width:600px; color:rgb(0,0,0); font-style: italic;'>We are TechTetra, and this is AlgoAerie.<br><br>It is an innovative and dynamic interactive platform, offering a visual journey through intricate algorithmic challenges and their tailored solutions. Seamlessly blending user-friendly graphics with in-depth problem exploration, AlgoAerie illuminates the intricate tapestry of algorithmic problem-solving like never before.</div></html>");
        descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(18f));
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER); 
        textPanel.add(descriptionPanel, createGbc());
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        JPanel additionalTextPanel = new JPanel(new GridBagLayout());
        additionalTextPanel.setOpaque(false); 
        JLabel additionalTextLabel = new JLabel("<html><span style='background-color: #70823E; font-style: italic;'>Are you ready to unveil algorithms and craft solutions?</span></html>");
        additionalTextLabel.setForeground(new Color(255, 255, 255));
        additionalTextLabel.setFont(additionalTextLabel.getFont().deriveFont(18f));
        additionalTextPanel.add(additionalTextLabel, createGbc());
        textPanel.add(additionalTextPanel, createGbc());
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        RoundedButtonPanel enterButton = new RoundedButtonPanel("I am!");

        enterButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                DisplayPane displayPane = new DisplayPane();
                displayPane.setLocationRelativeTo(null);
                displayPane.setVisible(true);
            });

            dispose();
        });

    
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(243, 234, 214));
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
            LaunchPad gui = new LaunchPad();
            gui.setLocationRelativeTo(null);
            gui.setVisible(true);
        });
    }
}
