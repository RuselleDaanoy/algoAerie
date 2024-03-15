package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPane extends JFrame {

    private JPanel outputPanel;
    private KnapsackNavigatorUI knapsackUI; 
    private SelectionSort sorting;
    private tspUI tspUI;
    private StringMatchingUI stringMatchingUI;

    public DisplayPane() {
        initComponents();
        knapsackUI = new KnapsackNavigatorUI(); 
        sorting = new SelectionSort();
        tspUI = new tspUI();
        stringMatchingUI = new StringMatchingUI();
    }

    private void initComponents() {
        setTitle("AlgoAerie");
        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(61, 96, 68));
        setContentPane(mainPanel);
    
        JLabel problemOutputLabel = new JLabel("Crafting Pane");
        problemOutputLabel.setFont(new Font("Arial", Font.BOLD, 20));
        problemOutputLabel.setForeground(Color.WHITE);
        problemOutputLabel.setHorizontalAlignment(SwingConstants.CENTER);

        problemOutputLabel.setPreferredSize(new Dimension(1280, 50)); 
        mainPanel.add(problemOutputLabel, BorderLayout.NORTH);

        outputPanel = new JPanel();
        outputPanel.setBackground(new Color(243, 234, 214));
        outputPanel.setLayout(new BorderLayout());
        mainPanel.add(outputPanel, BorderLayout.CENTER);

        JPanel sidePanel = createSideBar(new String[]{"Knapsack", "Selection Sort", "Travelling Salesman Problem", "String Matching", "EXIT!!!"});
        sidePanel.setPreferredSize(new Dimension(300, 720));
        mainPanel.add(sidePanel, BorderLayout.WEST);
    }

    private JPanel createSideBar(String[] values) {
        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(null); 
        sideBarPanel.setBackground(new Color(221, 173, 71));
    
        int buttonWidth = 260; 
        int buttonHeight = 100; 
        int verticalGap = 20; 
        int startY = 25;
    
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            RoundedButtonPanel buttonPanel = new RoundedButtonPanel(value);
            int panelX = 20; 
            int panelY = startY + i * (buttonHeight + verticalGap);
            buttonPanel.setBounds(panelX, panelY, buttonWidth, buttonHeight);
            buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleButtonClick(value);
                }
            });
            sideBarPanel.add(buttonPanel);
        }

        int preferredHeight = startY + values.length * buttonHeight + (values.length - 1) * verticalGap;
        sideBarPanel.setPreferredSize(new Dimension(buttonWidth + 40, preferredHeight)); 
        return sideBarPanel;
    }

    private void handleButtonClick(String buttonText) {
        outputPanel.removeAll();
        if (buttonText.equals("Knapsack")) {
            knapsackUI = new KnapsackNavigatorUI(); 
            outputPanel.add(knapsackUI.getMainPanel(), BorderLayout.CENTER);
        } else if (buttonText.equals("Selection Sort")) {
            sorting = new SelectionSort();
            outputPanel.add(sorting, BorderLayout.CENTER);
            outputPanel.revalidate();
            outputPanel.repaint();
        } else if (buttonText.equals("Travelling Salesman Problem")){
            tspUI = new tspUI();
            outputPanel.add(tspUI, BorderLayout.CENTER);
            outputPanel.revalidate();
            outputPanel.repaint();
        } else if (buttonText.equals("String Matching")) { 
            stringMatchingUI = new StringMatchingUI();
            outputPanel.add(stringMatchingUI, BorderLayout.CENTER);
            outputPanel.revalidate();
            outputPanel.repaint();
        } else if (buttonText.equals("EXIT!!!")) {
            String message = "<html><body style='font-family: Arial, sans-serif; font-size: 10px;'>";
            message += "<div style='border: 1px solid #ccc; padding: 5px;'>";
            message += "<i>As you depart, introducing the visionary minds behind AlgoArie's innovative GUI, TechTetra proudly presents:</i><br/>";
            message += "<ul style='list-style-type: none; padding-left: 0; margin-top: 3px;'>";
            message += "<br><li><i>Faye Camille Buri</i></li>";
            message += "<li><i>Louise Andrea Tatoy</i></li>";
            message += "<li><i>Ruselle Daanoy</i></li>";
            message += "<li><i>Royal Jones Go</i></li>";
            message += "</ul>";
            message += "</div>";
            message += "</body></html>";

            JDialog dialog = new JDialog();
            dialog.setTitle("Good bye!");
            JLabel label = new JLabel(message);
            dialog.add(label);

            JButton goodbyeButton = new JButton("Good Bye!");
            goodbyeButton.setMaximumSize(new Dimension(100, 30)); 
            goodbyeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose(); 
                    System.exit(0);
                }
            });

            dialog.add(goodbyeButton, BorderLayout.SOUTH);

            dialog.setSize(500, 200);
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        } else {
            outputPanel.add(new JLabel("Output for: " + buttonText), BorderLayout.CENTER);
        }
        outputPanel.revalidate();
        outputPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DisplayPane().setVisible(true);
        });
    }
}
