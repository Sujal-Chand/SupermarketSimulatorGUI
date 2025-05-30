/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sujalchand
 */
public class TopPanel extends JPanel {
    private final Map<String, JButton> buttons = new HashMap<>();
    private String selectedPage = "Menu";  // Default selected page

    public TopPanel(BottomCardPanel bottomCardPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        setBackground(new Color(188, 184, 177));

        add(createTabButton("Menu", bottomCardPanel));
        add(createTabButton("Equipment", bottomCardPanel));
        add(createTabButton("Inventory", bottomCardPanel));
        add(createTabButton("Products", bottomCardPanel));
        add(createTabButton("Open Store", bottomCardPanel));
        
        updateButtonColors();  // Set initial selection
    }

    private JButton createTabButton(String name, BottomCardPanel cardPanel) {
        JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        button.setOpaque(true);

        button.addActionListener(e -> {
            selectedPage = name;
            cardPanel.showPanel(name);
            updateButtonColors();
        });

        buttons.put(name, button);
        return button;
    }

    private void updateButtonColors() {
        for (Map.Entry<String, JButton> entry : buttons.entrySet()) {
            if (entry.getKey().equals(selectedPage)) {
                // Selected button color
                entry.getValue().setBackground(new Color(70, 63, 58));  
                entry.getValue().setForeground(new Color(244, 243, 238));
            } else {
                // Unselected button color
                entry.getValue().setBackground(new Color(138, 129, 124));
                entry.getValue().setForeground(new Color(244, 243, 238));
            }
        }
    }
}
