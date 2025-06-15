/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 *
 * @author prish
 */

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.GameState;
import com.pdcgame.Panels.BuyEquipmentPanel;
import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;

public class EquipmentControllerPagePanel extends SubPagePanel {
    private static final EquipmentControllerPagePanel instance =  new EquipmentControllerPagePanel();
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public static EquipmentControllerPagePanel getInstance() {
        return instance;
    }

    public EquipmentControllerPagePanel() {
        setLayout(new BorderLayout());

        // Setup CardLayout and container
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        updateView();
    }

    public void updateView() {
        cardPanel.removeAll();

        int[] selectedCoords = GameState.instance().getBoardManager().getSelectedCell();
        BoardCell cell = GameState.instance().getBoardManager().getCell(selectedCoords[0], selectedCoords[1]);

        if (cell == BoardCell.EMPTY) {
            BuyEquipmentPanel buyPanel = new BuyEquipmentPanel();
            cardPanel.add(buyPanel, "BUY");
            cardLayout.show(cardPanel, "BUY");
        }

        // Could add more conditions here later for MANAGE, etc.

        // Revalidate and repaint to ensure updates are shown
        cardPanel.revalidate();
        cardPanel.repaint();
    }
}
