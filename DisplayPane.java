package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPane extends JFrame {

    private JPanel outputPanel;
    private KnapsackNavigatorUI knapsackUI; 

    public DisplayPane() {
        initComponents();
        knapsackUI = new KnapsackNavigatorUI(); 
    }

    private void initComponents() {
        setTitle("AlgoAerie");
        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(10, 20, 30));
        setContentPane(mainPanel);

        JLabel problemOutputLabel = new JLabel("Problem Output");
        problemOutputLabel.setFont(new Font("Arial", Font.BOLD, 20));
        problemOutputLabel.setForeground(Color.WHITE);
        problemOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(problemOutputLabel, BorderLayout.NORTH);

        outputPanel = new JPanel();
        outputPanel.setBackground(new Color(255, 255, 225));
        outputPanel.setLayout(new BorderLayout());
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        JPanel sidePanel = createSideBar(new String[]{"Knapsack", "Selection Sort", "Travelling Salesman Problem", "String Matching", "EXIT!!!"});
        sidePanel.setPreferredSize(new Dimension(300, 720));
        mainPanel.add(sidePanel, BorderLayout.WEST);
    }

    private JPanel createSideBar(String[] values) {
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        sideBarPanel.setBackground(new Color(50, 50, 50));

        for (String value : values) {
            RoundedButtonPanel buttonPanel = new RoundedButtonPanel(value);
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(value);
                }
            });
            sideBarPanel.add(buttonPanel);
            sideBarPanel.add(Box.createVerticalStrut(10)); 
        }

        return sideBarPanel;
    }

    private void handleButtonClick(String buttonText) {
        outputPanel.removeAll();
        if (buttonText.equals("Knapsack")) {
            showInformationMessage();
        } else if (buttonText.equals("EXIT!!!")) {
            JOptionPane.showMessageDialog(this, "Exiting...");
            System.exit(0);
        } else {
            outputPanel.add(new JLabel("Output for: " + buttonText), BorderLayout.CENTER);
            outputPanel.revalidate();
            outputPanel.repaint();
        }
    }

    private void showInformationMessage() {
        String message = "<html><div style='text-align: center;'><font face='Tahoma' size='20' color='black'><b>KNAPSACK:</b></font><br>" +
                "Identify the compatible cargo for the vehicle, adhering to strict weight restrictions. The vehicle can accommodate loads ranging from a minimum of 1 kilogram to a maximum of 15 kilograms.<br>" +
                "<br>" +
                "<table border='1'>" +
                "<tr><td>Product Name</td><td>Weight per Unit (kg)</td><td>Quantity Available</td></tr>" +
                "<tr><td>Canned Goods</td><td>5</td><td>450</td></tr>" +
                "<tr><td>Cooking Oil</td><td>3</td><td>725</td></tr>" +
                "<tr><td>Noodles</td><td>2.5</td><td>375</td></tr>" +
                "<tr><td>Soap</td><td>7</td><td>500</td></tr>" +
                "</table></div></html>";
    
        Rectangle outputBounds = outputPanel.getBounds();
    
        JDialog dialog = new JDialog(this, "Information", true);
        dialog.setSize(980, 665);
    
        int dialogX = outputBounds.x + 320; 
        int dialogY = outputBounds.y + 210; 
        dialog.setLocation(dialogX, dialogY);
    
        JLabel messageLabel = new JLabel(message);
        messageLabel.setBounds(10, 10, 960, 500);
    
        RoundedButtonPanel proceedButton = new RoundedButtonPanel("Proceed");
        proceedButton.setBounds(410, 520, 160, 40); 
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); 
                outputPanel.removeAll();
                outputPanel.add(knapsackUI.getMainPanel(), BorderLayout.CENTER);
                outputPanel.revalidate();
                outputPanel.repaint();
            }
        });
    
        dialog.setLayout(null); 
        dialog.add(messageLabel);
        dialog.add(proceedButton);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DisplayPane().setVisible(true);
        });
    }
}
