/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.Panels.Pages.InventoryPagePanel;
import com.pdcgame.Panels.Pages.StorePagePanel;
import com.pdcgame.Panels.Pages.EquipmentPagePanel;
import com.pdcgame.Panels.Pages.InstructionPagePanel;
import com.pdcgame.Panels.Pages.ProductPagePanel;
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

        panels.put("Menu", new MenuPagePanel(this));
        panels.put("Default", new InstructionPagePanel());
        panels.put("Equipment", new EquipmentPagePanel());
        panels.put("Products", new ProductPagePanel());
        panels.put("Inventory", new InventoryPagePanel());
        panels.put("Store", new StorePagePanel());

        add(contentPanel, BorderLayout.CENTER);

        showPanel("Menu");
    }

    public void showPanel(String name) {
        contentPanel.removeAll();

        JPanel panel = panels.get(name);
        if (panel != null) {
            if (name.equals("Products") || name.equals("Store")) {
                // Basic layout — just page + StoreStatusPanel
                contentPanel.add(new DefaultPagePanel(panel), BorderLayout.CENTER);
            } else if (panel instanceof SubPagePanel) {
                // Full layout — SubPage + FunctionPage + StoreStatusPanel
                FunctionPagePanel functionPage;

                if (name.equals("Default") || name.equals("Inventory") || name.equals("Equipment")) {
                    functionPage = new GameBoardPanel();
                } else {
                    functionPage = new BuyProductsPanel();
                }

                contentPanel.add(new DefaultPagePanel((SubPagePanel) panel, functionPage), BorderLayout.CENTER);
            } else {
                // Fallback (shouldn't hit)
                contentPanel.add(panel, BorderLayout.CENTER);
            }

            contentPanel.revalidate();
            contentPanel.repaint();
        } else {
            System.err.println("Panel with name '" + name + "' not found.");
        }
    }
}
