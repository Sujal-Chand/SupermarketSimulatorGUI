/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */


import com.pdcgame.GameState;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

public class StoreStatusPanel extends JPanel{

    private JLabel balanceLabel;
    private JLabel actionsLabel;
    private JLabel dayLabel;

    public StoreStatusPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 213, 171));
        setPreferredSize(new Dimension(600, 100));

        // Store rating display
        StoreRatingPanel ratingDisplay = new StoreRatingPanel();
        JPanel ratingPanel = ratingDisplay.getRatingPanel(GameState.instance().getRating());
        ratingPanel.setOpaque(false);

        // Panel to hold stats
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        statsPanel.setOpaque(false);

        Font statsFont = new Font("Courier New", Font.BOLD, 24);
        Color statsColour = new Color(66, 62, 55);
        Border statBorder = BorderFactory.createLineBorder(new Color(66, 62, 55), 2);
        Insets padding = new Insets(10, 15, 10, 15);

        // Create and store labels
        balanceLabel = new JLabel();
        actionsLabel = new JLabel();
        dayLabel = new JLabel();

        // Add stat boxes with these labels
        statsPanel.add(createStatBox(balanceLabel, statsFont, statsColour, statBorder, padding));
        statsPanel.add(createStatBox(actionsLabel, statsFont, statsColour, statBorder, padding));
        statsPanel.add(createStatBox(dayLabel, statsFont, statsColour, statBorder, padding));

        // Initial label values
        refresh();

        add(statsPanel, BorderLayout.WEST);
        add(ratingPanel, BorderLayout.EAST);
    }

    private JPanel createStatBox(JLabel label, Font font, Color textColor, Border border, Insets padding) {
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

    public void refresh() {
        GameState game = GameState.instance();

        balanceLabel.setText("Balance: $" + String.format("%.2f", game.getBalance()));
        actionsLabel.setText("Actions Left: " + game.getActions() + "/" + game.getTotalActions());
        dayLabel.setText("Days Passed: " + game.day);

        revalidate();
        repaint();
    }
    
}
