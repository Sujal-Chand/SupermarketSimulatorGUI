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

public class ProductPagePanel extends SubPagePanel {
    
    public ProductPagePanel() {
        setLayout(null);
        JLabel label = new JLabel("Product Page");
        label.setBounds(10, 10, 200, 30); 
        label.setForeground(Color.RED);
        add(label);
    }
}
