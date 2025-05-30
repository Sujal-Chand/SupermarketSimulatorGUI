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
        rightPanel.setPreferredSize(new Dimension(350, 0));
        rightPanel.setLayout(new GridBagLayout()); // Centers the grid panel

        // Small grid panel
        JPanel gridPanel = new JPanel(new GridLayout(10, 10, 2, 2));
        gridPanel.setOpaque(false);
        
        Dimension buttonSize = new Dimension(50, 50); // Size of each button
        Color buttonColor = new Color(138, 129, 124);
        
        for (int i = 1; i <= 100; i++) {
            int x = (i - 1) % 10;
            int y = (i - 1) / 10;
            JButton button = new JButton("[D F]");
            button.setPreferredSize(buttonSize);
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setFocusable(false);
            button.setBackground(buttonColor);
            button.setOpaque(true);
            button.setBorderPainted(false);
            
            button.setFont(new Font("Arial", Font.BOLD, 11));
            gridPanel.add(button);

            
            button.addActionListener(e -> {
                System.out.println("Button x,y = " + x + ", " + y + " was clicked!");
            });
        }

        gridPanel.setPreferredSize(new Dimension(320, 320)); // Entire grid size

        rightPanel.add(gridPanel); // Centered by GridBagLayout

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
