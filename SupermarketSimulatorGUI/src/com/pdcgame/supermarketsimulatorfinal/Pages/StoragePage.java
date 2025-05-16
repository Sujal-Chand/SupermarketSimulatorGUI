package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.PageInputs.StorageInput;
import com.pdcgame.supermarketsimulatorfinal.Printers.InventoryPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class StoragePage extends Page {
    private final InventoryPrinter inventoryPrinter = new InventoryPrinter(); // printer
    private final StorageInput storageInput = new StorageInput();

    // constructor
    public StoragePage() {
        super(PageName.STORAGE_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Storage Page");
        inventoryPrinter.printInventory();
        inventoryPrinter.printChoices("move", "move items from inventory to shelves, fridges, and freezers.",
                "x", "go to the previous page.");
        return storageInput.getPageName(scanner);
    }
}
