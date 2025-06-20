package com.pdcgame.Pages;

import com.pdcgame.Abstracts.Page;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Printers.BuyEquipmentPrinter;

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
