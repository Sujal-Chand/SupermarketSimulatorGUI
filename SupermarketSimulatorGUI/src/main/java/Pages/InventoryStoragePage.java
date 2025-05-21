package main.java.Pages;

import main.java.Abstracts.Page;
import main.java.Enums.PageName;
import main.java.Managers.ActionManager;
import main.java.Printers.InventoryStoragePrinter;

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
