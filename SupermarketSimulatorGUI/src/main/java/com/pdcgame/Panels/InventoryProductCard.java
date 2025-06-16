package com.pdcgame.Panels;

import com.pdcgame.GameState;
import com.pdcgame.PriceChangeDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class InventoryProductCard extends JPanel {

    public InventoryProductCard(String name, String price) {
        // set vertical box layout to stack components top to bottom
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // add gray border around panel
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // set preferred size for the card
        setPreferredSize(new Dimension(120, 200));
        // set background color to white
        setBackground(Color.WHITE);

        JLabel imageLabel;
        try {
            // load product image from resources folder using product name
            URL imgUrl = getClass().getResource("/" + name + ".png");
            if (imgUrl != null) {
                // create image icon from the loaded url
                ImageIcon icon = new ImageIcon(imgUrl);
                Image img = icon.getImage();
                // get original image dimensions
                int originalWidth = img.getWidth(null);
                int originalHeight = img.getHeight(null);
                int maxDimension = 100; // max width or height for scaled image
                // calculate scale factor to maintain aspect ratio
                double scale = (double) maxDimension / Math.max(originalWidth, originalHeight);
                // calculate new scaled width and height
                int newWidth = (int) (originalWidth * scale);
                int newHeight = (int) (originalHeight * scale);
                // create scaled image with smooth scaling
                Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                // create label with scaled image icon
                imageLabel = new JLabel(new ImageIcon(scaledImage));
            } else {
                // fallback label if image not found
                imageLabel = new JLabel("[No Image]");
            }
        } catch (Exception e) {
            // fallback label if loading image throws exception
            imageLabel = new JLabel("[Error]");
        }
        // center image label horizontally
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);

        // create and configure label for product name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // create and configure label for price text
        JLabel priceLabel = new JLabel(price);
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);
        priceLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        // create and configure label to show stock quantity from inventory manager
        JLabel stockLabel = new JLabel("Stock: " + GameState.instance().getInventoryManager().getStoredProducts().get(name));
        stockLabel.setAlignmentX(CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        // small label to hint user can click to change price
        JLabel modifyLabel = new JLabel("Click to change price");
        modifyLabel.setAlignmentX(CENTER_ALIGNMENT);
        modifyLabel.setFont(new Font("Dialog", Font.BOLD, 10));

        // add vertical spacing and components to panel in order
        add(Box.createVerticalStrut(5));
        add(imageLabel);
        add(Box.createVerticalStrut(5));
        add(nameLabel);
        add(Box.createVerticalStrut(3));
        add(priceLabel);
        add(Box.createVerticalStrut(3));
        add(stockLabel);
        add(Box.createVerticalStrut(3));
        add(modifyLabel);
        add(Box.createVerticalGlue());

        // get recommended price from product manager for this product
        double recommendedPrice = GameState.instance().getProductManager().getProduct(name).getRecommendedSinglePrice();

        // add mouse listener to handle clicks on this product card
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // show price change dialog and check if price was changed
                boolean changed = PriceChangeDialog.showPriceChangeDialog(InventoryProductCard.this, name, recommendedPrice);
                if (changed) {
                    // if changed, get updated sell price from product manager
                    double newPrice = GameState.instance().getProductManager().getSellPrice(name);
                    // update price label text to show new price formatted as dollars
                    priceLabel.setText("Sell Price: " + String.format("$%.2f", newPrice));
                    // refresh layout and repaint panel
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
