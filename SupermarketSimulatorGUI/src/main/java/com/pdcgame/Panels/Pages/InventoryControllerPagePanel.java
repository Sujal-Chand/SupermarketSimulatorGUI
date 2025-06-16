/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 *
 * @author prish
 */

import com.pdcgame.Enums.ProductStorageType;
import com.pdcgame.GameState;
import com.pdcgame.Panels.InventoryGridPanel;
import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryControllerPagePanel extends SubPagePanel {

    private ProductStorageType lastSelectedType = null;
    private final InventoryGridPanel inventoryGridPanel = new InventoryGridPanel();

    public InventoryControllerPagePanel() {
        setLayout(null);

        JLabel titleLabel = new JLabel("Inventory");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        JButton shelfButton = new JButton("Shelf");
        JButton fridgeButton = new JButton("Fridge");
        JButton freezerButton = new JButton("Freezer");

        shelfButton.setBounds(50, 60, 100, 30);
        fridgeButton.setBounds(160, 60, 100, 30);
        freezerButton.setBounds(270, 60, 100, 30);

        add(shelfButton);
        add(fridgeButton);
        add(freezerButton);

        JScrollPane scrollPane = new JScrollPane(inventoryGridPanel);
        scrollPane.setBounds(15, 110, 850, 450);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // Timer to refresh product list every 2 seconds, only if a type is selected
        Timer timer = new Timer(2000, (ActionEvent e) -> {
            if (lastSelectedType != null) {
                refreshList();
            }
        });
        timer.start();

        shelfButton.addActionListener(e -> {
            lastSelectedType = ProductStorageType.SHELF;
            refreshList();
        });

        fridgeButton.addActionListener(e -> {
            lastSelectedType = ProductStorageType.FRIDGE;
            refreshList();
        });

        freezerButton.addActionListener(e -> {
            lastSelectedType = ProductStorageType.FROZEN;
            refreshList();
        });
    }

    private void refreshList() {
        inventoryGridPanel.setListProducts(GameState.instance()
                .getProductManager()
                .getFilteredPurchasableProducts(lastSelectedType));
    }
}
