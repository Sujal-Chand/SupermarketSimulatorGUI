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

public class StoreStatusPanel extends JPanel {

    private JLabel balanceLabel; // label for balance display
    private JLabel actionsLabel; // label for actions left display
    private JLabel dayLabel;     // label for days passed display

    public StoreStatusPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(217, 213, 171)); // panel background color
        setPreferredSize(new Dimension(600, 100)); // preferred size

        StoreRatingPanel ratingDisplay = new StoreRatingPanel(); // create rating panel
        JPanel ratingPanel = ratingDisplay.getRatingPanel(GameState.instance().getRating());
        ratingPanel.setOpaque(false); // make rating panel transparent

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // stats container panel
        statsPanel.setOpaque(false); // transparent background

        Font statsFont = new Font("Courier New", Font.BOLD, 24); // font for stats labels
        Color statsColour = new Color(66, 62, 55); // text color
        Border statBorder = BorderFactory.createLineBorder(new Color(66, 62, 55), 2); // border for stat boxes
        Insets padding = new Insets(10, 15, 10, 15); // padding inside stat boxes

        balanceLabel = new JLabel(); // initialize balance label
        actionsLabel = new JLabel(); // initialize actions label
        dayLabel = new JLabel();     // initialize day label

        // add styled stat boxes to stats panel
        statsPanel.add(createStatBox(balanceLabel, statsFont, statsColour, statBorder, padding));
        statsPanel.add(createStatBox(actionsLabel, statsFont, statsColour, statBorder, padding));
        statsPanel.add(createStatBox(dayLabel, statsFont, statsColour, statBorder, padding));

        refresh(); // update labels initially

        add(statsPanel, BorderLayout.WEST);  // add stats to left
        add(ratingPanel, BorderLayout.EAST); // add rating to right

        javax.swing.Timer timer = new javax.swing.Timer(250, e -> refresh()); // timer to refresh labels
        timer.start();
    }

    private JPanel createStatBox(JLabel label, Font font, Color textColor, Border border, Insets padding) {
        label.setFont(font); // set font
        label.setForeground(textColor); // set text color

        JPanel box = new JPanel();
        box.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0)); // center layout
        box.setOpaque(false); // transparent background
        // add border and padding
        box.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right)
        ));
        box.add(label); // add label to box
        return box;
    }

    public void refresh() {
        GameState game = GameState.instance();

        balanceLabel.setText("Balance: $" + String.format("%.2f", game.getBalance())); // update balance
        actionsLabel.setText("Actions Left: " + game.getActions() + "/" + game.getTotalActions()); // update actions left
        dayLabel.setText("Days Passed: " + game.day); // update days passed

        revalidate(); // revalidate layout
        repaint();    // repaint panel
    }
}
