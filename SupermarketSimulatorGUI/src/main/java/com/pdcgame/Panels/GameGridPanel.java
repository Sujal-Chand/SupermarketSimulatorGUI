/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGridPanel extends JPanel{
    
    public GameGridPanel() {
        setLayout(new GridBagLayout()); 
        setOpaque(false);
        setPreferredSize(new Dimension(400, 400)); 
        JPanel gridPanel = new JPanel(new GridLayout(10, 10, 2, 2));
        gridPanel.setOpaque(false);
        gridPanel.setPreferredSize(new Dimension(350, 350)); 

        Dimension labelSize = new Dimension(80, 80);
        Color labelColor = new Color(138, 129, 124);
        Color textColor = new Color(244, 243, 238);

        for (int i = 1; i <= 100; i++) {
            int x = (i - 1) % 10;
            int y = 9 - (i - 1) / 10;

            JLabel label = new JLabel("S", SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(labelColor);
            label.setForeground(textColor);
            label.setPreferredSize(labelSize);
            label.setFont(new Font("Dialog", Font.BOLD, 10));
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            int finalX = x;
            int finalY = y;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    label.setBackground(labelColor.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    label.setBackground(labelColor);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Label x,y = " + finalX + ", " + finalY + " was clicked!");
                }
            });

            gridPanel.add(label);
        }

        add(gridPanel, new GridBagConstraints());
    }
}
