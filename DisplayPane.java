package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayPane extends JFrame {

    private JPanel sidebar; // Sidebar panel
    private boolean sidebarVisible = false;

    public DisplayPane() {
        setTitle("Problem Solutions");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a panel to hold components
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create and customize the title label
        JLabel titleLabel = new JLabel("Problem No");
        titleLabel.setForeground(Color.WHITE); // Set text color to white
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 25)); // Set font and size
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

        // Create the black panel and add the title label to it
        JPanel blackPanel = new JPanel(new BorderLayout());
        blackPanel.setBackground(new Color(10, 20, 30));
        blackPanel.add(titleLabel, BorderLayout.CENTER);

        // Set preferred size for the black panel
        blackPanel.setPreferredSize(new Dimension(1280, 40)); // Set preferred size to 1280x40

        // Load the image and resize it using Defaults class
        ImageIcon icon = Defaults.getResizeImage("C:\\Users\\rusel\\Downloads\\list.png", 35, 35);

        // Create the button with the resized image
        JButton imageButton = new JButton(icon);
        imageButton.setContentAreaFilled(false); // Make the button transparent
        imageButton.setBorderPainted(false); // Remove border

        // Add action listener to the image button
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of the sidebar
                toggleSidebar();
            }
        });

        // Create the sidebar panel
        sidebar = new JPanel(new GridLayout(6, 1, 5, 5)); // 5 rows, 1 column, vertical gap of 10
        sidebar.setBackground(new Color(255,255,225)); // Set background color

        // Add buttons to the sidebar
        String[] buttonNames = { "Problem Details", "Knapsack", "Selection Sort", "Travelling Salesman", "String Matching", "EXIT"};
        for (String name : buttonNames) {
            RoundedButtonPanel button = new RoundedButtonPanel(name);
            button.setBackground(new Color(255, 255, 225)); // Set button background color
            button.setForeground(Color.WHITE); // Set text color to white
            button.setFont(new Font("Tahoma", Font.BOLD, 16)); // Set font
            sidebar.add(button);

            // Add action listener to the rounded button
            button.setActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click action here
                    // For demonstration, we'll just print the button text
                    System.out.println(name);
                }
            });
        }

        // Initially hide the sidebar
        sidebar.setVisible(false);

        // Create the white panel
        JPanel whitePanel = new JPanel(new BorderLayout());
        whitePanel.setBackground(new Color(255,255,225));

        // Create a panel to hold the icon button and sidebar
        JPanel sidebarPanel = new JPanel(new BorderLayout());
        sidebarPanel.setBackground(new Color(255,255,225));

        // Create a panel to hold the icon button
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout to align the icon button to the left
        iconPanel.setBackground(new Color(255,255,225));
        iconPanel.add(imageButton);

        // Add the icon panel to the upper left corner of the sidebar panel
        sidebarPanel.add(iconPanel, BorderLayout.NORTH);
        sidebarPanel.add(sidebar, BorderLayout.CENTER);

        // Add the sidebar panel to the left side of the white panel
        whitePanel.add(sidebarPanel, BorderLayout.WEST);

        // Add the black panel and white panel to the main panel
        mainPanel.add(blackPanel, BorderLayout.NORTH);
        mainPanel.add(whitePanel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(mainPanel);

        setVisible(true); // Make the frame visible
    }

    // Method to toggle the visibility of the sidebar
    private void toggleSidebar() {
        sidebarVisible = !sidebarVisible;
        sidebar.setVisible(sidebarVisible);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DisplayPane::new); // Create and show the GUI on the Event Dispatch Thread
    }
}
