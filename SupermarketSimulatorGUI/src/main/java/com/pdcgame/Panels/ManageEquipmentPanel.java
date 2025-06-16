package com.pdcgame.Panels;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.Managers.BuilderManager;
import com.pdcgame.Panels.Pages.EquipmentControllerPagePanel;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author sujalchand
 */
public class ManageEquipmentPanel extends JPanel {

    public ManageEquipmentPanel() {
        // get currently selected cell coordinates from game state
        int[] cell = GameState.instance().getBoardManager().getSelectedCell();
        // convert coordinates to string format for storage lookup
        String stringCoordinates = IOHandler.instance().coordinatesToString(cell);
        // get the board cell object at selected coordinates
        BoardCell selectedCell = GameState.instance().getBoardManager().getCell(cell[0], cell[1]);
        // get cell name and format by replacing underscores with spaces
        String cellName = selectedCell.name().replace("_", " ");
        // check how many storage spaces are available at this location
        int availableStorage = GameState.instance().getFloorStorageManager().getAvailableSpacesAt(stringCoordinates);

        // use absolute positioning for components
        setLayout(null);
        setBackground(new Color(250, 250, 240));

        // create and add title label with cell name
        JLabel titleLabel = new JLabel(cellName);
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        // create and add sub-label showing selected cell coordinates
        JLabel subLabel = new JLabel("Selected Cell at x = " + cell[0] + " and y = " + cell[1]);
        subLabel.setBounds(20, 60, 400, 30);
        subLabel.setForeground(new Color(66, 62, 55));
        subLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(subLabel);

        // if the cell can store products, show available storage label
        if (selectedCell.canStoreProducts()) {
            JLabel stockLabel = new JLabel("Available Storage: " + availableStorage);
            stockLabel.setBounds(300, 60, 400, 30);
            stockLabel.setForeground(new Color(66, 62, 55));
            stockLabel.setFont(new Font("Dialog", Font.BOLD, 16));
            add(stockLabel);
        }

        // create and add sell button, positioned below sub-label
        JButton sellButton = new JButton("Sell");
        sellButton.setBounds(20, 100, 100, 30);
        sellButton.setFont(new Font("Dialog", Font.PLAIN, 14));
        sellButton.addActionListener(e -> {
            // attempt to sell the item in the selected cell via BuilderManager
            InternalCases outcome = BuilderManager.trySellItem(selectedCell, stringCoordinates);

            // handle different outcomes from selling attempt
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
                    // calculate sell price with 10% deduction
                    double sellPrice = selectedCell.getItemCost() * 0.9;
                    // confirm sale with user
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to sell " + selectedCell.name().toLowerCase() +
                                    " for $" + String.format("%.2f", sellPrice) + "?",
                            "Confirm Sale",
                            JOptionPane.YES_NO_OPTION);

                    // if user confirms, proceed with selling and show confirmation
                    if (confirm == JOptionPane.YES_OPTION) {
                        BuilderManager.sellItem(selectedCell, cell, stringCoordinates);
                        JOptionPane.showMessageDialog(this,
                                "Sold " + selectedCell.name().toLowerCase() +
                                        " for $" + String.format("%.2f", sellPrice),
                                "Item Sold",
                                JOptionPane.INFORMATION_MESSAGE);
                        EquipmentControllerPagePanel.getInstance().updateView();
                    }
                }

                // fallback case for null or unknown outcome
                case null, default -> JOptionPane.showMessageDialog(this,
                        "Item could not be sold.",
                        "Sell Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        add(sellButton);

        // if cell can store products, show the stock grid panel inside a scroll pane
        if (selectedCell.canStoreProducts()) {
            StockGridPanel stockGridPanel = new StockGridPanel();

            JScrollPane scrollPane = new JScrollPane(stockGridPanel);
            scrollPane.setBounds(20, 140, 820, 400);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            add(scrollPane);

            // populate stock grid with filtered purchasable products matching cell's storage type
            stockGridPanel.setListProducts(GameState.instance().getProductManager().getFilteredPurchasableProducts(selectedCell.getStorageType()), cell);
        }
    }
}
