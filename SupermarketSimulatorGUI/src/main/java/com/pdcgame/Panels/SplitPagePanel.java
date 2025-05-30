/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sujalchand
 */

public class SplitPagePanel extends JPanel {
    public SplitPagePanel(String labelText) {
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(138, 129, 124));
        JLabel leftLabel = new JLabel(labelText + " - Left Side");
        leftLabel.setForeground(new Color(244, 243, 238));
        leftPanel.add(leftLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(70, 63, 58));
        JLabel rightLabel = new JLabel(labelText + " - Right Side");
        rightLabel.setForeground(new Color(244, 243, 238));
        rightPanel.add(rightLabel);
        rightPanel.setPreferredSize(new Dimension(300, 0));

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
