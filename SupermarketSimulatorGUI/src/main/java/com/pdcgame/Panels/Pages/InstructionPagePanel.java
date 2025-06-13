/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;
import com.pdcgame.Panels.TutorialPanel;
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
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);


        TutorialPanel tutorial = new TutorialPanel();
        tutorial.setBounds(0, 0, 900, 800);
        add(tutorial);
    }
}
