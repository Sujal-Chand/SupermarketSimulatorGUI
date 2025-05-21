package com.pdcgame.Pages;

import com.pdcgame.Enums.PageName;
import com.pdcgame.Abstracts.Page;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.PageInputs.EquipmentInput;
import com.pdcgame.Printers.EquipmentPrinter;

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
