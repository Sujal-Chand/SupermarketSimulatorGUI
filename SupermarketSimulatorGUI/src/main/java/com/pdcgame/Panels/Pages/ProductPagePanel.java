/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 *
 * @author prish
 */

import com.pdcgame.Panels.FreezerPanel;
import com.pdcgame.Panels.FridgePanel;
import com.pdcgame.Panels.ProductInfoPanel;
import com.pdcgame.Panels.ShelfPanel;
import com.pdcgame.Panels.StoreStatusPanel;
import com.pdcgame.Panels.SubPagePanel;

import javax.swing.*;
import java.awt.*;

public class ProductPagePanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel contentPanel;
    private final ProductInfoPanel productInfoPanel;

    public ProductPagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        JLabel titleLabel = new JLabel("Products Page");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        // Buttons
        JButton shelfButton = new JButton("Shelf");
        JButton fridgeButton = new JButton("Fridge");
        JButton freezerButton = new JButton("Freezer");

        shelfButton.setBounds(50, 60, 100, 30);
        fridgeButton.setBounds(160, 60, 100, 30);
        freezerButton.setBounds(270, 60, 100, 30);

        add(shelfButton);
        add(fridgeButton);
        add(freezerButton);

        // Panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBounds(50, 100, 700, 500);
        contentPanel.setOpaque(false);

        // Info Panel
        productInfoPanel = new ProductInfoPanel();
        productInfoPanel.setBounds(880, 0, 400, 580); // Adjust as needed
        add(productInfoPanel);

        // ShelfPanel setup with listener
        ShelfPanel shelfPanel = new ShelfPanel();
        shelfPanel.setProductClickListener(product -> productInfoPanel.showProductInfo(product));

        FridgePanel fridgePanel = new FridgePanel();
        fridgePanel.setProductClickListener(product -> productInfoPanel.showProductInfo(product));
        
        FreezerPanel freezerPanel = new FreezerPanel();
        freezerPanel.setProductClickListener(product -> productInfoPanel.showProductInfo(product));
        
        contentPanel.add(shelfPanel, "shelf");
        contentPanel.add(fridgePanel, "fridge");
        contentPanel.add(freezerPanel, "freezer");

        add(contentPanel);

        shelfButton.addActionListener(e -> cardLayout.show(contentPanel, "shelf"));
        fridgeButton.addActionListener(e -> cardLayout.show(contentPanel, "fridge"));
        freezerButton.addActionListener(e -> cardLayout.show(contentPanel, "freezer"));
    }
}
