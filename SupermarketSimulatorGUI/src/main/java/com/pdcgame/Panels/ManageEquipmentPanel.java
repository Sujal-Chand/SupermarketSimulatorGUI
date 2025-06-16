package com.pdcgame.Panels;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.Managers.BuilderManager;

import javax.swing.*;
import java.awt.*;

public class ManageEquipmentPanel extends JPanel {

    public ManageEquipmentPanel() {
        int[] cell = GameState.instance().getBoardManager().getSelectedCell();
        String stringCoordinates = IOHandler.instance().coordinatesToString(cell);
        String cellName = GameState.instance().getBoardManager().getCell(cell[0], cell[1]).name().replace("_"," ");
        BoardCell selectedCell = GameState.instance().getBoardManager().getCell(cell[0], cell[1]);
        int availableStorage = GameState.instance().getFloorStorageManager().getAvailableSpacesAt(stringCoordinates);
        setLayout(null);

        JLabel titleLabel = new JLabel(cellName);
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        JLabel subLabel = new JLabel("Selected Cell at x = " + cell[0] + " and y = " + cell[1]);
        subLabel.setBounds(20, 60, 400, 30);
        subLabel.setForeground(new Color(66, 62, 55));
        subLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(subLabel);

        if(selectedCell.canStoreProducts()) {
            JLabel stockLabel = new JLabel("Available Storage: " + availableStorage);
            stockLabel.setBounds(300, 60, 400, 30);
            stockLabel.setForeground(new Color(66, 62, 55));
            stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));
            add(stockLabel);
        }

        JButton sellButton = new JButton("Sell");
        sellButton.setBounds(20, 100, 100, 30); // Positioned below subLabel
        sellButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        sellButton.addActionListener(e -> {


            InternalCases outcome = BuilderManager.trySellItem(selectedCell, stringCoordinates);

            switch (outcome) {
                case EMPTY -> JOptionPane.showMessageDialog(this,
                        "This cell is already empty.",
                        "Sell Failed",
                        JOptionPane.WARNING_MESSAGE);

                case NO_ACTIONS -> JOptionPane.showMessageDialog(this,
                        "You don't have enough actions to sell equipment!",
                        "Sell Failed",
                        JOptionPane.WARNING_MESSAGE);

                case MIN_AMOUNT -> JOptionPane.showMessageDialog(this,
                        "You need at least one cashier!",
                        "Sell Failed",
                        JOptionPane.WARNING_MESSAGE);

                case SPACE_TAKEN -> JOptionPane.showMessageDialog(this,
                        "There are products in this location that need to be removed first!",
                        "Sell Failed",
                        JOptionPane.WARNING_MESSAGE);

                case SUCCESS -> {
                    double sellPrice = selectedCell.getItemCost() * 0.9;
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to sell " + selectedCell.name().toLowerCase() +
                                    " for $" + String.format("%.2f", sellPrice) + "?",
                            "Confirm Sale",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        BuilderManager.sellItem(selectedCell, cell, stringCoordinates);
                        JOptionPane.showMessageDialog(this,
                                "Sold " + selectedCell.name().toLowerCase() +
                                        " for $" + String.format("%.2f", sellPrice),
                                "Item Sold",
                                JOptionPane.INFORMATION_MESSAGE);

                    }
                }

                case null, default -> JOptionPane.showMessageDialog(this,
                        "Item could not be sold.",
                        "Sell Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        add(sellButton);

        if(GameState.instance().getBoardManager().getCell(cell[0], cell[1]).canStoreProducts()) {
            StockGridPanel stockGridPanel = new StockGridPanel();

            JScrollPane scrollPane = new JScrollPane(stockGridPanel);
            scrollPane.setBounds(20, 140, 820, 400);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            add(scrollPane);

            stockGridPanel.setListProducts(GameState.instance().getProductManager().getFilteredPurchasableProducts(selectedCell.getStorageType()), cell);
        }
    }
}
