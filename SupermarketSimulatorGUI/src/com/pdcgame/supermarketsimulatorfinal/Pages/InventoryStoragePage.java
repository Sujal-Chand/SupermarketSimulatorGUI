package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Printers.InventoryStoragePrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class InventoryStoragePage extends Page {
    private final InventoryStoragePrinter inventoryStoragePrinter = new InventoryStoragePrinter(); // printer

    // constructor
    public InventoryStoragePage() {
        super(PageName.INVENTORY_STORAGE_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Product Movement Page");
        ActionManager.tryActionUpdate();
        inventoryStoragePrinter.printBody();
        return inventoryStoragePrinter.movementHandler(scanner);
    }
}
