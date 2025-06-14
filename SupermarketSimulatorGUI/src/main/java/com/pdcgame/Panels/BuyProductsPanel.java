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

public class BuyProductsPanel extends FunctionPagePanel{
    
    public BuyProductsPanel(){
    
        JLabel rightLabel = new JLabel("Buy Products Board", SwingConstants.CENTER);
        rightLabel.setForeground(Color.WHITE);
        rightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
      
        add(rightLabel, BorderLayout.NORTH);
    }
}
