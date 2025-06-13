/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class StoreRatingPanel {

    private final ImageIcon starIcon;
    private final ImageIcon halfStarIcon;

    public StoreRatingPanel() {
        starIcon = loadIcon("/full_star_tran.png");
        halfStarIcon = loadIcon("/half_star_tran.png");
    }

    private ImageIcon loadIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl == null) {
            System.err.println("Image not found: " + path);
            return new ImageIcon();
        }

        Image image = new ImageIcon(imgUrl).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public JPanel getRatingPanel(double rating) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setOpaque(false);

        int fullStars = (int) rating;
        boolean hasHalfStar = (rating - fullStars) >= 0.5;

        for (int i = 0; i < fullStars; i++) {
            panel.add(new JLabel(starIcon));
        }

        if (hasHalfStar) {
            panel.add(new JLabel(halfStarIcon));
        }

        return panel;
    }
}