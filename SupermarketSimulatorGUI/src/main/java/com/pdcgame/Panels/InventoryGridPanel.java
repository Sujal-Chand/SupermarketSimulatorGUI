package com.pdcgame.Panels;

import com.pdcgame.ProductTypes.PurchasableProduct;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 *
 * @author sujalchand
 */
public class InventoryGridPanel extends JPanel {

    // label shown initially or when no products are displayed
    private final JLabel messageLabel;

    public InventoryGridPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // initialize message label with instructions
        messageLabel = new JLabel("Click on a category to display stock and info!");
        messageLabel.setFont(new Font("Dialog", Font.BOLD, 24));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add the message label at the top (NORTH region)
        add(messageLabel, BorderLayout.NORTH);
    }

    /**
     * Updates the panel to show product cards for the given collection of products.
     * Removes existing components and sets up a grid layout with 3 columns.
     *
     * @param products Collection of PurchasableProduct to display.
     */
    public void setListProducts(Collection<PurchasableProduct> products) {
        // Clear all existing components before adding new ones
        removeAll();

        // Remove message label if products are being shown
        if (!products.isEmpty()) {
            // switch to GridLayout for product cards
            setLayout(new GridLayout(0, 3, 10, 10));
            setBackground(Color.WHITE);

            // add a product card for each product
            for (PurchasableProduct product : products) {
                add(new InventoryProductCard(product.getName(), "Sell Price: $" + product.getSellPrice()));
            }

            // calculate preferred height based on number of rows needed (rows * card height approx)
            int rows = (int) Math.ceil(products.size() / 3.0);
            setPreferredSize(new Dimension(600, rows * 220));
        } else {
            // if no products, reset to BorderLayout and show message label
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            add(messageLabel, BorderLayout.NORTH);
            setPreferredSize(null); // reset preferred size
        }

        // refresh layout and repaint to show changes
        revalidate();
        repaint();
    }
}
