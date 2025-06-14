/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */


import com.pdcgame.GameState;
import com.pdcgame.Managers.CartManager;
import com.pdcgame.ProductTypes.Product;

import javax.swing.*;
import java.awt.*;

public class ProductInfoPanel extends JPanel {

    private final JPanel productInfoPanel;
    private final JButton addToCartButton;
    private final JButton viewCartButton;

    private Product currentProduct;

    public ProductInfoPanel() {
        // Panel setup to look like a sidebar
        setLayout(new BorderLayout());
        setBackground(new Color(70, 63, 58));
        setPreferredSize(new Dimension(400, 0)); // Fixed width, full height

        // Title
        JLabel titleLabel = new JLabel("Product Info", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Inner info panel
        productInfoPanel = new JPanel();
        productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
        productInfoPanel.setBackground(new Color(70, 63, 58));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productInfoPanel.add(createLabel("Click on any product to start!",""));
        add(productInfoPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(70, 63, 58));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");

        styleButton(addToCartButton);
        styleButton(viewCartButton);

        // Add action listeners
        addToCartButton.addActionListener(e -> {
            if (currentProduct != null) {
                System.out.println("Adding to cart: " + currentProduct.getName());
                CartManager.instance().addProduct(currentProduct.getName(), 1);
                productInfoPanel.add(createLabel("Added:", " 1 x " + currentProduct.getName() +" to cart)"));
                productInfoPanel.revalidate();
                productInfoPanel.repaint();
                
            }
        });

        viewCartButton.addActionListener(e -> {
            System.out.println("Viewing cart...cart is empty: "+CartManager.instance().cartEmpty());

        });

        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);

        add(buttonPanel, BorderLayout.SOUTH);
        
    }

    public void showProductInfo(Product product) {
        productInfoPanel.removeAll();

        productInfoPanel.add(createLabel("Name:", product.getName()));
        productInfoPanel.add(createWrappedLabel("Description:", product.getDescription()));
        productInfoPanel.add(createLabel("Price:", "$" + String.format("%.2f", product.getBulkPrice())));
        productInfoPanel.add(createLabel("Quantity:", String.valueOf(product.getQuantityInBox())));
        productInfoPanel.add(createLabel("Storage Type:", product.getStorageType().name()));

        productInfoPanel.revalidate();
        productInfoPanel.repaint();
        currentProduct = product;
        System.out.println("showing product info for: " + product.getName());
    }

    private JLabel createLabel(String key, String value) {
        String html = "<html><span style='font-weight:bold'>" + key + "</span> " + value + "</html>";
        JLabel label = new JLabel(html);
        label.setFont(new Font("Courier New", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }

    private JLabel createWrappedLabel(String key, String value) {
        String html = "<html><body style='width:300px'><span style='font-weight:bold'>" 
                      + key + ":</span> " + value + "</body></html>";
        JLabel label = new JLabel(html);
        label.setFont(new Font("Courier New", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        return label;
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Courier New", Font.BOLD, 16));
        button.setBackground(new Color(90, 80, 75));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
    }
}
