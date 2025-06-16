/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame;

/**
 *
 * @author sujalchand
 */
import com.pdcgame.Panels.BottomCardPanel;
import com.pdcgame.Panels.PanelNavigator;
import javax.swing.*;
import java.awt.*;

public class SupermarketGameGUI {
    public static void main(String[] args) {
        
        //LogSettings logSettings = new LogSettings(); // turns off Hibernate auto logging and warnings
        
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Supermarket Simulator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280, 720);
            frame.setResizable(false); // make the frame unresizable

            // create a container panel with vertical BoxLayout
            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

            // interactable CardLayout Panel
            BottomCardPanel bottomCardPanel = new BottomCardPanel();
            
            // interactable TopPanel used to switch cards
            PanelNavigator panelNavigator = PanelNavigator.getInstance(bottomCardPanel);
            
            // set fixed heights
            int frameHeight = frame.getHeight();
            int topHeight = (int)(frameHeight * 0.05);
            int bottomHeight = (int)(frameHeight * 0.95);
            
            panelNavigator.setPreferredSize(new Dimension(frame.getWidth(), topHeight));
            bottomCardPanel.setPreferredSize(new Dimension(frame.getWidth(), bottomHeight));
            
            container.add(panelNavigator);
            container.add(bottomCardPanel);
            
            frame.setContentPane(container);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
