package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Printers.BuyProductPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class BuyProductPage extends Page {
    private final BuyProductPrinter buyProductPrinter = new BuyProductPrinter(); // printer

    // constructor
    public BuyProductPage() {
        super(PageName.BUY_PRODUCTS_PAGE);
    }


    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("Product Purchase Page");
        ActionManager.tryActionUpdate();
        buyProductPrinter.printBody();
        return buyProductPrinter.purchaseMain(scanner);
    }
}
