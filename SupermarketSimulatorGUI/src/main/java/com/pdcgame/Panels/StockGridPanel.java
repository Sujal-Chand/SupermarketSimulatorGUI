package com.pdcgame.Panels;

import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.ProductTypes.PurchasableProduct;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 *
 * @author sujalchand
 */
public class StockGridPanel extends JPanel {

    // constructor sets up panel with border layout and white background
    public StockGridPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    /**
     * populates the panel with stock product cards based on given products and coordinates
     * @param products collection of purchasable products to display
     * @param coordinates integer array representing a location coordinate
     */
    public void setListProducts(Collection<PurchasableProduct> products, int[] coordinates) {
        removeAll(); // clear existing components
        setLayout(new GridLayout(0, 3, 10, 10)); // set grid layout with 3 columns and 10px gaps
        setBackground(Color.WHITE); // set background color to white

        // convert coordinate array to string representation
        String coordinate = IOHandler.instance().coordinatesToString(coordinates);

        // iterate products and add a StockProductCard for each with stock info
        for (PurchasableProduct product : products) {
            // get quantity of product at specified coordinate from floor storage manager
            int itemStock = GameState.instance().getFloorStorageManager().getProductQuantityAt(coordinate, product.getName());
            // add a card with product name, stock quantity, and coordinate info
            add(new StockProductCard(product.getName(), String.valueOf(itemStock), coordinate));
        }

        // calculate number of rows needed based on product count (3 per row)
        int rows = (int) Math.ceil(products.size() / 3.0);
        // set preferred size based on rows, assuming 200px height per row and fixed width
        setPreferredSize(new Dimension(550, rows * 200));
    }
}
