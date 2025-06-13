/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author prish
 */
public class InstructionPagePanel extends SubPagePanel {
    
        public InstructionPagePanel() {
        setLayout(null);
        JLabel label = new JLabel("instruction Page");
        label.setBounds(10, 10, 200, 30); 
        label.setForeground(Color.RED);
        add(label);
    }
    
    
    
}
