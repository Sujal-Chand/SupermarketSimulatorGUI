package main.java.Pages;

import main.java.Enums.PageName;
import main.java.Abstracts.Page;
import main.java.Managers.ActionManager;
import main.java.PageInputs.EquipmentInput;
import main.java.Printers.EquipmentPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class EquipmentPage extends Page {
    private final EquipmentPrinter equipmentPrinter = new EquipmentPrinter(); // printer
    private final EquipmentInput equipmentInput = new EquipmentInput();

    // constructor
    public EquipmentPage(){
        super(PageName.EQUIPMENT_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Equipment Menu");
        ActionManager.tryActionUpdate();
        equipmentPrinter.printBody();
        equipmentPrinter.printChoices("buy", "buy equipment for your store.",
                "sell", "sell equipment from your store.",
                "x", "go to the previous page.");
        return equipmentInput.getPageName(scanner);
    }
}
