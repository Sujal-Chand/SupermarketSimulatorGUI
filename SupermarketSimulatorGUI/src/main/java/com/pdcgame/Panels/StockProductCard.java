package com.pdcgame.Panels;

import com.pdcgame.GameState;
import com.pdcgame.MoveOrRemoveDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class StockProductCard extends JPanel {

    /**
     * constructor creates a card panel displaying product image, name, stock, and a hint label
     * @param name name of the product
     * @param stock current stock quantity as string
     * @param coordinate location coordinate string for this product's storage
     */
    public StockProductCard(String name, String stock, String coordinate) {
        // set vertical box layout for stacking components top to bottom
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY)); // add gray border around panel
        setPreferredSize(new Dimension(120, 200)); // fixed size for the card
        setBackground(Color.WHITE); // white background

        JLabel imageLabel;
        try {
            // try to load product image from resources folder using product name
            URL imgUrl = getClass().getResource("/" + name + ".png");
            if (imgUrl != null) {
                // if image exists, load and scale it proportionally to max dimension 100px
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
                // fallback label if no image found
                imageLabel = new JLabel("[No Image]");
            }
        } catch (Exception e) {
            // fallback label if error occurs loading image
            imageLabel = new JLabel("[Error]");
        }
        imageLabel.setAlignmentX(CENTER_ALIGNMENT); // center align image horizontally

        // label for product name
        JLabel nameLabel = new JLabel(name);
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));

        // label showing current stock quantity
        JLabel stockLabel = new JLabel("Stock displayed: " + stock);
        stockLabel.setAlignmentX(CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        // small label hinting user can click to modify stock
        JLabel modifyLabel = new JLabel("Click to modify stock displayed");
        modifyLabel.setAlignmentX(CENTER_ALIGNMENT);
        modifyLabel.setFont(new Font("Dialog", Font.BOLD, 10));

        // add vertical spacing and add components to panel in order
        add(Box.createVerticalStrut(5));
        add(imageLabel);
        add(Box.createVerticalStrut(5));
        add(nameLabel);
        add(Box.createVerticalStrut(3));
        add(stockLabel);
        add(Box.createVerticalStrut(3));
        add(modifyLabel);
        add(Box.createVerticalGlue()); // push content to top

        // add mouse click listener to allow modifying stock via dialog
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // show move/remove dialog; returns true if stock was changed
                boolean changed = MoveOrRemoveDialog.showMoveOrRemoveDialog(StockProductCard.this, name, coordinate);
                if (changed) {
                    // update stock label with new quantity after modification
                    int newStock = GameState.instance().getFloorStorageManager().getProductQuantityAt(coordinate, name);
                    stockLabel.setText("Stock displayed: " + String.valueOf(newStock));
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
