/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 *
 * @author prish
 */

import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;

public class EquipmentPagePanel extends SubPagePanel {
    
    public EquipmentPagePanel() {
        setLayout(null);
        JLabel label = new JLabel("Equipment Page");
        label.setBounds(10, 10, 200, 30); 
        label.setForeground(Color.RED);
        add(label);
    }
}
