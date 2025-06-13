/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author sujalchand
 */
public class TopPanel extends JPanel {
    private final Map<String, JButton> buttons = new HashMap<>();
    private String selectedPage = "Default";

    private final JPanel tabButtonPanel;
    private final BottomCardPanel bottomCardPanel;  
    public TopPanel(BottomCardPanel bottomCardPanel) {
        this.bottomCardPanel = bottomCardPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(188, 184, 177));

        tabButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        tabButtonPanel.setBackground(new Color(188, 184, 177));

        addTabButton("Default");
        addTabButton("Equipment");
        addTabButton("Inventory");
        addTabButton("Products");
        addTabButton("Open Store");
        

        add(tabButtonPanel, BorderLayout.NORTH);
        updateButtonColors();
    }

    private void addTabButton(String name) {
        JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        button.setOpaque(true);

        button.addActionListener(e -> {
            selectedPage = name;
            bottomCardPanel.showPanel(name); 
            updateButtonColors();
            System.out.println("Switched to page: " + name);
        });

        buttons.put(name, button);
        tabButtonPanel.add(button);
    }

    private void updateButtonColors() {
        for (Map.Entry<String, JButton> entry : buttons.entrySet()) {
            if (entry.getKey().equals(selectedPage)) {
                entry.getValue().setBackground(new Color(70, 63, 58));
                entry.getValue().setForeground(new Color(244, 243, 238));
            } else {
                entry.getValue().setBackground(new Color(138, 129, 124));
                entry.getValue().setForeground(new Color(244, 243, 238));
            }
        }
    }
}