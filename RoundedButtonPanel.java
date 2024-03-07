package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoundedButtonPanel extends JPanel {

    private boolean hovered = false;
    private String buttonText;

    public RoundedButtonPanel(String buttonText) {
        this.buttonText = buttonText;
        setPreferredSize(new Dimension(290, 35));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Draw curved background
        if (hovered) {
            g2d.setColor(new Color(255, 135, 0));
        } else {
            g2d.setColor(new Color(50, 50, 50));
        }
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Draw text
        g2d.setColor(Color.WHITE);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(buttonText);
        int textHeight = fm.getHeight();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() - textHeight) / 2 + fm.getAscent();
        g2d.drawString(buttonText, x, y);

        g2d.dispose();
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            hovered = true;
            repaint();
        } else if (e.getID() == MouseEvent.MOUSE_EXITED) {
            hovered = false;
            repaint();
        }
    }
}

