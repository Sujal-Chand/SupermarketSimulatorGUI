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
import com.pdcgame.ProductTypes.PurchasableProduct;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Collection;
import java.util.List;

public class PopularProductsPanel extends JPanel {

    public PopularProductsPanel() {
        setLayout(new BorderLayout());  // use border layout for the panel
        setBackground(new Color(250, 250, 240));  // set background color

        // create and configure title label
        JLabel title = new JLabel("This Week's Popular Products", SwingConstants.CENTER);
        title.setFont(new Font("Courier New", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);  // add title to the top of the panel

        // create product grid with 3 columns and gaps between items
        JPanel productGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        productGrid.setBackground(new Color(250, 250, 240));  // match background color
        productGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // padding around grid

        // get popular products from game state
        Collection<PurchasableProduct> popularProducts = GameState.instance().getProductManager().getPopularProducts();

        // create a panel for each product and add it to the grid
        for (PurchasableProduct product : popularProducts) {
            JPanel itemPanel = new JPanel(new BorderLayout());  // use border layout for each product panel
            itemPanel.setBackground(new Color(255, 255, 250));  // slightly different background
            itemPanel.setBorder(BorderFactory.createLineBorder(new Color(66, 62, 55), 2));  // border around product

            JLabel imageLabel = createScaledImageLabel(product.getName());  // create scaled image label for product

            // create label for product name
            JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Courier New", Font.BOLD, 20));
            nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));  // vertical padding for text

            itemPanel.add(imageLabel, BorderLayout.CENTER);  // add image to center
            itemPanel.add(nameLabel, BorderLayout.SOUTH);  // add name below image

            productGrid.add(itemPanel);  // add product panel to grid
        }

        // add product grid to a scroll pane
        JScrollPane scrollPane = new JScrollPane(productGrid);
        scrollPane.setBorder(null);  // remove border around scroll pane
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  // set scroll speed
        add(scrollPane, BorderLayout.CENTER);  // add scroll pane to center of main panel
    }

    // method to create a scaled image label from product name
    private JLabel createScaledImageLabel(String name) {
        URL imgUrl = getClass().getResource("/" + name.toLowerCase() + ".png");  // load image resource based on product name

        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);  // create image icon from url
            Image img = icon.getImage();

            int originalWidth = img.getWidth(null);  // get original width
            int originalHeight = img.getHeight(null);  // get original height

            int maxDimension = 150;  // max width or height for scaling
            double scale = (double) maxDimension / Math.max(originalWidth, originalHeight);  // calculate scale factor

            int newWidth = (int) (originalWidth * scale);  // scaled width
            int newHeight = (int) (originalHeight * scale);  // scaled height

            Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);  // create scaled image
            return new JLabel(new ImageIcon(scaledImage));  // return label with scaled image
        } else {
            System.err.println("missing image: " + name);  // error message if image not found
            return new JLabel("no image");  // fallback label if image missing
        }
    }
}
