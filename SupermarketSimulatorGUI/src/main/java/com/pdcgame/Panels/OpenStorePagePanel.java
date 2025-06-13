/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import javax.swing.JPanel;

/**
 *
 * @author prish
 */

import javax.swing.*;
import java.awt.*;

public class OpenStorePagePanel extends JPanel {

    public OpenStorePagePanel() {
        
        setLayout(null);
        setBackground(new Color(236, 234, 213));
        JLabel label = new JLabel("Open Store Page");
        label.setBounds(10, 10, 200, 30); 
        label.setForeground(Color.RED);
        add(label);
        
    }
    
}
