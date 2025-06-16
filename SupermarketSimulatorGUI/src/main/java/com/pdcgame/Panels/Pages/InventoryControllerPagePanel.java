/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 * @author prisha, sujal
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

    // tracks the last selected product storage type (shelf, fridge, freezer)
    private ProductStorageType lastSelectedType = null;
    // panel that displays inventory items in a grid
    private final InventoryGridPanel inventoryGridPanel = new InventoryGridPanel();

    public InventoryControllerPagePanel() {
        // use absolute positioning
        setLayout(null);

        // create title label
        JLabel titleLabel = new JLabel("Inventory");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        // create buttons for storage types
        JButton shelfButton = new JButton("Shelf");
        JButton fridgeButton = new JButton("Fridge");
        JButton freezerButton = new JButton("Freezer");

        // position buttons
        shelfButton.setBounds(50, 60, 100, 30);
        fridgeButton.setBounds(160, 60, 100, 30);
        freezerButton.setBounds(270, 60, 100, 30);

        // add buttons to panel
        add(shelfButton);
        add(fridgeButton);
        add(freezerButton);

        // create scrollable panel for inventory display
        JScrollPane scrollPane = new JScrollPane(inventoryGridPanel);
        scrollPane.setBounds(15, 110, 850, 450);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // refresh every 250ms
        Timer timer = new Timer(2500, (ActionEvent e) -> {
            if (lastSelectedType != null) {
                refreshList();
            }
        });
        timer.start();

        // add button listeners to change selected storage type and refresh
        shelfButton.addActionListener(e -> handleStorageTypeSelection(ProductStorageType.SHELF));
        fridgeButton.addActionListener(e -> handleStorageTypeSelection(ProductStorageType.FRIDGE));
        freezerButton.addActionListener(e -> handleStorageTypeSelection(ProductStorageType.FROZEN));
    }

    // updates product list shown in the grid panel based on selected type
    private void refreshList() {
        inventoryGridPanel.setListProducts(GameState.instance()
                .getProductManager()
                .getFilteredPurchasableProducts(lastSelectedType));
    }

    // sets the selected storage type and refreshes the list
    private void handleStorageTypeSelection(ProductStorageType type) {
        lastSelectedType = type;
        refreshList();
    }
}
