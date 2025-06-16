package com.pdcgame;

import com.pdcgame.Enums.ActionSource;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Panels.Pages.InventoryControllerPagePanel;

import javax.swing.*;
import java.awt.*;

public class PriceChangeDialog {

    /**
     * Opens a dialog to change the price of a product.
     * This code was written partially by ChatGPT
     *
     * @param parentComponent  the parent component for the dialog (for positioning)
     * @param productName      the name of the product
     * @param recommendedPrice the recommended price
     * @return true if price was changed, false if cancelled or error
     */
    public static boolean showPriceChangeDialog(Component parentComponent, String productName, double recommendedPrice) {
        if (!ActionManager.enoughActions()) {
            JOptionPane.showMessageDialog(parentComponent,
                    "You don't have enough actions to change this item's price!",
                    "Not enough actions", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(new JLabel("Enter new price for " + productName + ":"), BorderLayout.NORTH);
        JTextField priceField = new JTextField();
        inputPanel.add(priceField, BorderLayout.CENTER);

        String[] options = {"Set", "Use Recommended ($" + String.format("%.2f", recommendedPrice) + ")", "Cancel"};
        int result = JOptionPane.showOptionDialog(
                parentComponent,
                inputPanel,
                "Change Price",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (result) {
            case JOptionPane.YES_OPTION -> {
                try {
                    double newPrice = Double.parseDouble(priceField.getText().trim());
                    if (newPrice < 0) throw new NumberFormatException();

                    newPrice = Math.round(newPrice * 100.0) / 100.0;

                    GameState.instance().getProductManager().setSellPrice(productName, newPrice);
                    JOptionPane.showMessageDialog(parentComponent,
                            "Changed price of " + productName + " to $" + String.format("%.2f", newPrice),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    ActionManager.futureConsume(ActionSource.CHANGE_PRICE);
                    GamePersistence.saveGame();
                    return true;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Please enter a valid number.",
                            "Invalid input", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            case JOptionPane.NO_OPTION -> {
                double roundedRecommended = Math.round(recommendedPrice * 100.0) / 100.0;
                GameState.instance().getProductManager().setSellPrice(productName, roundedRecommended);
                JOptionPane.showMessageDialog(parentComponent,
                        "Set price of " + productName + " to recommended: $" + String.format("%.2f", roundedRecommended),
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                ActionManager.futureConsume(ActionSource.CHANGE_PRICE);
                GamePersistence.saveGame();
                return true;
            }

            default -> {
                // Cancel or closed dialog
                return false;
            }
        }
    }
}
