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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fireActionPerformed();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public String getButtonText() {
        return buttonText;
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

    public void addActionListener(ActionListener listener) {
        listenerList.add(ActionListener.class, listener);
    }

    public void removeActionListener(ActionListener listener) {
        listenerList.remove(ActionListener.class, listener);
    }

    protected void fireActionPerformed() {
        Object[] listeners = listenerList.getListenerList();
        ActionEvent e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, buttonText);
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ActionListener.class) {
                ((ActionListener) listeners[i + 1]).actionPerformed(e);
            }
        }
    }
}
