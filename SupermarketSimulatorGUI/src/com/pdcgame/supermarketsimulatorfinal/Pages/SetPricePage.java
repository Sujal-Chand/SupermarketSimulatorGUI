package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Printers.SetPricePrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class SetPricePage extends Page {
    private final SetPricePrinter setPricePrinter = new SetPricePrinter(); // printer

    // constructor
    public SetPricePage() {
        super(PageName.SET_PRICE_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Price Setting Page");
        ActionManager.tryActionUpdate();
        setPricePrinter.showProducts();
        setPricePrinter.printBody();

        return setPricePrinter.priceSetHandler(scanner);
    }
}
