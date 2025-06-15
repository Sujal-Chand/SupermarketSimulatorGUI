/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.Panels.Pages.InventoryControllerPagePanel;
import com.pdcgame.Panels.Pages.OpenStorePagePanel;
import com.pdcgame.Panels.Pages.EquipmentControllerPagePanel;
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
        panels.put("Equipment", EquipmentControllerPagePanel.getInstance());
        panels.put("Products", new ProductPagePanel());
        panels.put("Inventory", new InventoryControllerPagePanel());
        panels.put("Open Store", new OpenStorePagePanel());

        add(contentPanel, BorderLayout.CENTER);

        showPanel("Menu");
    }

    public void showPanel(String name) {
        contentPanel.removeAll();

        JPanel panel = panels.get(name);
        if (panel != null) {
            if (name.equals("Products") || name.equals("Open Store")) {
                // Basic layout — just page + StoreStatusPanel
                contentPanel.add(new DefaultPagePanel(panel), BorderLayout.CENTER);
            } else if (panel instanceof SubPagePanel) {
                // Full layout — SubPage + FunctionPage + StoreStatusPanel
                FunctionPagePanel functionPage;

                if (name.equals("Default") || name.equals("Inventory") || name.equals("Equipment")) {
                    EquipmentControllerPagePanel.getInstance().updateView();
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
