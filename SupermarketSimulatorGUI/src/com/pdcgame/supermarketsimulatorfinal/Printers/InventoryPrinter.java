package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.GameState;

import java.util.Map;

/**
 * @author prisha, sujal
 */
public class InventoryPrinter extends Printer {

    public void printInventory() {
        Map<String, Integer> inventory = GameState.instance().getInventoryManager().getStoredProducts();
        int totalQuantity = inventory.values().stream().mapToInt(Integer::intValue).sum();

        // empty inventory view
        if(totalQuantity == 0) {
            System.out.println("There are no items in your inventory.");
            return;
        }


        // go through each item in the inventory hashmap that has a higher than 0 quantity and print it
        System.out.println("|        ITEMS IN INVENTORY        |");
        System.out.printf("| %-20s | %-9s |\n", "NAME", "QUANTITY");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.printf("| %-20s | %-9d |\n", entry.getKey(), entry.getValue());
            }
        }
    }
}
