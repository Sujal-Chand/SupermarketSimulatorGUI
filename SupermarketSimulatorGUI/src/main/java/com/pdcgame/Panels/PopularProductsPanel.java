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
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 240));

        JLabel title = new JLabel("This Week's Popular Products", SwingConstants.CENTER);
        title.setFont(new Font("Courier New", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel productGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        productGrid.setBackground(new Color(250, 250, 240));
        productGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Collection<PurchasableProduct> popularProducts = GameState.instance().getProductManager().getPopularProducts();

        for (PurchasableProduct product : popularProducts) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBackground(new Color(255, 255, 250));
            itemPanel.setBorder(BorderFactory.createLineBorder(new Color(66, 62, 55), 2));

            JLabel imageLabel = createScaledImageLabel(product.getName());

            JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
            nameLabel.setFont(new Font("Courier New", Font.BOLD, 20));
            nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

            itemPanel.add(imageLabel, BorderLayout.CENTER);
            itemPanel.add(nameLabel, BorderLayout.SOUTH);

            productGrid.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(productGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JLabel createScaledImageLabel(String name) {
        URL imgUrl = getClass().getResource("/" + name.toLowerCase() + ".png");

        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage();

            int originalWidth = img.getWidth(null);
            int originalHeight = img.getHeight(null);

            int maxDimension = 150;
            double scale = (double) maxDimension / Math.max(originalWidth, originalHeight);

            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } else {
            System.err.println("Missing image: " + name);
            return new JLabel("No image");
        }
    }
}
