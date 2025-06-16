/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author sujalchand
 */
public class PanelNavigator extends JPanel {
    private static PanelNavigator instance;

    private final Map<String, JButton> buttons = new LinkedHashMap<>();
    private String selectedPage = "Default";

    private final JPanel tabButtonPanel;
    private final BottomCardPanel bottomCardPanel;

    private boolean buttonsAdded = false;

    // colors reused for buttons
    private static final Color COLOR_BG_SELECTED = new Color(70, 63, 58);
    private static final Color COLOR_BG_UNSELECTED = new Color(138, 129, 124);
    private static final Color COLOR_FG = new Color(244, 243, 238);

    // private constructor enforces singleton pattern
    private PanelNavigator(BottomCardPanel bottomCardPanel) {
        this.bottomCardPanel = bottomCardPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(237, 235, 215));

        tabButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        tabButtonPanel.setBackground(new Color(188, 184, 177));
    }

    public static synchronized PanelNavigator getInstance(BottomCardPanel bottomCardPanel) {
        if (instance == null) {
            instance = new PanelNavigator(bottomCardPanel);
        }
        return instance;
    }

    public static PanelNavigator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PanelNavigator has not been initialized yet.");
        }
        return instance;
    }

    /**
     * Adds navigation buttons if not already added.
     * Ensures buttons are added only once.
     */
    public void addButtons() {
        if (buttonsAdded) return;

        // Define your tabs here
        String[] tabs = { "Default", "Equipment", "Inventory", "Products", "Store", "Menu" };

        for (String tab : tabs) {
            addTabButton(tab);
        }

        add(tabButtonPanel, BorderLayout.NORTH);
        updateButtonColors();

        revalidate();
        repaint();

        buttonsAdded = true;
    }


     // creates a button for a tab and sets its behavior.
    private void addTabButton(String name) {
        JButton button = new JButton(name);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        button.setOpaque(true);
        button.setForeground(COLOR_FG);

        button.addActionListener(e -> switchPanel(name));

        buttons.put(name, button);
        tabButtonPanel.add(button);
    }

    // switches the view to the selected panel and updates button styles.
    public void switchPanel(String panel) {
        if (panel == null || panel.equals(selectedPage)) return; // No change

        selectedPage = panel;
        bottomCardPanel.showPanel(panel);
        updateButtonColors();

        System.out.println("Switched to panel: " + panel);
    }


    // updates button background colors based on selected state.

    private void updateButtonColors() {
        for (Map.Entry<String, JButton> entry : buttons.entrySet()) {
            boolean isSelected = entry.getKey().equals(selectedPage);
            JButton button = entry.getValue();

            button.setBackground(isSelected ? COLOR_BG_SELECTED : COLOR_BG_UNSELECTED);
            button.setForeground(COLOR_FG);
        }
    }
}
