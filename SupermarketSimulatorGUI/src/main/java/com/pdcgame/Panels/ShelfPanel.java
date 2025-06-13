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

    private final Image backgroundImage;
    private final List<ShelfItem> shelfItems = new ArrayList<>();

    public ShelfPanel() {
        backgroundImage = loadImage("/shelf.png");
        setPreferredSize(new Dimension(700, 500));
        setLayout(null);
        setOpaque(false);
        
        populateShelf();
        
    }

    private void populateShelf() {
        int x = 40, y = 65, w = 80, h = 80;
        int spacing = 80;

        for (PurchasableProduct product : GameState.instance().getProductManager().getFilteredPurchasableProducts(ProductStorageType.SHELF)) {
            String imagePath = "/" + product.getName().toLowerCase() + ".png";
            addShelfItem(imagePath, x, y, w, h, product);

            x += spacing;
            if (x > 500) {
                x = 40;
                y += spacing;
            }
        }

        for (ShelfItem item : shelfItems) {
            JButton btn = new JButton(item.icon);
            btn.setBounds(item.x, item.y, item.width, item.height);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setOpaque(false);

            btn.addActionListener(e -> {
                System.out.println("Clicked: " + item.product.getName());
            });

            add(btn);
        }
    }
   
    private void addShelfItem(String imagePath, int x, int y, int width, int height, Product product) {
    ImageIcon icon = loadAndScaleIcon(imagePath, width, height);
    shelfItems.add(new ShelfItem(icon, x, y, width, height, product));
    }

    private Image loadImage(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl == null) {
            System.err.println("Shelf image not found: " + path);
            return null;
        }
        return new ImageIcon(imgUrl).getImage();
    }

    private ImageIcon loadAndScaleIcon(String path, int width, int height) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl == null) {
            System.err.println("Button image not found: " + path);
            return new ImageIcon();
        }
        Image img = new ImageIcon(imgUrl).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
