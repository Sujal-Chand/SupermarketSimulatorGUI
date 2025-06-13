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

public class BottomCardPanel extends JPanel {
    private final JPanel contentPanel;
    private final Map<String, JPanel> panels;

    public BottomCardPanel() {
        setLayout(new BorderLayout());

        panels = new HashMap<>();
        contentPanel = new JPanel(new BorderLayout());

        // Initialise and store panels
        panels.put("Menu", new MenuPagePanel(this));
        panels.put("Default", new InstructionPagePanel());
        panels.put("Equipment", new EquipmentPagePanel());
        panels.put("Products", new ProductPagePanel());
        panels.put("Inventory", new InventoryPagePanel());
        panels.put("Open Store", new OpenStorePagePanel());
        

        add(contentPanel, BorderLayout.CENTER);

        showPanel("Menu");
    }

    public void showPanel(String name) {
        contentPanel.removeAll();

        JPanel panel = panels.get(name);
        if (panel != null) {
            if (panel instanceof SubPagePanel) {
                // Wrap in DefaultPagePanel
                contentPanel.add(new DefaultPagePanel((SubPagePanel) panel), BorderLayout.CENTER);
            } else {
                // Show raw panel (e.g. MenuPagePanel)
                contentPanel.add(panel, BorderLayout.CENTER);
            }
            contentPanel.revalidate();
            contentPanel.repaint();
        } else {
            System.err.println("Panel with name '" + name + "' not found.");
        }
    }

}
