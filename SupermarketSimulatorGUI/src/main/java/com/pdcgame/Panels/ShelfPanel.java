    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.pdcgame.Panels;

    /**
     *
     * @author prish
     */

import com.pdcgame.Enums.ProductStorageType;
import com.pdcgame.GameState;
import com.pdcgame.ProductTypes.Product;
import com.pdcgame.ProductTypes.PurchasableProduct;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

    public class ShelfPanel extends JPanel {

        // background image for the shelf panel
        private final Image backgroundImage;
        // list to store shelf items with their image icons and positions
        private final List<ShelfItem> shelfItems = new ArrayList<>();
        // list of buttons corresponding to shelf items
        private final List<JButton> buttons = new ArrayList<>();

        // interface for handling product click events
        public interface ProductClickListener {
            void onProductClicked(Product product);
        }

        // listener instance to be notified on product clicks
        private ProductClickListener listener;

        // setter to assign the product click listener
        public void setProductClickListener(ProductClickListener listener) {
            this.listener = listener;
        }

        // constructor initializes the panel
        public ShelfPanel() {
            backgroundImage = loadImage("/shelf.png"); // load shelf background image
            setPreferredSize(new Dimension(700, 500)); // set preferred panel size
            setLayout(null); // use absolute positioning
            setOpaque(false); // make background transparent to show image
            populateShelf(); // populate shelf with products and buttons
        }

        // creates shelf items and buttons for each product
        private void populateShelf() {
            int x = 40, y = 65, w = 80, h = 80; // initial position and size of buttons
            int spacing = 80; // spacing between buttons

            // iterate through filtered purchasable products to add them to shelf
            for (PurchasableProduct product : GameState.instance().getProductManager().getFilteredPurchasableProducts(ProductStorageType.SHELF)) {
                String imagePath = "/" + product.getName().toLowerCase() + ".png"; // image path based on product name
                addShelfItem(imagePath, x, y, w, h, product); // add shelf item with image and position

                x += spacing; // move x position for next button
                if (x > 500) { // wrap to next row if x exceeds limit
                    x = 40;
                    y += spacing;
                }
            }

            // create buttons for each shelf item
            for (ShelfItem item : shelfItems) {
                JButton btn = new JButton(item.icon);
                btn.setBounds(item.x, item.y, item.width, item.height);
                btn.setBorderPainted(false); // remove button border
                btn.setContentAreaFilled(false); // remove button background fill
                btn.setFocusPainted(false); // remove focus painting
                btn.setOpaque(false); // make button transparent

                // add action listener for button clicks, calling onButtonClicked with the product
                btn.addActionListener(e -> onButtonClicked(item.product));

                add(btn); // add button to panel
                buttons.add(btn); // keep track of buttons
            }
        }

        // called when a shelf button is clicked, notifies listener if set
        private void onButtonClicked(Product product) {
            System.out.println("Clicked: " + product.getName());
            if (listener != null) {
                System.out.println("Listener is set. Calling...");
                listener.onProductClicked(product); // notify listener of clicked product
            } else {
                System.out.println("Listener is NULL!");
            }
        }

        // helper method to create and add a shelf item with scaled icon and product info
        private void addShelfItem(String imagePath, int x, int y, int width, int height, Product product) {
            ImageIcon icon = loadAndScaleIcon(imagePath, width, height); // load and scale product image
            shelfItems.add(new ShelfItem(icon, x, y, width, height, product)); // add shelf item to list
        }

        // loads an image from resources by path, returns null if not found
        private Image loadImage(String path) {
            URL imgUrl = getClass().getResource(path);
            if (imgUrl == null) {
                System.err.println("Shelf image not found: " + path);
                return null;
            }
            return new ImageIcon(imgUrl).getImage();
        }

        // loads and scales an icon from resources by path and size, returns empty icon if not found
        private ImageIcon loadAndScaleIcon(String path, int width, int height) {
            URL imgUrl = getClass().getResource(path);
            if (imgUrl == null) {
                System.err.println("Button image not found: " + path);
                return new ImageIcon();
            }
            Image img = new ImageIcon(imgUrl).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }

        // paint component to draw the background image stretched to panel size
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
