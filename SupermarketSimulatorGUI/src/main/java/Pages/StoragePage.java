package main.java.Pages;

import main.java.Abstracts.Page;
import main.java.Enums.PageName;
import main.java.PageInputs.StorageInput;
import main.java.Printers.InventoryPrinter;

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
