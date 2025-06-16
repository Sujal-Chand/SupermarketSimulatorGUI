package com.pdcgame;

import com.pdcgame.Enums.ActionSource;
import com.pdcgame.Managers.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MoveOrRemoveDialog {

    /**
     * Shows a dialog to move/remove stock for a product between inventory and a specific location.
     * This code was written partially by ChatGPT
     *
     * @param parentComponent    the component to anchor the dialog
     * @param productName        the name of the product
     * @param locationCoordinate the location coordinate (e.g. "A1")
     * @return true if action successful, false otherwise
     */
    public static boolean showMoveOrRemoveDialog(Component parentComponent, String productName, String locationCoordinate) {
        if (!ActionManager.enoughActions()) {
            JOptionPane.showMessageDialog(parentComponent,
                    "You do not have enough actions to move products!",
                    "Not enough actions", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        var game = GameState.instance();
        var inventory = game.getInventoryManager();
        var floor = game.getFloorStorageManager();

        int inventoryQty = inventory.getQuantity(productName);
        int locationQty = floor.getProductQuantityAt(locationCoordinate, productName);
        int availableStorage = floor.getAvailableSpacesAt(locationCoordinate);
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Modify displayed stock for: " + productName));
        panel.add(new JLabel("Inventory quantity: " + inventoryQty));
        panel.add(new JLabel("Quantity at location: " + locationQty));
        panel.add(new JLabel("Available space at location: " + availableStorage));

        JComboBox<String> actionBox = new JComboBox<>(new String[]{"place here", "place in inventory"});
        panel.add(new JLabel("Action:"));
        panel.add(actionBox);

        JTextField amountField = new JTextField();
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                panel,
                "Stock Adjustment",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return false;

        String action = (String) actionBox.getSelectedItem();
        int amount;

        try {
            amount = Integer.parseInt(amountField.getText().trim());
            if (amount <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parentComponent,
                    "Please enter a valid positive number.",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ("place here".equalsIgnoreCase(action)) {
            if (inventoryQty < amount) {
                JOptionPane.showMessageDialog(parentComponent,
                        "You do not have enough of this item in inventory.",
                        "Insufficient Inventory", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            if (amount > availableStorage) {
                JOptionPane.showMessageDialog(parentComponent,
                        "Not enough space at location " + locationCoordinate + ".",
                        "Insufficient Storage Space", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            inventory.removeProduct(productName, amount);
            floor.addProductAt(locationCoordinate, productName, amount);

            JOptionPane.showMessageDialog(parentComponent,
                    "Moved " + amount + " of " + productName + " to location " + locationCoordinate + ".",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } else {
            if (locationQty < amount) {
                JOptionPane.showMessageDialog(parentComponent,
                        "You do not have enough of this item at location " + locationCoordinate + ".",
                        "Insufficient Stock at Location", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            floor.removeProductAt(locationCoordinate, productName, amount);
            inventory.addToInventory(productName, amount);

            JOptionPane.showMessageDialog(parentComponent,
                    "Placed " + amount + " of " + productName + " back to inventory.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        ActionManager.futureConsume(ActionSource.CHANGE_STOCK);
        GamePersistence.saveGame();
        return true;
    }
}
