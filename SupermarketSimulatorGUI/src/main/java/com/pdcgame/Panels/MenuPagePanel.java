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
public class MenuPagePanel extends JPanel {

    private JPanel rightPanel;
    private JLabel difficultyLabel;
    private JPanel difficultyButtonPanel;
    private JButton goBackButton;
    private final BottomCardPanel bottomCardPanel;

    public MenuPagePanel(BottomCardPanel bottomCardPanel) {
        this.bottomCardPanel = bottomCardPanel;
        setLayout(new BorderLayout());
        setBackground(new Color(237, 235, 215));
        

        // left Panel creation and addition of elements
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(getBackground());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        JLabel titleLabel = new JLabel("Supermarket Simulator", SwingConstants.CENTER);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 48));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(getBackground());
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton loadGameButton = new JButton("Load Game");
        JButton newGameButton = new JButton("New Game");
        JButton quitButton = new JButton("Quit");

        Color btnBg = new Color(66, 62, 55);
        Color btnFg = new Color(237, 235, 215);
        Dimension buttonSize = new Dimension(400, 28);

        for (JButton button : new JButton[]{loadGameButton, newGameButton, quitButton}) {
            button.setBackground(btnBg);
            button.setForeground(btnFg);
            button.setFont(new Font("Courier New", Font.BOLD, 18));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
        }

        // button listeners
        quitButton.addActionListener(e -> System.exit(0));
        newGameButton.addActionListener(e -> showDifficulty());

        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(quitButton);

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(titleLabel);
        leftPanel.add(imageLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createVerticalGlue());

        // right panel elements creation
        createRightPanel();

        // add the left panel
        add(leftPanel, BorderLayout.CENTER);
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400, 0));
        rightPanel.setBackground(new Color(70, 63, 58));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        difficultyLabel = new JLabel("Choose Difficulty", SwingConstants.CENTER);
        difficultyLabel.setForeground(new Color(244, 243, 238));
        difficultyLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        difficultyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel easyDesc = new JLabel("Easy - $5000, Customers are usually chill, few unlucky events.");
        JLabel normalDesc = new JLabel("Normal - $3500, Balanced game, unlucky events do occur.");
        JLabel hardDesc = new JLabel("Hard - $2500, Chaos. Frequent robberies and disasters.");

        for (JLabel label : new JLabel[]{easyDesc, normalDesc, hardDesc}) {
            label.setForeground(new Color(244, 243, 238));
            label.setFont(new Font("Dialog", Font.BOLD, 11));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        difficultyButtonPanel = new JPanel();
        difficultyButtonPanel.setLayout(new BoxLayout(difficultyButtonPanel, BoxLayout.Y_AXIS));
        difficultyButtonPanel.setOpaque(false);
        difficultyButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        Color btnBg = new Color(70, 63, 58);

        Dimension buttonSize = new Dimension(600, 30);

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
        
        //difculty buttons 
        setupDifficultyButton(easyButton, Difficulty.Easy);
        setupDifficultyButton(mediumButton, Difficulty.Normal);
        setupDifficultyButton(hardButton, Difficulty.Hard);

        
    }
    
    //set game instance to chose difculty and switch screens
    private void setupDifficultyButton(JButton button, Difficulty difficulty) {
    button.addActionListener(e -> {
        GameState.instance().setDifficulty(difficulty);
        GamePersistence.saveGame();
        System.out.println("Difficulty chosen: "+GameState.instance().getDifficulty());
        bottomCardPanel.showPanel("Default");
    });
    }


    // show the right pannel
    public void showDifficulty() {
        if (rightPanel.getParent() == null) {
            add(rightPanel, BorderLayout.EAST);
        }
        revalidate();
        repaint();
    }
    
    // remove the right panel 
    public void hideDifficulty() {
        remove(rightPanel);
        revalidate();
        repaint();
    }
}