package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectionSort extends JPanel implements ActionListener {
    private JLabel chooseLabel;
    private JComboBox<String> choicesDropdown;
    private JButton proceedButton, submitButton;
    private JPanel outputPanel;
    private JTextArea algorithmOutput;

    public SelectionSort() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(980, 700));

        add(mainPanel(), BorderLayout.CENTER);
    }

    public JPanel mainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(243, 234, 214));

        panel.add(header());
        panel.add(content());

        return panel;
    }

    public JPanel getMaiPanel() {
        return mainPanel();
    }

    public JPanel header() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(112, 130, 62));
        panel.setBounds(0, 0, 980, 45);

        JLabel title = new JLabel("Selection Sort Problem");
        title.setBounds(0, 0, 980, 45);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title);

        return panel;
    }

    public JPanel content() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(243, 234, 214));
        panel.setBounds(0, 40, 980, 655);

        String htmlContent = "<html>Sort the items according to the following criteria:<br><br>"
        + "<table border='1' style='margin: 0 auto;'>" +
        "<tr><td>Product Name</td><td>Weight per Unit (kg)</td><td>Quantity Available</td></tr>" +
        "<tr><td>Canned Goods</td><td>5</td><td>450</td></tr>" +
        "<tr><td>Cooking Oil</td><td>3</td><td>725</td></tr>" +
        "<tr><td>Noodles</td><td>2.5</td><td>375</td></tr>" +
        "<tr><td>Soap</td><td>7</td><td>500</td></tr>" +
        "</table><br>"
        + "a. Product Name<br>"
        + "b. Weight<br>"
        + "c. Amount<br><br>"
        + "Provide details on how the sorting process should be illustrated.</html>";

        JLabel content = new JLabel(htmlContent);
        content.setBounds(40, 80, 900, 640);
        content.setVerticalAlignment(JLabel.TOP);
        content.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(content);

        proceedButton = new JButton("Proceed");
        proceedButton.setBounds(440, 520, 100, 30);
        proceedButton.addActionListener(this);

        panel.add(proceedButton);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proceedButton) {
            displayOutputPanel();
        }
    }

    public void displayOutputPanel() {
        outputPanel = new JPanel(null);
        outputPanel.setBackground(new Color(243, 234, 214));
        outputPanel.setPreferredSize(new Dimension(980, 680));

        outputPanel.add(newHeader());
        outputPanel.add(userPanel());
        outputPanel.add(algorithmPanel());

        removeAll();
        add(outputPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public JPanel newHeader() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(112, 130, 62));
        panel.setBounds(0, 0, 980, 45);

        JLabel title = new JLabel("Selection Sort Solution");
        title.setBounds(0, 0, 980, 45);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title);

        return panel;
    }

    public JPanel userPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(243, 234, 214));
        panel.setBounds(0, 0, 980, 100);

        //Label
        chooseLabel = new JLabel("Choose your sorting method:");
        chooseLabel.setBounds(210, 60, 300, 20);
        panel.add(chooseLabel);

        //Dropdown
        String[] choices = {"Select choice", "a. Product Name", "b. Weight", "c. Amount"};
        choicesDropdown = new JComboBox<>(choices);
        choicesDropdown.setBounds(380, 60, 200, 20);
        panel.add(choicesDropdown);

        //Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(590, 60, 100, 20);
        submitButton.addActionListener(this::actionPerformedSubmit);
        panel.add(submitButton);

        return panel;
    }

    public JPanel algorithmPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(250, 223, 163));
        panel.setBounds(0, 0, 980, 640);

        algorithmOutput = new JTextArea();
        algorithmOutput.setBounds(40, 130, 880, 400);
        algorithmOutput.setEditable(false);
        algorithmOutput.setBackground(new Color(250, 223, 163));
        panel.add(algorithmOutput);

        return panel;
    }

    public void actionPerformedSubmit(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String selectedChoice = (String) choicesDropdown.getSelectedItem();

            if (selectedChoice.equals("Select choice")) {
                JOptionPane optionPane = new JOptionPane("Please select your option.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setLocation(700, 370);
                dialog.setVisible(true);
                return;
            }

            String message = "This is where your output goes.";

            algorithmOutput.setText(message);
            algorithmOutput.setFont(new Font("Arial", Font.PLAIN, 16));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SelectionSort().setVisible(true);
        });
    }
    
}
