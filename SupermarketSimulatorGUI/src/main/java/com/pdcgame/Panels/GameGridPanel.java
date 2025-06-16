/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.GameState;
import com.pdcgame.Panels.Pages.EquipmentControllerPagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGridPanel extends JPanel {
    // 2D array to store references to grid cell labels
    private JLabel[][] labelGrid;

    // JPanel to hold the grid of labels
    private JPanel gridPanel;

    // Grid dimensions
    private final int ROWS = 10;
    private final int COLS = 10;

    // Colors used for label background and text
    private final Color labelColor = new Color(138, 129, 124);
    private final Color textColor = new Color(244, 243, 238);

    public GameGridPanel() {
        setLayout(new GridBagLayout());  // use GridBagLayout to center gridPanel nicely
        setOpaque(false);
        setPreferredSize(new Dimension(400, 400));

        // Initialize the grid panel with GridLayout, 2px gaps between cells
        gridPanel = new JPanel(new GridLayout(ROWS, COLS, 2, 2));
        gridPanel.setOpaque(false);
        gridPanel.setPreferredSize(new Dimension(350, 350));

        // Initialize label grid array
        labelGrid = new JLabel[COLS][ROWS];

        initializeGrid();  // create and add labels to gridPanel

        add(gridPanel, new GridBagConstraints());  // add gridPanel to this panel

        updateBoard();  // initial update of board display

        // Timer to update the board every 1 second (1000ms)
        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> updateBoard());
        timer.start();
    }

    // Initialize grid labels and add mouse listeners for interaction
    private void initializeGrid() {
        Dimension labelSize = new Dimension(80, 80);

        for (int i = 0; i < ROWS * COLS; i++) {
            // Calculate x and y coordinates; y is inverted (0 at bottom)
            int x = i % COLS;
            int y = ROWS - 1 - i / COLS;

            JLabel label = new JLabel("", SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(labelColor);
            label.setForeground(textColor);
            label.setPreferredSize(labelSize);
            label.setFont(new Font("Dialog", Font.BOLD, 10));
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            // Capture final coordinates for the inner class
            int[] finalCoords = new int[]{x, y};

            // Add mouse listeners for hover effect and clicks
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    label.setBackground(labelColor.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    int[] selected = GameState.instance().getBoardManager().getSelectedCell();
                    // Keep darker color for selected cell, otherwise normal color
                    if (finalCoords[0] == selected[0] && finalCoords[1] == selected[1]) {
                        label.setBackground(labelColor.darker().darker().darker());
                    } else {
                        label.setBackground(labelColor);
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    GameState.instance().getBoardManager().setSelectedCell(finalCoords);
                    System.out.println("Label x,y = " + finalCoords[0] + ", " + finalCoords[1] + " was clicked!");
                    PanelNavigator.getInstance().switchPanel("Equipment");
                    EquipmentControllerPagePanel.getInstance().updateView();
                }
            });

            // Save label in array and add to grid panel
            labelGrid[x][y] = label;
            gridPanel.add(label);
        }
    }

    // Update the grid labels based on the current game board state
    public void updateBoard() {
        BoardCell[][] board = GameState.instance().getBoardManager().get2DBoard();
        int[] selectedCell = GameState.instance().getBoardManager().getSelectedCell();

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                JLabel label = labelGrid[x][y];
                // Update label text with the icon string from the board cell
                label.setText(board[y][x].getIcon());

                // Highlight the selected cell with a darker background
                if (x == selectedCell[0] && y == selectedCell[1]) {
                    label.setBackground(labelColor.darker().darker().darker());
                } else {
                    label.setBackground(labelColor);
                }
            }
        }

        revalidate();  // refresh layout
        repaint();     // repaint the panel
    }
}
