/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author prish
 */
public class InstructionPagePanel extends SubPagePanel {
    
    private int currentStep = 0;

    private static class TutorialStep {
        String text;
        int x, y, width, height;

        TutorialStep(String text, int x, int y, int width, int height) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private final TutorialStep[] steps = new TutorialStep[]{
        new TutorialStep("Explore the menus.", 30, 80, 400, 70),
        new TutorialStep("Your first day has infinite actions!", 250, 500, 500, 70),
        new TutorialStep("Buy a cashier and equipment from the equipment page.", 350, 20, 450, 70),
        new TutorialStep("Go to products and purchase stock for shelves.", 350, 20, 500, 70),
        new TutorialStep("Display your stock from the storage page.", 350, 20, 420, 70),
        new TutorialStep("Reach 5 stars! Game ends if rating is 0 or you go bankrupt.", 270, 500, 600, 70),
        new TutorialStep("TIP: Don’t set prices too high, or customers will be angry!", 220, 500, 650, 70),
        new TutorialStep("Tutorial complete! You're ready to begin.", 30, 80, 400, 70)
    };

    private final JPanel instructionBox;
    private final JLabel instructionText;
    private final JButton nextButton;

    public InstructionPagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        JLabel titleLabel = new JLabel("Instruction Page");
        titleLabel.setBounds(30, 30, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        instructionBox = new JPanel(null);
        instructionBox.setBackground(new Color(245, 240, 235));
        instructionBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        instructionText = new JLabel();
        instructionText.setFont(new Font("Courier New", Font.BOLD, 16));
        instructionText.setBounds(10, 5, 350, 30);
        instructionBox.add(instructionText);

        nextButton = new JButton("→");
        nextButton.setFont(new Font("Courier New", Font.PLAIN, 25));
        nextButton.setBackground(new Color(66, 62, 55));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBounds(0, 0, 60, 30);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advanceStep();
            }
        });

        instructionBox.add(nextButton);
        add(instructionBox);

        updateInstruction();
    }

    private void updateInstruction() {
        if (currentStep >= steps.length) {
            remove(instructionBox);
            repaint();
            revalidate();
            return;
        }

        TutorialStep step = steps[currentStep];
        instructionBox.setBounds(step.x, step.y, step.width, step.height);
        instructionText.setText("<html>" + step.text + "</html>");

        instructionText.setBounds(10, 5, step.width - 90, step.height - 15);
        nextButton.setBounds(step.width - 80, step.height - 55, 70, 40);

    }

    private void advanceStep() {
        currentStep++;
        updateInstruction();
    }
}
