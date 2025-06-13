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
        JLabel titleLabel = new JLabel("Equipment Page");
        titleLabel.setBounds(30, 30, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);
    }
}
