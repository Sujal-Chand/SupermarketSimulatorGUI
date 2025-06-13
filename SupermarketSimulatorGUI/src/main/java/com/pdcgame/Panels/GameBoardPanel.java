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


public class GameBoardPanel extends JPanel{
    public GameBoardPanel(){
        
        setLayout(new BorderLayout());
        setBackground(new Color(70, 63, 58));
        setPreferredSize(new Dimension(400, 0));

        JLabel rightLabel = new JLabel("Right Panel", SwingConstants.CENTER);
        rightLabel.setForeground(Color.WHITE);
        rightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        GameGridPanel grid = new GameGridPanel();

        add(rightLabel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
    }

    
}
