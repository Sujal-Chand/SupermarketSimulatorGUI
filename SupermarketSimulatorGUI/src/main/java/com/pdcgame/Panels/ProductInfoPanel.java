/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */
import com.pdcgame.Enums.InternalCases;
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
    private final JScrollPane cartScrollPane;
    private final JLabel cartTotalLabel = new JLabel();
    private final JButton purchaseCartButton = new JButton("Purchase Cart");
    
    private JButton backButton;

    private Product currentProduct;

    public ProductInfoPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(70, 63, 58));
        setPreferredSize(new Dimension(400, 0));

        productInfoPanel = new JPanel();
        productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));
        productInfoPanel.setBackground(new Color(70, 63, 58));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productInfoPanel.add(createCenteredMessageLabel("Click on any product to start!"));

        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(new Color(70, 63, 58));
        cartPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        cartScrollPane = new JScrollPane(cartPanel);
        cartScrollPane.setBorder(null);
        cartScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cartScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        cartScrollPane.getViewport().setBackground(new Color(70, 63, 58)); 

        addToCartButton = new JButton("Add to Cart");
        viewCartButton = new JButton("View Cart");
        backButton = new JButton("Back");

        styleButton(addToCartButton);
        styleButton(viewCartButton);
        styleButton(backButton);
        
        styleButton(purchaseCartButton);
        purchaseCartButton.addActionListener(e -> {
            if (!CartManager.instance().cartEmpty()) {

                InternalCases result = CartManager.instance().canCheckoutCart();

                JLabel messageLabel;
                switch (result) {
                    case NO_ACTIONS:
                        messageLabel = createCenteredMessageLabel("NO ACTIONS REMAINING");
                        break;
                    case NO_FUNDS:
                        messageLabel = createCenteredMessageLabel("NOT ENOUGH FUNDS");
                        break;
                    case SUCCESS:
                        cartPanel.removeAll();
                        messageLabel = createCenteredMessageLabel("Purchase complete! Thanks.");
                        break;
                    default:
                        messageLabel = createLabel("Unknown error occurred.", "");
                        break;
                }

                cartPanel.add(messageLabel);
                revalidate();
                repaint();
            }
        });

        productButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        productButtonsPanel.setBackground(new Color(70, 63, 58));
        productButtonsPanel.add(addToCartButton);
        productButtonsPanel.add(viewCartButton);

        cartButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        cartButtonsPanel.setBackground(new Color(70, 63, 58));
        cartButtonsPanel.add(backButton);

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
            currentProduct = null;
            productInfoPanel.removeAll();
            productInfoPanel.add(createCenteredMessageLabel("Click a product"));
            showProductInfoPanel();
        });

        showProductInfoPanel();
    }

    private void showProductInfoPanel() {
        removeAll();
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
        removeAll();

        JLabel titleLabel = new JLabel("Cart", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        cartPanel.removeAll();
        Map<String, Integer> items = CartManager.instance().getStoredProducts();

        if (items.isEmpty()) {
            cartPanel.add(createCenteredMessageLabel("CART IS EMPTY"));
        } else {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                String productName = entry.getKey();
                int currentQty = entry.getValue();

                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                itemPanel.setBackground(new Color(70, 63, 58));

                JLabel nameLabel = new JLabel(productName + ": ");
                nameLabel.setFont(new Font("Courier New", Font.PLAIN, 16));
                nameLabel.setForeground(Color.WHITE);

                JSpinner qtySpinner = new JSpinner(new SpinnerNumberModel(currentQty, 0, 100, 1));
                qtySpinner.setPreferredSize(new Dimension(60, 25));

                JButton updateButton = new JButton("Modify");
                styleButton(updateButton);

                updateButton.addActionListener(e -> {
                    int newQty = (Integer) qtySpinner.getValue();
                    if (newQty <= 0) {
                        CartManager.instance().removeProduct(productName, currentQty);
                    } else {
                        CartManager.instance().removeProduct(productName, currentQty);
                        CartManager.instance().addProduct(productName, newQty);
                    }
                    showCartPanel();
                });

                itemPanel.add(nameLabel);
                itemPanel.add(qtySpinner);
                itemPanel.add(updateButton);

                cartPanel.add(itemPanel);
            }
        }

        double total = CartManager.instance().cartTotalValue();
        cartTotalLabel.setText("Total: $" + String.format("%.2f", total));
        cartTotalLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        cartTotalLabel.setForeground(Color.WHITE);
        cartTotalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        cartTotalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartPanel.add(cartTotalLabel);

        add(cartScrollPane, BorderLayout.CENTER);

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
        
        showProductInfoPanel();
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
    
    private JLabel createCenteredMessageLabel(String message) {
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Courier New", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
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
        button.setFont(new Font("Courier New", Font.PLAIN, 5));
        button.setBackground(new Color(110, 100, 90));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(45, 30));
    }
    
}

