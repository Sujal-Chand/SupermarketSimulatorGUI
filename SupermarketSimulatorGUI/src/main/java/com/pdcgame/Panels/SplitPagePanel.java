/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.GameState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sujalchand
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class SplitPagePanel extends JPanel {
    public SplitPagePanel(String labelText) {
        setLayout(new BorderLayout());

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(138, 129, 124));
        JLabel leftLabel = new JLabel(labelText + " - Left Side");
        leftLabel.setForeground(new Color(244, 243, 238));
        leftPanel.add(leftLabel);

        // Right Panel (contains grid panel inside it)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(70, 63, 58));
        rightPanel.setPreferredSize(new Dimension(400, 0));
        rightPanel.setLayout(new GridBagLayout()); // Centers the grid panel

        // Small grid panel
        JPanel gridPanel = new JPanel(new GridLayout(10, 10, 2, 2));
        gridPanel.setOpaque(false);

        Dimension labelSize = new Dimension(80, 80); // Size of each label "button"
        Color labelColor = new Color(138, 129, 124);
        Color textColor = new Color(244, 243, 238);

        for (int i = 1; i <= 100; i++) {
            int x = (i - 1) % 10;
            int y = (i - 1) / 10;

            JLabel label = new JLabel("S", SwingConstants.CENTER);
            label.setOpaque(true);
            // resource getting example
            //URL url = getClass().getResource("/stand.png");
            //System.out.println(url);
            label.setBackground(labelColor);
            label.setForeground(textColor);
            label.setPreferredSize(labelSize);
            label.setFont(new Font("Dialog", Font.BOLD, 10));
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            
            // Add hover and click effects
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
                    System.out.println("Label x,y = " + x + ", " + y + " was clicked!");
                }
            });

            gridPanel.add(label);
        }

        gridPanel.setPreferredSize(new Dimension(350, 350)); // Entire grid size
        rightPanel.add(gridPanel); // Centered by GridBagLayout

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}