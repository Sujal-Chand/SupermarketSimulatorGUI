package com.pdcgame.Panels;

import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.ProductTypes.PurchasableProduct;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class StockGridPanel extends JPanel {
    public StockGridPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    public void setListProducts(Collection<PurchasableProduct> products, int[] coordinates) {
        removeAll();
        setLayout(new GridLayout(0, 3, 10, 10));
        setBackground(Color.WHITE);

        String coordinate = IOHandler.instance().coordinatesToString(coordinates);
        for (PurchasableProduct product : products) {
            int itemStock = GameState.instance().getFloorStorageManager().getProductQuantityAt(coordinate, product.getName());
            add(new StockProductCard(product.getName(), String.valueOf(itemStock), coordinate));
        }

        int rows = (int) Math.ceil(products.size() / 3.0);
        setPreferredSize(new Dimension(550, rows * 200));
    }
}
