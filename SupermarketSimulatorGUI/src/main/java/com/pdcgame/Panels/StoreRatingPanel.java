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

    // load full star icon image
    private final ImageIcon starIcon;
    // load half star icon image
    private final ImageIcon halfStarIcon;

    public StoreRatingPanel() {
        starIcon = loadIcon("/full_star_tran.png");
        halfStarIcon = loadIcon("/half_star_tran.png");
    }

    // loads and scales an image icon from resource path
    private ImageIcon loadIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl == null) {
            System.err.println("image not found: " + path);
            return new ImageIcon();
        }

        Image image = new ImageIcon(imgUrl).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    /**
     * creates and returns a panel with star icons representing the rating
     * @param rating the rating as a double (e.g., 3.5)
     * @return a JPanel containing full and half star icons
     */
    public JPanel getRatingPanel(double rating) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); // left aligned layout
        panel.setOpaque(false); // transparent background

        int fullStars = (int) rating; // number of full stars
        boolean hasHalfStar = (rating - fullStars) >= 0.5; // check if half star needed

        // add full star icons
        for (int i = 0; i < fullStars; i++) {
            panel.add(new JLabel(starIcon));
        }

        // add half star icon if needed
        if (hasHalfStar) {
            panel.add(new JLabel(halfStarIcon));
        }

        return panel;
    }
}
