package com.pdcgame.Panels;

import com.pdcgame.ProductTypes.PurchasableProduct;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ProductGridPanel extends JPanel {

    public ProductGridPanel() {
        setLayout(new GridLayout(0, 3, 10, 10));
        setBackground(Color.WHITE);
    }

    public void setListProducts(Collection<PurchasableProduct> products) {
        removeAll();
        for(PurchasableProduct product : products) {
            add(new ProductCard(product.getName(), "Sell Price: $"+product.getSellPrice()));
        }

        int rows = (int) Math.ceil(products.size() / 3.0);
        setPreferredSize(new Dimension(600, rows * 180));
        revalidate();
        repaint();
    }
}
