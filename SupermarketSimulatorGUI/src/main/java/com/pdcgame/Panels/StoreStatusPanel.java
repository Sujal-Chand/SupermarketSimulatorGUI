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
import javax.swing.border.Border;

public class StoreStatusPanel extends JPanel{
    
    public StoreStatusPanel() {
        
        setLayout(new BorderLayout());
        setBackground(new Color(217, 213, 171));
        setPreferredSize(new Dimension(600, 100));

        //store rating display
        StoreRatingPanel ratingDisplay = new StoreRatingPanel();
        JPanel ratingPanel = ratingDisplay.getRatingPanel(5);
        ratingPanel.setOpaque(false);

        //panel to hold stats
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        statsPanel.setOpaque(false);

        Font statsFont = new Font("Courier New", Font.BOLD, 24);
        Color statsColour = new Color(66, 62, 55);
        Border statBorder = BorderFactory.createLineBorder(new Color(66, 62, 55), 2);
        Insets padding = new Insets(10, 15, 10, 15);

        statsPanel.add(createStatBox("Balance: $1500", statsFont, statsColour, statBorder, padding));
        statsPanel.add(createStatBox("Actions Left: 3/3", statsFont, statsColour, statBorder, padding));
        statsPanel.add(createStatBox("Days Passed: 1", statsFont, statsColour, statBorder, padding));

        add(statsPanel, BorderLayout.WEST);
        add(ratingPanel, BorderLayout.EAST);
    }

    private JPanel createStatBox(String text, Font font, Color textColor, Border border, Insets padding) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(textColor);

        JPanel box = new JPanel();
        box.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        box.setOpaque(false);
        box.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right)
        ));
        box.add(label);
        return box;
    }
    
}
