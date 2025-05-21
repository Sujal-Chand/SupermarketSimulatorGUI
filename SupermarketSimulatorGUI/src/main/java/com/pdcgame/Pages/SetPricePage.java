package com.pdcgame.Pages;

import com.pdcgame.Abstracts.Page;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Printers.SetPricePrinter;

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
