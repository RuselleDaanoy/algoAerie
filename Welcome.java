package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Welcome extends JFrame {
    private JPanel mainPanel;
    private JPanel textPanel;
    private JTextField nameField;
    private JLabel hiLabel;
    private GridBagConstraints gbc;

    public Welcome() {
        setTitle("Chip | Welcome");
        setSize(new Dimension(1280, 720));
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
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical spacing between components

        hiLabel = new JLabel("Hi, are you new here?");
        hiLabel.setFont(hiLabel.getFont().deriveFont(45f)); // Set font size to 45
        textPanel.add(hiLabel, createGbc()); // Use separate instance of GridBagConstraints
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        gbc.gridy = 1;
        nameField = new JTextField(20);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    showWelcomeMessage(name);
                }
            }
        });
        textPanel.add(nameField, createGbc()); // Use separate instance of GridBagConstraints
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        mainPanel.add(textPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void showWelcomeMessage(String name) {
        textPanel.removeAll(); // Remove all components from the textPanel
        hiLabel.setText("Welcome, " + name + "!"); // Change the label text to "Welcome, [name]!"
        textPanel.add(hiLabel, createGbc()); // Add the hiLabel back to the textPanel
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        // Create a panel for the description text
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false); // Make the panel transparent
        JLabel descriptionLabel = new JLabel("<html><p style='width:600px; color:rgb(0,0,0);'>We are [group]. [Window name] is an interactive graphic user interface that showcases specific algorithms problems and its corresponding solution.</p></html>");
        descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(18f)); // Set font size for the description
        descriptionPanel.add(descriptionLabel, BorderLayout.CENTER); // Use JScrollPane for long text
        textPanel.add(descriptionPanel, createGbc()); // Add the descriptionPanel to the textPanel
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        // Create a panel for the additional text
        JPanel additionalTextPanel = new JPanel(new GridBagLayout());
        additionalTextPanel.setOpaque(false); // Make the panel transparent
        JLabel additionalTextLabel = new JLabel("<html><span style='background-color: #AB875F; font-style: italic;'>Are you ready to dive into kineme basta punchline na handa n ba kau?</span></html>");
        additionalTextLabel.setFont(additionalTextLabel.getFont().deriveFont(18f)); // Set font size for the additional text
        additionalTextPanel.add(additionalTextLabel, createGbc()); // Add the additionalTextLabel to the additionalTextPanel
        textPanel.add(additionalTextPanel, createGbc()); // Add the additionalTextPanel to the textPanel
        textPanel.add(Box.createVerticalStrut(20), createGbc());

        // Create a simple "Enter" button
        RoundedButtonPanel enterButton = new RoundedButtonPanel("Let's Go!");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Orient class
                SwingUtilities.invokeLater(() -> {
                    Orient orient = new Orient();
                    orient.setLocationRelativeTo(null);
                    orient.setVisible(true);
                });

            // Close the Welcome window
            dispose();
            
            }
        });
        
        gbc.gridy++; // Increment gridy
        textPanel.add(enterButton, createGbc()); // Add the enterButton to the textPanel

        mainPanel.revalidate(); // Revalidate the mainPanel to reflect the changes
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0); // Add vertical spacing between components
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
