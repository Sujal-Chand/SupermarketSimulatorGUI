/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.Panels.Pages.InventoryControllerPagePanel;
import com.pdcgame.Panels.Pages.StorePagePanel;
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

        // create and register main panels
        panels.put("Menu", new MenuPagePanel(this));
        panels.put("Default", new InstructionPagePanel());
        panels.put("Equipment", EquipmentControllerPagePanel.getInstance());
        panels.put("Products", new ProductPagePanel());
        panels.put("Inventory", new InventoryControllerPagePanel());
        panels.put("Store", new StorePagePanel());

        add(contentPanel, BorderLayout.CENTER);

        // show the menu panel by default
        showPanel("Menu");
    }

    // show a panel by name, clearing previous content first
    public void showPanel(String name) {
        contentPanel.removeAll();

        JPanel panel = panels.get(name);
        if (panel == null) {
            System.err.println("panel with name '" + name + "' not found.");
            return;
        }

        if (name.equals("Products") || name.equals("Store")) {
            showBasicLayout(panel);
        } else if (panel instanceof SubPagePanel) {
            showFullLayout(name, (SubPagePanel) panel);
        } else {
            // fallback: just add the panel as is
            contentPanel.add(panel, BorderLayout.CENTER);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // show panels with simple layout (just the page inside a default wrapper)
    private void showBasicLayout(JPanel panel) {
        contentPanel.add(new DefaultPagePanel(panel), BorderLayout.CENTER);
    }

    // show panels that require subpage + function page + store status panel
    private void showFullLayout(String name, SubPagePanel panel) {
        FunctionPagePanel functionPage = switch (name) {
            case "Default", "Inventory" -> new GameBoardPanel();
            case "Equipment" -> {
                EquipmentControllerPagePanel.getInstance().updateView();
                yield new GameBoardPanel();
            }
            default -> new BuyProductsPanel();
        };

        contentPanel.add(new DefaultPagePanel(panel, functionPage), BorderLayout.CENTER);
    }
}
