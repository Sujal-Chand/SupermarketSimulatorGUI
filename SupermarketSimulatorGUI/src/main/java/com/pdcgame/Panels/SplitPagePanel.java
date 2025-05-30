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
        leftPanel.setBackground(new Color(40, 40, 40));
        leftPanel.add(new JLabel(labelText + " - Left Side"));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(20, 20, 20));
        rightPanel.add(new JLabel(labelText + " - Right Side"));
        rightPanel.setPreferredSize(new Dimension(400, 0)); // Half of 800

        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}
