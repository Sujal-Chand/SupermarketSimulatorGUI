/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.Enums.Difficulty;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author sujalchand
 */
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author sujalchand
 */
public class MenuPagePanel extends JPanel {

    private JPanel rightPanel;
    private JLabel difficultyLabel;
    private JPanel difficultyButtonPanel;
    private JButton goBackButton;
    private final BottomCardPanel bottomCardPanel;

    // timer to update button states every 1 second
    private final Timer updateTimer;

    public MenuPagePanel(BottomCardPanel bottomCardPanel) {
        this.bottomCardPanel = bottomCardPanel;

        // set layout and background color for main panel
        setLayout(new BorderLayout());
        setBackground(new Color(237, 235, 215));

        // create left panel with vertical box layout and padding
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(getBackground());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // create image label and load supermarket image, scale if available
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        URL imageUrl = getClass().getResource("/supermarket.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setText("Image Missing");
            imageLabel.setForeground(Color.RED);
        }

        // create title label centered with impact font
        JLabel titleLabel = new JLabel("Supermarket Simulator", SwingConstants.CENTER);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 48));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // create button panel for main menu buttons, vertical box layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(getBackground());
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // define main menu buttons
        JButton loadGameButton = new JButton("Load Game");
        JButton newGameButton = new JButton("New Game");
        JButton quitButton = new JButton("Quit");

        // button styling colors and dimensions
        Color btnBg = new Color(66, 62, 55);
        Color btnFg = new Color(237, 235, 215);
        Dimension buttonSize = new Dimension(400, 28);

        // apply consistent styling to all main buttons
        for (JButton button : new JButton[]{loadGameButton, newGameButton, quitButton}) {
            styleMainMenuButton(button, btnBg, btnFg, buttonSize);
        }

        // add button listeners
        quitButton.addActionListener(e -> System.exit(0)); // quit button exits app
        loadGameButton.addActionListener(e -> loadGame()); // load game button loads save
        newGameButton.addActionListener(e -> showDifficulty()); // new game button shows difficulty options

        // add buttons conditionally and with spacing
        buttonPanel.add(loadGameButton);
        loadGameButton.setEnabled(GamePersistence.saveExists());
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(quitButton);

        // add components to left panel with glue for vertical spacing
        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(titleLabel);
        leftPanel.add(imageLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createVerticalGlue());

        // create and setup the right panel for difficulty selection
        createRightPanel();

        // add left panel to center of main panel
        add(leftPanel, BorderLayout.CENTER);

        // create and start timer to update load button state every 1 second
        updateTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // enable load button only if save exists
                loadGameButton.setEnabled(GamePersistence.saveExists());

            }
        });
        updateTimer.start();
    }

    // helper method to style main menu buttons consistently
    private void styleMainMenuButton(JButton button, Color bg, Color fg, Dimension size) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Courier New", Font.BOLD, 18));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);
    }

    // create right panel components for difficulty selection
    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400, 0));
        rightPanel.setBackground(new Color(70, 63, 58));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // difficulty heading label
        difficultyLabel = new JLabel("Choose Difficulty", SwingConstants.CENTER);
        difficultyLabel.setForeground(new Color(244, 243, 238));
        difficultyLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        difficultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // descriptive labels for each difficulty level
        JLabel easyDesc = new JLabel("Easy - $5000, Customers are usually chill, few unlucky events.");
        JLabel normalDesc = new JLabel("Normal - $3500, Balanced game, unlucky events do occur.");
        JLabel hardDesc = new JLabel("Hard - $2500, Chaos. Frequent robberies and disasters.");

        // style description labels uniformly
        for (JLabel label : new JLabel[]{easyDesc, normalDesc, hardDesc}) {
            label.setForeground(new Color(244, 243, 238));
            label.setFont(new Font("Dialog", Font.BOLD, 11));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        // panel to hold difficulty buttons, transparent background
        difficultyButtonPanel = new JPanel();
        difficultyButtonPanel.setLayout(new BoxLayout(difficultyButtonPanel, BoxLayout.Y_AXIS));
        difficultyButtonPanel.setOpaque(false);
        difficultyButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // difficulty selection buttons
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        Color btnBg = new Color(70, 63, 58);
        Dimension buttonSize = new Dimension(600, 30);

        // style and add difficulty buttons to panel with spacing
        for (JButton btn : new JButton[]{easyButton, mediumButton, hardButton}) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(btnBg);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setPreferredSize(buttonSize);
            btn.setMaximumSize(buttonSize);
            btn.setMinimumSize(buttonSize);
            difficultyButtonPanel.add(btn);
            difficultyButtonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // create and style the go back button
        goBackButton = new JButton("Go Back");
        goBackButton.setBackground(btnBg);
        goBackButton.setForeground(new Color(244, 243, 238));
        goBackButton.setOpaque(true);
        goBackButton.setBorderPainted(false);
        goBackButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        goBackButton.setPreferredSize(buttonSize);
        goBackButton.setMaximumSize(buttonSize);
        goBackButton.setMinimumSize(buttonSize);
        goBackButton.addActionListener(e -> hideDifficulty());

        // add all components to right panel with spacing
        rightPanel.add(difficultyLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        rightPanel.add(easyDesc);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(normalDesc);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(hardDesc);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        rightPanel.add(difficultyButtonPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(goBackButton);

        // set listeners on difficulty buttons to update game state
        setupDifficultyButton(easyButton, Difficulty.Easy);
        setupDifficultyButton(mediumButton, Difficulty.Normal);
        setupDifficultyButton(hardButton, Difficulty.Hard);
    }

    // add action listener to difficulty buttons for setting game difficulty
    private void setupDifficultyButton(JButton button, Difficulty difficulty) {
        button.addActionListener(e -> {
            GameState.instance().setDifficulty(difficulty);
            GamePersistence.saveGame();
            System.out.println("Difficulty chosen: " + GameState.instance().getDifficulty());
            bottomCardPanel.showPanel("Default");
            PanelNavigator.getInstance().addButtons();
        });
    }

    // load saved game and show default panel
    private void loadGame() {
        if (GamePersistence.saveExists()) {
            GamePersistence.loadGame();
        }
        PanelNavigator.getInstance().switchPanel("Default");
        bottomCardPanel.showPanel("Default");
        PanelNavigator.getInstance().addButtons();
    }

    // add the right panel to show difficulty selection options
    public void showDifficulty() {
        if (rightPanel.getParent() == null) {
            add(rightPanel, BorderLayout.EAST);
        }
        revalidate();
        repaint();
    }

    // remove the right panel to hide difficulty selection options
    public void hideDifficulty() {
        remove(rightPanel);
        revalidate();
        repaint();
    }
}
