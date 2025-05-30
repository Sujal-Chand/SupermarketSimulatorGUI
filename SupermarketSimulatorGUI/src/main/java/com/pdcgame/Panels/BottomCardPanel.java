/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author sujalchand
 */
public class BottomCardPanel extends JPanel{
    private CardLayout cardLayout;
    
    public BottomCardPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        add(new SplitPagePanel("Home"), "Home");
        add(new SplitPagePanel("Projects"), "Projects");
        add(new SplitPagePanel("Settings"), "Settings");
        
        cardLayout.show(this, "Home");
    }
    
    public void showPanel(String name) {
        cardLayout.show(this, name);
    }
    
}
