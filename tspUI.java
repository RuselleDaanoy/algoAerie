package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tspUI extends JFrame implements ActionListener {
    private JLabel addressForm, nameLabel, houseLabel, streetLabel, barangayLabel, municipalityLabel, provinceLabel;
    private JTextField nameField, houseField, streetField, barangayField, municipalityField;
    private JComboBox<String> provinceDropdown;
    private JButton proceedButton, submitButton;
    private JTextArea addressOutput;
    private JPanel outputPanel;

    public tspUI() {
        setTitle("TSP Details");
        setSize(new Dimension(1280, 720));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(mainPanel());
    }

    public JPanel mainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(0xD9D9D9));

        panel.add(header()); //Gray Panel
        panel.add(content());

        return panel;
    }

    public JPanel header() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(0x737373));
        panel.setBounds(0,0,1280, 40);

        JLabel title = new JLabel("Traveling Salesman Problem"); //Header title
        title.setBounds(0,0,1280,40);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title);

        return panel;
    }

    public JPanel content() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(0xD9D9D9));
        panel.setBounds(0, 40, 1280, 680);

        JLabel content = new JLabel("<html>Deliver items to the customer's address from the selected province using the shortest route. Provide customer name and address for delivery initiation. Determine shortest route based on distances between provinces.<br><br>Province Distances (in kilometers):<br><br>"
                                    + "<table border='1' cellpadding='5' cellspacing='0'>"
                                    + "<tr><td></td><td>St. Peter</td><td>St. John</td><td>Lanao</td><td>Maguindanao</td></tr>"
                                    + "<tr><td>St. Peter</td><td>0</td><td>300</td><td>150</td><td>200</td></tr>"
                                    + "<tr><td>St. John</td><td>150</td><td>0</td><td>200</td><td>300</td></tr>"
                                    + "<tr><td>Lanao</td><td>100</td><td>120</td><td>0</td><td>200</td></tr>"
                                    + "<tr><td>Maguindanao</td><td>200</td><td>200</td><td>100</td><td>0</td></tr>"
                                    + "</table></html>");
        content.setBounds(40, 40, 1200, 660);
        content.setVerticalAlignment(JLabel.TOP);
        content.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(content);

        //Proceed button
        proceedButton = new JButton("Proceed");
        proceedButton.setBounds(580, 580, 100, 30);
        proceedButton.addActionListener(this);

        panel.add(proceedButton);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proceedButton) {
            // Proceed to the output panel
            displayOutputPanel();
            updateHeaderTitle("Traveling Salesman Solution");
        }
    }

    public void updateHeaderTitle(String newTitle) {
        // Find the title label and update its text
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] headerComponents = panel.getComponents();
                for (Component headerComponent : headerComponents) {
                    if (headerComponent instanceof JLabel) {
                        JLabel titleLabel = (JLabel) headerComponent;
                        if (titleLabel.getText().equals("Traveling Salesman Problem")) {
                            titleLabel.setText(newTitle);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void displayOutputPanel() {
        // Create output panel
        outputPanel = new JPanel(null);
        outputPanel.setBackground(new Color(0xD9D9D9));
        outputPanel.setBounds(0, 40, 1280, 680);

        //Add panels to the existing output panel
        outputPanel.add(addressForm());
        outputPanel.add(addressOutput());
        outputPanel.add(algorithmOutput());

        // Add output panel to the frame
        getContentPane().removeAll(); // Remove existing content
        getContentPane().add(header()); // Add header
        getContentPane().add(outputPanel); // Add output panel

        // Refresh frame
        revalidate();
        repaint();
    }

    public JPanel addressForm() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(0xD9D9D9));
        panel.setBounds(0, 0, 320, 400);

        //Labels
        addressForm = new JLabel("ADDRESS FORM");
        addressForm.setBounds(20, 60, 100, 20);
        panel.add(addressForm);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(20, 100, 100, 20);
        panel.add(nameLabel);

        houseLabel = new JLabel("House No.:");
        houseLabel.setBounds(20, 140, 100, 20);
        panel.add(houseLabel);

        streetLabel = new JLabel("Street:");
        streetLabel.setBounds(20, 180, 100, 20);
        panel.add(streetLabel);

        barangayLabel = new JLabel("Barangay:");
        barangayLabel.setBounds(20, 220, 100, 20);
        panel.add(barangayLabel);

        municipalityLabel = new JLabel("Municipality:");
        municipalityLabel.setBounds(20, 260, 100, 20);
        panel.add(municipalityLabel);

        provinceLabel = new JLabel("Province:");
        provinceLabel.setBounds(20, 300, 100, 20);
        panel.add(provinceLabel);

        //Text Fields
        nameField = new JTextField();
        nameField.setBounds(100, 100, 200, 20);
        panel.add(nameField);

        houseField = new JTextField();
        houseField.setBounds(100, 140, 200, 20);
        panel.add(houseField);

        streetField = new JTextField();
        streetField.setBounds(100, 180, 200, 20);
        panel.add(streetField);

        barangayField = new JTextField();
        barangayField.setBounds(100, 220, 200, 20);
        panel.add(barangayField);

        municipalityField = new JTextField();
        municipalityField.setBounds(100, 260, 200, 20);
        panel.add(municipalityField);

        //Dropdown Box
        String[] provinces = {"Select province", "St. Peter", "St. John", "Lanao", "Maguindanao"};
        provinceDropdown = new JComboBox<>(provinces);
        provinceDropdown.setBounds(100, 300, 200, 20);
        panel.add(provinceDropdown);

        //Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 340, 100, 30);
        submitButton.addActionListener(this::actionPerformedSubmit);
        panel.add(submitButton);

        return panel;
    }

    public JPanel addressOutput() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(0xD9D9D9));
        panel.setBounds(0, 400, 320, 370);

        addressOutput = new JTextArea();
        addressOutput.setBounds(35, 45, 250, 170);
        addressOutput.setEditable(false);
        addressOutput.setBackground(new Color(0xD9D9D9));
        panel.add(addressOutput);

        return panel;
    }

    public JPanel algorithmOutput() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.BLUE);
        panel.setBounds(320, 0, 960, 680);

        return panel;
    }

    public void actionPerformedSubmit(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String custName = capitalizeFirstLetter(nameField.getText().trim());
            String houseNo = capitalizeFirstLetter(houseField.getText().trim());
            String street = capitalizeFirstLetter(streetField.getText().trim());
            String barangay = capitalizeFirstLetter(barangayField.getText().trim());
            String municipality = capitalizeFirstLetter(municipalityField.getText().trim());
            String selectedProvince = capitalizeFirstLetter((String) provinceDropdown.getSelectedItem());
    
            if (custName.trim().isEmpty() || houseNo.trim().isEmpty() || street.trim().isEmpty() ||
                barangay.trim().isEmpty() || municipality.trim().isEmpty() ||
                selectedProvince == null || selectedProvince.trim().isEmpty()) {
                //Create and display the pop-up message for the "Fill all the fields" case
                JOptionPane optionPane = new JOptionPane("Please fill in all fields.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setLocation(73, 460); //Sets at a specific location
                dialog.setVisible(true);
                return;
            }
    
            if (selectedProvince.equals("Select Province")) {
                //Create and display the pop-up message for "Please select a province" case
                JOptionPane optionPane = new JOptionPane("Please select a province.", JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog("Error");
                dialog.setLocation(73, 460); //Sets at a specific location
                dialog.setVisible(true);
                return;
            }
    
            //Create the message string
            //trim() method is added; excess " " will not be counted
            String message = custName.trim() +"'s address is:\n\n" +
                    "#" + houseNo.trim() + " " + street.trim() + " St., \nBrgy. " +
                    barangay.trim() + ", \n" + municipality.trim() + ", " + selectedProvince;
    
            addressOutput.setText(message);
            addressOutput.setFont(new Font("Arial", Font.BOLD, 14));
        }
    }

    //Method to capitalize the first letter of each word
    private String capitalizeFirstLetter(String str) {
        if (str.isEmpty()) {
            return str;
        }

        // Capitalize the first letter of the string
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        // Capitalize the first letter of each word
        StringBuilder sb = new StringBuilder();
        String[] words = str.split("\\s+");
        for (String word : words) {
            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new tspUI().setVisible(true);
        });
    }
}
