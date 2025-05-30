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
        
        add(new SplitPagePanel("Menu"), "Menu");
        add(new SplitPagePanel("Equipment"), "Equipment");
        add(new SplitPagePanel("Storage"), "Storage");
        add(new SplitPagePanel("Products"), "Products");
        add(new SplitPagePanel("Open Store"), "Open Store");
        
        cardLayout.show(this, "Menu");
    }
    
    public void showPanel(String name) {
        cardLayout.show(this, name);
    }
    
}
