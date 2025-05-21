package com.pdcgame.Pages;

import com.pdcgame.Abstracts.Page;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Printers.InventoryStoragePrinter;

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
