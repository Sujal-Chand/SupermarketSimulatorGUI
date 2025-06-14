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
import com.pdcgame.Panels.ShelfPanel;
import com.pdcgame.Panels.SubPagePanel;

import javax.swing.*;
import java.awt.*;

public class ProductPagePanel extends SubPagePanel {

    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    public ProductPagePanel() {
        setLayout(null);

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

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBounds(50, 100, 700, 500);
        contentPanel.setOpaque(false);

        ShelfPanel shelfPanel = new ShelfPanel();
        FridgePanel fridgePanel = new FridgePanel();
        FreezerPanel freezerPanel = new FreezerPanel();

        contentPanel.add(shelfPanel, "shelf");
        contentPanel.add(fridgePanel, "fridge");
        contentPanel.add(freezerPanel, "freezer");

        add(contentPanel);

        shelfButton.addActionListener(e -> cardLayout.show(contentPanel, "shelf"));
        fridgeButton.addActionListener(e -> cardLayout.show(contentPanel, "fridge"));
        freezerButton.addActionListener(e -> cardLayout.show(contentPanel, "freezer"));
    }
}
