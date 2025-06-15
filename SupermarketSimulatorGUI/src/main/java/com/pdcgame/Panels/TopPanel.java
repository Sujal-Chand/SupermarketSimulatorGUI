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
    private static TopPanel instance;

    private final Map<String, JButton> buttons = new HashMap<>();
    private String selectedPage = "Default";

    private final JPanel tabButtonPanel;
    private final BottomCardPanel bottomCardPanel;

    private boolean buttonsAdded = false;

    // Private constructor
    private TopPanel(BottomCardPanel bottomCardPanel) {
        this.bottomCardPanel = bottomCardPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(237, 235, 215));

        tabButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        tabButtonPanel.setBackground(new Color(188, 184, 177));

        // Do not add tabButtonPanel yet — only when buttons are added
    }

    public static TopPanel getInstance(BottomCardPanel bottomCardPanel) {
        if (instance == null) {
            instance = new TopPanel(bottomCardPanel);
        }
        return instance;
    }

    public static TopPanel getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TopPanel has not been initialized yet.");
        }
        return instance;
    }

    public void addButtons() {
        if (buttonsAdded) return;

        addTabButton("Default");
        addTabButton("Equipment");
        addTabButton("Inventory");
        addTabButton("Products");
        addTabButton("Open Store");

        add(tabButtonPanel, BorderLayout.NORTH);  // Add it *after* buttons are ready
        updateButtonColors();
        revalidate();  // Refresh layout
        repaint();

        buttonsAdded = true;
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
            boolean selected = entry.getKey().equals(selectedPage);
            entry.getValue().setBackground(selected ? new Color(70, 63, 58) : new Color(138, 129, 124));
            entry.getValue().setForeground(new Color(244, 243, 238));
        }
    }
}
