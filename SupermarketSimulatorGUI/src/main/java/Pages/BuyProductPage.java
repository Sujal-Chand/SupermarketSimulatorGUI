package main.java.Pages;

import main.java.Abstracts.Page;
import main.java.Enums.PageName;
import main.java.Managers.ActionManager;
import main.java.Printers.BuyProductPrinter;

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
