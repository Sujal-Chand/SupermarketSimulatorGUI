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
import com.pdcgame.Panels.ProductGridPanel;
import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;

public class InventoryPagePanel extends SubPagePanel {

    public InventoryPagePanel() {
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

        // Panel to hold the products
        ProductGridPanel productGridPanel = new ProductGridPanel();

        JScrollPane scrollPane = new JScrollPane(productGridPanel);
        scrollPane.setBounds(15, 110, 850, 450); // below the buttons
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        shelfButton.addActionListener(e -> {
            productGridPanel.setListProducts(GameState.instance()
                    .getProductManager().getFilteredPurchasableProducts(ProductStorageType.SHELF));
        });

        fridgeButton.addActionListener(e -> {
            productGridPanel.setListProducts(GameState.instance()
                    .getProductManager().getFilteredPurchasableProducts(ProductStorageType.FRIDGE));
        });

        freezerButton.addActionListener(e -> {
            productGridPanel.setListProducts(GameState.instance()
                    .getProductManager().getFilteredPurchasableProducts(ProductStorageType.FROZEN));
        });
    }
}
