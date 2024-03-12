package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayPane extends JFrame {

    private JPanel outputPanel;
    private JPanel mainPanel;

    public DisplayPane() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Chip | Display Panel");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(10, 20, 30));
        setContentPane(mainPanel);

        outputPanel = new JPanel();
        outputPanel.setBounds(320, 50, 935, 620);
        outputPanel.setBackground(new Color(255, 255, 225));
        outputPanel.setLayout(new BorderLayout()); // Set layout to BorderLayout
        mainPanel.add(outputPanel);

        mainPanel.add(createAppBar());
        mainPanel.add(createSideBar(new String[]{"Problem Details", "Knapsack", "Selection Sort", "Travelling Salesman", "String Matching", "EXIT"}));
    }

    private JPanel createAppBar() {
        JPanel appBarPanel = new JPanel(null);
        appBarPanel.setBackground(new Color(50, 50, 50));
        appBarPanel.setBounds(0, 0, 1280, 45);
    
        JLabel titleLabel = new JLabel("Output");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        // x label horizontally
        int xCoordinate = (1280 - titleLabel.getPreferredSize().width) / 2;
        titleLabel.setBounds(xCoordinate, 0, titleLabel.getPreferredSize().width, 45);

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

    public void setKnapsackUI() {
        // Replace with the Knapsack Navigator UI
        KnapsackNavigatorUI knapsackUI = new KnapsackNavigatorUI();
        outputPanel.add(knapsackUI.getMainPanel(), BorderLayout.CENTER);
        outputPanel.revalidate();
        outputPanel.repaint();
    }

    private class ButtonMouseListener implements MouseListener {
        private String buttonText;
    
        public ButtonMouseListener(String buttonText) {
            this.buttonText = buttonText;
        }
    
        @Override
        public void mouseClicked(MouseEvent e) {
            if (buttonText.equals("Problem Details")) {
                Orient orient = new Orient();
                orient.setLocationRelativeTo(null);
                orient.setVisible(true);
                setVisible(false); // Hide the DisplayPane frame
            } else if (buttonText.equals("Knapsack")) {
                setKnapsackUI(); // Initialize the Knapsack UI
            }else if (buttonText.equals("EXIT")) {
                // Exit the application
                dispatchEvent(new WindowEvent(DisplayPane.this, WindowEvent.WINDOW_CLOSING));
            } else {
                // pra sa other buttons as displaying a placeholder label
                JLabel outputLabel = new JLabel("Output for: " + buttonText);
                outputPanel.add(outputLabel, BorderLayout.CENTER);
                outputPanel.revalidate();
                outputPanel.repaint();
            }
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
            new DisplayPane().setVisible(true);
        });
    }
}
