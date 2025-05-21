package com.pdcgame.Pages;

import com.pdcgame.Abstracts.Page;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Printers.SellEquipmentPrinter;

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
