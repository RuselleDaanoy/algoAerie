package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterActiveApp extends JFrame {

    private JPanel outputPanel;
    private JPanel mainPanel;

    public InterActiveApp() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Purin Bar");
        setSize(new Dimension(1280, 720));

        setIconImage(new ImageIcon("C:\\Users\\rusel\\Downloads\\pompompurin.jpg").getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize mainPanel
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(10, 20, 30));
        setContentPane(mainPanel);

        // Create output panel
        outputPanel = new JPanel();
        outputPanel.setBounds(320, 50, 935, 620);
        outputPanel.setBackground(new Color(255, 255, 225));
        outputPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
        mainPanel.add(outputPanel);

        // Add components to main panel
        mainPanel.add(createAppBar());
        mainPanel.add(createSideBar(new String[]{"PURINKnapSack", "POMPOMSelection", "POMPOMCustomerDelivery", "PURINStringMatching", "POMPOMPURINEXIT"}));
    }

    private JPanel createAppBar() {
        JPanel appBarPanel = new JPanel(null);
        appBarPanel.setBackground(new Color(222, 222, 222));
        appBarPanel.setBounds(0, 0, 1280, 45);

        JLabel titleLabel = new JLabel("Pompompurin Application Bar");
        JLabel appLogoLabel = new JLabel(Defaults.getResizeImage("C:\\Users\\rusel\\Downloads\\images.jpg",35,35));

        appLogoLabel.setBounds(0, 0, 45, 45);
        titleLabel.setBounds(50, 0, 480, 45);

        // Add components to app bar panel
        appBarPanel.add(appLogoLabel);
        appBarPanel.add(titleLabel);

        return appBarPanel;
    }

    private JPanel createSideBar(String[] values) {
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBounds(10, 50, 300, 620);
        sideBarPanel.setBackground(new Color(50, 50, 50));

        for (String value : values) {
            RoundedButtonPanel buttonPanel = new RoundedButtonPanel(value);
            buttonPanel.addMouseListener(new ButtonMouseListener(value));
            sideBarPanel.add(buttonPanel);
        }

        return sideBarPanel;
    }

    private class ButtonMouseListener implements MouseListener {
        private String buttonText;

        public ButtonMouseListener(String buttonText) {
            this.buttonText = buttonText;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Update output panel content based on the clicked button
            outputPanel.removeAll();
            if (buttonText.equals("PURINKnapSack")) {
                KnapsackNavigatorUI knapsackUI = new KnapsackNavigatorUI();
                JPanel knapsackPanel = knapsackUI.getMainPanel();
                // Resize the knapsackPanel to fit the outputPanel
                knapsackPanel.setPreferredSize(outputPanel.getSize());
                outputPanel.add(knapsackPanel, BorderLayout.CENTER);
            } else if (buttonText.equals("POMPOMPURINEXIT")) {
                // Display names before exiting
                JOptionPane.showMessageDialog(InterActiveApp.this, "Pompompurin\nPochaco\nKurumi\nMy Melody\nCinamoroll", "Characters", JOptionPane.INFORMATION_MESSAGE);
                // Close the window
                dispatchEvent(new WindowEvent(InterActiveApp.this, WindowEvent.WINDOW_CLOSING));
            } else if (buttonText.equals("POMPOMSelection")) {
                
            } else {
                JLabel outputLabel = new JLabel("Output for: " + buttonText);
                outputPanel.add(outputLabel, BorderLayout.CENTER);
            }
            outputPanel.revalidate();
            outputPanel.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterActiveApp().setVisible(true);
        });
    }
}
