package com.pdcgame.Panels;

import com.pdcgame.GameState;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Map;

public class ProductCard extends JPanel {

    public ProductCard(String name, String price) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(120, 200));
        setBackground(Color.WHITE);


        // Load image from resource
        JLabel imageLabel;
        try {
            URL imgUrl = getClass().getResource("/" + name + ".png");
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage();

                int originalWidth = img.getWidth(null);
                int originalHeight = img.getHeight(null);

                int maxDimension = 100;
                double scale = (double) maxDimension / Math.max(originalWidth, originalHeight);

                int newWidth = (int) (originalWidth * scale);
                int newHeight = (int) (originalHeight * scale);

                Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(scaledImage));
            } else {
                // Fallback if image not found
                imageLabel = new JLabel("[No Image]");
            }
        } catch (Exception e) {
            imageLabel = new JLabel("[Error]");
        }

        imageLabel.setAlignmentX(CENTER_ALIGNMENT);

        // Name label
        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // Price label
        JLabel priceLabel = new JLabel(price);
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);
        priceLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        // Stock label
        JLabel stockLabel = new JLabel("Stock: " + GameState.instance().getInventoryManager().getStoredProducts().get(name));
        stockLabel.setAlignmentX(CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        // Add components
        add(Box.createVerticalStrut(5));
        add(imageLabel);
        add(Box.createVerticalStrut(5));
        add(nameLabel);
        add(Box.createVerticalStrut(3));
        add(priceLabel);
        add(Box.createVerticalStrut(3));
        add(stockLabel);
        add(Box.createVerticalGlue());

    }

}
