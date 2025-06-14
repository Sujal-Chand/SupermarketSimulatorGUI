/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */

import com.pdcgame.ProductTypes.Product;

import javax.swing.*;
import java.awt.*;

public class ProductInfoPanel extends JPanel {

    private final JPanel productInfoPanel;

    public ProductInfoPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(70, 63, 58));
        setPreferredSize(new Dimension(400, 0));

        JLabel titleLabel = new JLabel("Products info Board", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        productInfoPanel = new JPanel();
        productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
        productInfoPanel.setBackground(new Color(30, 30, 30));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(productInfoPanel, BorderLayout.CENTER);
    }
    
    public void showProductInfo(Product product) {
        productInfoPanel.removeAll();
        productInfoPanel.add(createLabel("Name: " + product.getName()));
        productInfoPanel.add(createLabel("Price: " + product.getBulkPrice()));
        productInfoPanel.add(createLabel("Storage Type: " + product.getStorageType().name()));
        productInfoPanel.revalidate();
        productInfoPanel.repaint();
        System.out.println("show product info printed");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }
}
