package main.java.Pages;

import main.java.Abstracts.Page;
import main.java.Enums.PageName;
import main.java.Managers.ActionManager;
import main.java.Printers.SetPricePrinter;

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
