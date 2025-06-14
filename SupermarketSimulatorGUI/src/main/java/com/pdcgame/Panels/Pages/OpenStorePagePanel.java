/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

import com.pdcgame.Managers.ScenarioManager;
import javax.swing.JPanel;

/**
 *
 * @author prish
 */

import javax.swing.*;
import java.awt.*;

public class OpenStorePagePanel extends JPanel {


    private final ScenarioManager scenarioManager = new ScenarioManager();

    public OpenStorePagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        // Title label
        JLabel titleLabel = new JLabel("Open Store");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        // Open Store button
        JButton openStoreButton = new JButton("Open Store");
        openStoreButton.setBounds(10, 60, 200, 40);
        openStoreButton.setFont(new Font("Courier New", Font.BOLD, 16));
        openStoreButton.setBackground(new Color(90, 80, 75));
        openStoreButton.setForeground(Color.WHITE);
        openStoreButton.setFocusPainted(false);
        openStoreButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        openStoreButton.addActionListener(e -> {
            scenarioManager.runDay();  // Run all store logic
            JOptionPane.showMessageDialog(this, "Store simulation complete.", "Day Finished", JOptionPane.INFORMATION_MESSAGE);
        });

        add(openStoreButton);
    }
    
}
