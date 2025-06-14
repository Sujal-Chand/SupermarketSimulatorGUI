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
import com.pdcgame.Managers.ActionManager;
import javax.swing.*;
import java.awt.*;

import com.pdcgame.Managers.CartManager;
import com.pdcgame.ProductTypes.Product;

import java.util.Map;

public class ProductInfoPanel extends JPanel {

    private final JPanel productInfoPanel;
    private final JPanel cartPanel;
    private final JButton addToCartButton;
    private final JButton viewCartButton;
    private final JPanel productButtonsPanel;
    private final JPanel cartButtonsPanel;
    private final JLabel cartTotalLabel = new JLabel();
    private final JButton purchaseCartButton = new JButton("Purchase Cart");
    
    private JButton backButton;

    private Product currentProduct;

    public ProductInfoPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(70, 63, 58));
        setPreferredSize(new Dimension(400, 0));

        // Title label will be added dynamically when switching views

        // Product Info Panel setup
        productInfoPanel = new JPanel();
        productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
        productInfoPanel.setBackground(new Color(70, 63, 58));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productInfoPanel.add(createLabel("Click on any product to start!", ""));

        // Cart Panel setup (empty initially)
        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(new Color(70, 63, 58));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Buttons
        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        backButton = new JButton("Back");

        styleButton(addToCartButton);
        styleButton(viewCartButton);
        styleButton(backButton);
        
        styleButton(purchaseCartButton);
        purchaseCartButton.addActionListener(e -> {
            if (!CartManager.instance().cartEmpty()) {
                cartPanel.removeAll();
                cartPanel.add(createLabel("Purchase complete! Thanks.", ""));
                CartManager.instance().checkoutCart();
                ActionManager.tryActionUpdate();
                revalidate();
                repaint();               

                
            }
        });

        // Product buttons panel (Add + View)
        productButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        productButtonsPanel.setBackground(new Color(70, 63, 58));
        productButtonsPanel.add(addToCartButton);
        productButtonsPanel.add(viewCartButton);

        // Cart buttons panel (Back only)
        cartButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        cartButtonsPanel.setBackground(new Color(70, 63, 58));
        cartButtonsPanel.add(backButton);

        // Add actions
        addToCartButton.addActionListener(e -> {
            if (currentProduct != null) {
                System.out.println("Adding to cart: " + currentProduct.getName());
                CartManager.instance().addProduct(currentProduct.getName(), 1);
                productInfoPanel.add(createLabel("Added:", " 1 x " + currentProduct.getName() + " to cart"));
                productInfoPanel.revalidate();
                productInfoPanel.repaint();
            }
        });

        viewCartButton.addActionListener(e -> {
            System.out.println("Viewing cart...cart is empty: " + CartManager.instance().cartEmpty());
            showCartPanel();
        });

        backButton.addActionListener(e -> {
            showProductInfoPanel();
        });

        // Show initial product info view
        showProductInfoPanel();
    }

    private void showProductInfoPanel() {
        removeAll(); // Remove everything before adding new

        JLabel titleLabel = new JLabel("Product Info", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        add(productInfoPanel, BorderLayout.CENTER);
        add(productButtonsPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void showCartPanel() {
        removeAll(); // Remove everything before adding new

        JLabel titleLabel = new JLabel("Cart", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        cartPanel.removeAll();
        Map<String, Integer> items = CartManager.instance().getStoredProducts();

        if (items.isEmpty()) {
            cartPanel.add(createLabel("Cart is empty.", ""));
        } else {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                cartPanel.add(createLabel(entry.getKey(), "Quantity: " + entry.getValue()));
            }
        }

        double total = CartManager.instance().cartTotalValue();
        cartTotalLabel.setText("Total: $" + String.format("%.2f", total));
        cartTotalLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        cartTotalLabel.setForeground(Color.WHITE);
        cartTotalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        cartTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartPanel.add(cartTotalLabel);

        add(cartPanel, BorderLayout.CENTER);

        // Cart button panel (Back + Purchase)
        cartButtonsPanel.removeAll();
        cartButtonsPanel.add(backButton);
        cartButtonsPanel.add(purchaseCartButton);
        add(cartButtonsPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
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
        String html = "<html><body style='width:250px'><span style='font-weight:bold'>" 
                      + key + "</span> " + value + "</body></html>";
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
    
    private void styleSmallButton(JButton button) {
        button.setFont(new Font("Courier New", Font.BOLD, 14));
        button.setBackground(new Color(110, 100, 90));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(45, 30));
    }
}

