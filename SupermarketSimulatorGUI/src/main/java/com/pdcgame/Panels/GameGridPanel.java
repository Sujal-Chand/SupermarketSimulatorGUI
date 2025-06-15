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
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGridPanel extends JPanel {
    private JLabel[][] labelGrid; // store references to labels
    private JPanel gridPanel;
    private final int ROWS = 10;
    private final int COLS = 10;
    private final Color labelColor = new Color(138, 129, 124);
    private final Color textColor = new Color(244, 243, 238);

    public GameGridPanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(400, 400));

        gridPanel = new JPanel(new GridLayout(ROWS, COLS, 2, 2));
        gridPanel.setOpaque(false);
        gridPanel.setPreferredSize(new Dimension(350, 350));

        labelGrid = new JLabel[COLS][ROWS];
        initializeGrid();

        add(gridPanel, new GridBagConstraints());
        updateBoard();
        javax.swing.Timer timer = new javax.swing.Timer(1000, e -> updateBoard());
        timer.start();
    }

    private void initializeGrid() {
        Dimension labelSize = new Dimension(80, 80);

        for (int i = 0; i < 100; i++) {
            int x = i % 10;
            int y = 9 - i / 10;

            JLabel label = new JLabel("", SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(labelColor);
            label.setForeground(textColor);
            label.setPreferredSize(labelSize);
            label.setFont(new Font("Dialog", Font.BOLD, 10));
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            int[] finalCoords = new int[2];
            finalCoords[0] = x;
            finalCoords[1] = y;
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    label.setBackground(labelColor.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    label.setBackground(labelColor);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    GameState.instance().getBoardManager().setSelectedCell(finalCoords);
                    System.out.println("Label x,y = " + finalCoords[0] + ", " + finalCoords[1] + " was clicked!");
                    PanelNavigator.getInstance().switchPanel("Equipment");
                }
            });

            labelGrid[x][y] = label;
            gridPanel.add(label);
        }
    }

    // Call this function to update the grid with new icons
    public void updateBoard() {
        BoardCell[][] board = GameState.instance().getBoardManager().get2DBoard();

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                labelGrid[x][y].setText(board[y][x].getIcon());
            }
        }

        revalidate();
        repaint();
    }
}