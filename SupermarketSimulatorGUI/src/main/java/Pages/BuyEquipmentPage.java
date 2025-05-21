package main.java.Pages;

import main.java.Abstracts.Page;
import main.java.Enums.PageName;
import main.java.Managers.ActionManager;
import main.java.Printers.BuyEquipmentPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class BuyEquipmentPage extends Page {
    private final BuyEquipmentPrinter buyEquipmentPrinter = new BuyEquipmentPrinter(); // printer
    // constructor
    public BuyEquipmentPage() {
        super(PageName.BUY_EQUIPMENT_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Purchase Equipment Page");
        ActionManager.tryActionUpdate();
        buyEquipmentPrinter.printBody();
        buyEquipmentPrinter.purchaseHandler(scanner);
        return PageName.EQUIPMENT_PAGE;
    }
}
