package com.pdcgame.Panels;

import com.pdcgame.GameState;
import com.pdcgame.MoveOrRemoveDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class StockProductCard extends JPanel {

    public StockProductCard(String name, String stock, String coordinate) {
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

        JLabel stockLabel = new JLabel("Stock displayed: " + stock);
        stockLabel.setAlignmentX(CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        JLabel modifyLabel = new JLabel("Click to modify stock displayed");
        modifyLabel.setAlignmentX(CENTER_ALIGNMENT);
        modifyLabel.setFont(new Font("Dialog", Font.BOLD, 10));


        add(Box.createVerticalStrut(5));
        add(imageLabel);
        add(Box.createVerticalStrut(5));
        add(nameLabel);
        add(Box.createVerticalStrut(3));
        add(stockLabel);
        add(Box.createVerticalStrut(3));
        add(modifyLabel);
        add(Box.createVerticalGlue());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean changed = MoveOrRemoveDialog.showMoveOrRemoveDialog(StockProductCard.this, name, coordinate);
                if (changed) {
                    int newStock = GameState.instance().getFloorStorageManager().getProductQuantityAt(coordinate, name);
                    stockLabel.setText("Stock displayed: " + String.valueOf(newStock));
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
