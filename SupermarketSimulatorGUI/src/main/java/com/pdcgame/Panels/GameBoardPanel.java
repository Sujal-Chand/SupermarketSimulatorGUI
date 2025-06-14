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


public class GameBoardPanel extends FunctionPagePanel{
    public GameBoardPanel(){
        
        JLabel titleLabel = new JLabel("Game Board", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        GameGridPanel grid = new GameGridPanel();

        add(titleLabel, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
    }
}
