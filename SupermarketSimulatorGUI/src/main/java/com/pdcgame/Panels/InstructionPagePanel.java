/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.GameState;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author prish
 */
public class InstructionPagePanel extends SubPagePanel {
    
        public InstructionPagePanel() {
        setLayout(null);
        JLabel label = new JLabel("Instruction Page");
        label.setBounds(10, 10, 200, 30); 
        label.setForeground(Color.RED);
        add(label);
        
        StoreRatingPanel ratingDisplay = new StoreRatingPanel();
        JPanel ratingPanel = ratingDisplay.getRatingPanel(3.5); //change to match gamestate instance
        ratingPanel.setBounds(10, 50, 600, 120);
        add(ratingPanel);
        
    }
    
    
    
}
