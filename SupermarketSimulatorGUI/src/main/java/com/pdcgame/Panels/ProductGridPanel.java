package com.pdcgame.Panels;

import com.pdcgame.GameState;
import com.pdcgame.ProductTypes.PurchasableProduct;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ProductGridPanel extends JPanel {

    public ProductGridPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel("Click on a category to display stock and info!");
        messageLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(messageLabel, BorderLayout.NORTH);
    }

    public void setListProducts(Collection<PurchasableProduct> products) {
        removeAll();
        setLayout(new GridLayout(0, 3, 10, 10));
        setBackground(Color.WHITE);
        for(PurchasableProduct product : products) {
            add(new ProductCard(product.getName(), "Sell Price: $"+product.getSellPrice()));
        }

        int rows = (int) Math.ceil(products.size() / 3.0);
        setPreferredSize(new Dimension(600, rows * 200));
        revalidate();
        repaint();
    }
}
