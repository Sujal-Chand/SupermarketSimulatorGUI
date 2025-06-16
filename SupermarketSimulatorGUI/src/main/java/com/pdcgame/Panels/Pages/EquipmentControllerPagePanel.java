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
import com.pdcgame.Panels.ManageEquipmentPanel;
import com.pdcgame.Panels.SubPagePanel;
import javax.swing.*;
import java.awt.*;

public class EquipmentControllerPagePanel extends SubPagePanel {
    // singleton instance of this panel
    private static final EquipmentControllerPagePanel instance = new EquipmentControllerPagePanel();

    // layout to switch between different equipment panels
    private final CardLayout cardLayout;
    // panel that holds different cards (buy/manage)
    private final JPanel cardPanel;

    // get the singleton instance
    public static EquipmentControllerPagePanel getInstance() {
        return instance;
    }

    public EquipmentControllerPagePanel() {
        // set layout to border layout
        setLayout(new BorderLayout());

        // initialize card layout and panel that uses it
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // add card panel to center of this panel
        add(cardPanel, BorderLayout.CENTER);

        // setup initial view based on selected cell
        updateView();
    }

    // updates the visible card based on currently selected cell
    public void updateView() {
        // clear all cards from card panel
        cardPanel.removeAll();

        // get coordinates of currently selected cell on the board
        int[] selectedCoords = GameState.instance().getBoardManager().getSelectedCell();
        // get the board cell at the selected coordinates
        BoardCell cell = GameState.instance().getBoardManager().getCell(selectedCoords[0], selectedCoords[1]);

        // if cell is empty show buy equipment panel
        if (cell == BoardCell.EMPTY) {
            showBuyEquipmentPanel();
        } else {
            // otherwise show manage equipment panel
            showManageEquipmentPanel();
        }

        // refresh UI after changes
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    // helper method to show buy equipment card
    private void showBuyEquipmentPanel() {
        BuyEquipmentPanel buyPanel = new BuyEquipmentPanel();
        cardPanel.add(buyPanel, "BUY");
        cardLayout.show(cardPanel, "BUY");
    }

    // helper method to show manage equipment card
    private void showManageEquipmentPanel() {
        ManageEquipmentPanel managePanel = new ManageEquipmentPanel();
        cardPanel.add(managePanel, "MANAGE");
        cardLayout.show(cardPanel, "MANAGE");
    }
}

