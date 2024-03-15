package com.example;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

public class StringMatchingUI extends JPanel implements ActionListener {
    private JButton proceedButton;
    private JPanel outputPanel;
    private JPanel addressObtain;
    private JPanel outputMatch;
    private JTextArea resultArea;

    public StringMatchingUI() {
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

    public JPanel getMainPanel() {
        return mainPanel();
    }

    public JPanel header() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(112, 130, 62));
        panel.setBounds(0, 0, 980, 45);

        JLabel title = new JLabel("String Matching Problem");
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

        String htmlContent = "<html><div style='text-align: left;'>STRING MATCHING:<br>" +
                "Locate customer addresses within text and display occurrence count and position of each address, disregarding case sensitivity.</div></html>";

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
    
        JPanel headerPanel = newHeader();
        headerPanel.setBounds(0, 0, 980, 45);
        outputPanel.add(headerPanel);
    
        JPanel contentPanel = new JPanel();
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        contentPanel.setBounds(0, 45, 980, 280);
    
        JLabel addressLabel = new JLabel("Customer Address:");
        JTextField addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(400, 30));
    
        JLabel searchLabel = new JLabel("Search Word:");
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
    
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == searchButton) {
                    
                    String address = addressField.getText();
                    String search = searchField.getText();
            
                    // Perform string matching
                    List<Integer> occurrences = new ArrayList<>();
                    String[] addressArray = address.split(", ");
                    for (int i = 0; i < addressArray.length; i++) {
                        if (addressArray[i].toLowerCase().contains(search.toLowerCase())) {
                            occurrences.add(i + 1);
                        }
                    }
            
                    // Display results in algorithmOutput
                    StringBuilder result = new StringBuilder();
                    result.append("You are searching for ").append(search).append(":\n");
                    if (occurrences.isEmpty()) {
                        result.append("No match found.");
                    } else {
                        for (int i = 0; i < occurrences.size(); i++) {
                            result.append((search) + " is found at index ").append(occurrences.get(i)).append(" (").append(addressArray[occurrences.get(i) - 1]).append(") ").append("\n");
                        }
                        result.append("It occured ").append(occurrences.size()).append(" time(s)");
                    }
                    resultArea.setText(result.toString());
                }
            }
            
        });
    
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(addressLabel)
                .addComponent(addressField)
                .addComponent(searchLabel)
                .addComponent(searchField)
                .addComponent(searchButton)
        );
    
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(addressLabel)
                .addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(searchLabel)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(searchButton)
        );
    
        outputPanel.add(contentPanel);
    
        outputMatch = new JPanel(null);
        outputMatch.setBackground(Color.YELLOW);
        outputMatch.setBounds(0, 295, 980, 380);
    
        JLabel resultLabel = new JLabel("Search Results:");
        resultLabel.setBounds(20, 20, 150, 20);
        outputMatch.add(resultLabel);
    
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(20, 50, 940, 300);
        outputMatch.add(scrollPane);
    
        outputPanel.add(outputMatch);
    
        removeAll();
        add(outputPanel, BorderLayout.CENTER);
    
        revalidate();
        repaint();
    }
    

    public JPanel newHeader() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(112, 130, 62));
        panel.setBounds(0, 0, 980, 45);

        JLabel title = new JLabel("STRING MATCHING");
        title.setBounds(0, 0, 980, 45);
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("String Matching UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new StringMatchingUI());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
