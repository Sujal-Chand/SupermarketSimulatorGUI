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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialPanel extends JPanel {

    private int currentStep = 0; // current tutorial step index

    // array of tutorial steps with text and box position/size
    private final TutorialStep[] steps = new TutorialStep[] {
            new TutorialStep("Explore the menus.", 30, 80, 400, 70),
            new TutorialStep("Your first day has infinite actions!", 250, 500, 500, 70),
            new TutorialStep("Buy a cashier and equipment from the equipment page.", 350, 20, 450, 70),
            new TutorialStep("Go to products and purchase stock for shelves.", 350, 20, 500, 70),
            new TutorialStep("Display your stock from the storage page.", 350, 20, 420, 70),
            new TutorialStep("Reach 5 stars! Game ends if rating is 0 or you go bankrupt.", 270, 500, 600, 70),
            new TutorialStep("TIP: Don’t set prices too high, or customers will be angry!", 220, 500, 650, 70),
            new TutorialStep("Tutorial complete! You're ready to begin.", 30, 80, 400, 70)
    };

    private final JPanel instructionBox; // panel containing instruction text and next button
    private final JLabel instructionText; // label displaying instruction text
    private final JButton nextButton; // button to advance tutorial steps

    public TutorialPanel() {
        setLayout(null); // use absolute positioning
        setOpaque(false); // transparent background

        instructionBox = new JPanel(null); // panel with absolute layout for instruction box
        instructionBox.setBackground(new Color(250, 250, 240)); // light background color
        instructionBox.setBorder(BorderFactory.createCompoundBorder( // gray border with padding
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        instructionText = new JLabel(); // create label for instruction text
        instructionText.setFont(new Font("Courier New", Font.BOLD, 16)); // set font style and size
        instructionText.setBounds(10, 5, 350, 30); // initial bounds inside instruction box
        instructionBox.add(instructionText); // add label to instruction box

        nextButton = new JButton("→"); // button to go to next step
        nextButton.setFont(new Font("Courier New", Font.PLAIN, 25)); // font for button text
        nextButton.setBackground(new Color(66, 62, 55)); // dark background color
        nextButton.setForeground(Color.WHITE); // white text color
        nextButton.setBounds(0, 0, 70, 40); // initial button size and position

        // add click listener to advance tutorial steps when button is pressed
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advanceStep();
            }
        });

        instructionBox.add(nextButton); // add button to instruction box
        add(instructionBox); // add instruction box to TutorialPanel

        updateInstruction(); // initialize instruction display
    }

    // update instruction box position, size, and text based on current step
    private void updateInstruction() {
        if (currentStep >= steps.length) {
            remove(instructionBox); // remove instruction box when tutorial ends
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

    // increment current step and update instruction display
    private void advanceStep() {
        currentStep++;
        updateInstruction();
    }

    // inner class to represent a tutorial step's text and instruction box layout
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
}
