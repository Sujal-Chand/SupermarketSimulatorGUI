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
        // use absolute positioning
        setLayout(null);

        // create title label
        JLabel titleLabel = new JLabel("Supermarket Simulator");
        titleLabel.setBounds(30, 30, 1000, 40); // set position and size
        titleLabel.setForeground(new Color(66, 62, 55)); // set text color
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40)); // set font

        // create and add tutorial panel
        TutorialPanel tutorial = new TutorialPanel();
        tutorial.setBounds(0, 0, 900, 800); // set position and size

        // add components to panel
        add(tutorial);
        add(titleLabel);
    }
}