package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StringMatchingUI extends JPanel implements ActionListener {
    private JButton proceedButton;
    private JPanel outputPanel;
    private JPanel addressObtain;
    private JPanel outputMatch;

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

        addressObtain = new JPanel();
        addressObtain.setBackground(Color.GREEN);
        addressObtain.setBounds(0, 45, 980, 280);
        outputPanel.add(addressObtain);

        outputMatch = new JPanel();
        outputMatch.setBackground(Color.YELLOW);
        outputMatch.setBounds(0, 295, 980, 380);
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

        JLabel title = new JLabel("String Matching Solution");
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
