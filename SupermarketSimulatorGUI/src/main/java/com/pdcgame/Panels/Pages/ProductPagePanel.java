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
import com.pdcgame.Panels.PopularProductsPanel;

import javax.swing.*;
import java.awt.*;

public class ProductPagePanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    public ProductPagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        JLabel titleLabel = new JLabel("Products");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        JLabel subTitleLabel = new JLabel("- here you can choose to purchase products and see weekly popular products");
        subTitleLabel.setBounds(120, 10, 1500, 40);
        subTitleLabel.setForeground(new Color(66, 62, 55));
        subTitleLabel.setFont(new Font("Impact", Font.PLAIN, 30));


        // buttons
        JButton shelfButton = new JButton("Shelf");
        JButton fridgeButton = new JButton("Fridge");
        JButton freezerButton = new JButton("Freezer");
        JButton popularButton = new JButton("Popular");

        shelfButton.setBounds(20, 60, 100, 30);
        fridgeButton.setBounds(130, 60, 100, 30);
        freezerButton.setBounds(240, 60, 110, 30);
        popularButton.setBounds(360, 60, 110, 30);

        Font buttonFont = new Font("Courier New", Font.BOLD, 16);
        Color buttonBg = new Color(250, 250, 240);
        Color buttonFg = new Color(90, 80, 75);

        shelfButton.setFont(buttonFont);
        fridgeButton.setFont(buttonFont);
        freezerButton.setFont(buttonFont);
        popularButton.setFont(buttonFont);

        shelfButton.setBackground(buttonBg);
        fridgeButton.setBackground(buttonBg);
        freezerButton.setBackground(buttonBg);
        popularButton.setBackground(buttonBg);

        shelfButton.setForeground(buttonFg);
        fridgeButton.setForeground(buttonFg);
        freezerButton.setForeground(buttonFg);
        popularButton.setForeground(buttonFg);

        shelfButton.setFocusPainted(false);
        fridgeButton.setFocusPainted(false);
        freezerButton.setFocusPainted(false);
        popularButton.setFocusPainted(false);

        add(shelfButton);
        add(fridgeButton);
        add(freezerButton);
        add(popularButton);

        // panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBounds(50, 100, 700, 500);
        contentPanel.setOpaque(false);

        // info Panel
        ProductInfoPanel productInfoPanel = new ProductInfoPanel();
        productInfoPanel.setBounds(880, 0, 400, 584);
        add(productInfoPanel);

        // ShelfPanel setup with listener
        ShelfPanel shelfPanel = new ShelfPanel();
        shelfPanel.setProductClickListener(productInfoPanel::showProductInfo);

        FridgePanel fridgePanel = new FridgePanel();
        fridgePanel.setProductClickListener(productInfoPanel::showProductInfo);

        FreezerPanel freezerPanel = new FreezerPanel();
        freezerPanel.setProductClickListener(productInfoPanel::showProductInfo);

        PopularProductsPanel popularPanel = new PopularProductsPanel();
        popularButton.addActionListener(e -> cardLayout.show(contentPanel, "popular"));

        contentPanel.add(shelfPanel, "shelf");
        contentPanel.add(fridgePanel, "fridge");
        contentPanel.add(freezerPanel, "freezer");
        contentPanel.add(popularPanel, "popular");

        add(contentPanel);

        shelfButton.addActionListener(e -> cardLayout.show(contentPanel, "shelf"));
        fridgeButton.addActionListener(e -> cardLayout.show(contentPanel, "fridge"));
        freezerButton.addActionListener(e -> cardLayout.show(contentPanel, "freezer"));
        popularButton.addActionListener(e -> cardLayout.show(contentPanel, "popular"));
    }
}
