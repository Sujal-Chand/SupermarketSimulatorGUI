/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author prish
 */
public class InstructionPagePanel extends SubPagePanel {
    
    
    public InstructionPagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        JLabel titleLabel = new JLabel("Instruction Page");
        titleLabel.setBounds(30, 30, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("impact", Font.BOLD, 40));
        add(titleLabel);

        JTextArea instructionText = new JTextArea(
            """
            Explore the menus, your first day has infinite actions!
            Start by purchasing a cashier and other equipment from the equipment page.
            Then go to products and purchase products to display on shelves, fridges, freezers.
            Make sure you display your stock on the store floor by going to the storage page.
            Your goal is to get 5 stars! The game ends if rating is 0 or you run out of money!
            
            TIP: Make sure your prices each day are not too high or customers will get angry!""");
        
        instructionText.setBounds(40, 100, 700, 400);
        instructionText.setLineWrap(true);
        instructionText.setWrapStyleWord(true);
        instructionText.setEditable(false);
        instructionText.setFocusable(false);
        instructionText.setFont(new Font("SansSerif", Font.PLAIN, 18));
        instructionText.setBackground(new Color(245, 240, 235));
        instructionText.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        add(instructionText);
    }
}
