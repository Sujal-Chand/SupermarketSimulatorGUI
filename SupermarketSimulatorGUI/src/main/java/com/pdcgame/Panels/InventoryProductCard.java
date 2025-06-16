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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(120, 200));
        setBackground(Color.WHITE);

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
                imageLabel = new JLabel("[No Image]");
            }
        } catch (Exception e) {
            imageLabel = new JLabel("[Error]");
        }
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        JLabel priceLabel = new JLabel(price);
        priceLabel.setAlignmentX(CENTER_ALIGNMENT);
        priceLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        JLabel stockLabel = new JLabel("Stock: " + GameState.instance().getInventoryManager().getStoredProducts().get(name));
        stockLabel.setAlignmentX(CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        JLabel modifyLabel = new JLabel("Click to change price");
        modifyLabel.setAlignmentX(CENTER_ALIGNMENT);
        modifyLabel.setFont(new Font("Dialog", Font.BOLD, 10));

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

        // recommended price
        double recommendedPrice = GameState.instance().getProductManager().getProduct(name).getRecommendedSinglePrice();

        // Mouse click listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean changed = PriceChangeDialog.showPriceChangeDialog(InventoryProductCard.this, name, recommendedPrice);
                if (changed) {
                    // Fetch the new price from ProductManager and update label
                    double newPrice = GameState.instance().getProductManager().getSellPrice(name);
                    priceLabel.setText("Sell Price: " + String.format("$%.2f", newPrice));
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
