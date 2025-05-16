package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Printers.BuyEquipmentPrinter;

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
