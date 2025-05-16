package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Printers.SellEquipmentPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class SellEquipmentPage extends Page {
    private final SellEquipmentPrinter sellEquipmentPrinter = new SellEquipmentPrinter(); // printer

    // constructor
    public SellEquipmentPage(){
        super(PageName.SELL_EQUIPMENT_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Sell Equipment Page");
        ActionManager.tryActionUpdate();
        sellEquipmentPrinter.printBody();
        sellEquipmentPrinter.sellHandler(scanner);
        return PageName.EQUIPMENT_PAGE;
    }
}
