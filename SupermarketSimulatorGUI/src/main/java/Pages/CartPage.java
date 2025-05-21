package main.java.Pages;

import main.java.Abstracts.Page;
import main.java.Enums.PageName;
import main.java.Managers.ActionManager;
import main.java.Printers.CartPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class CartPage extends Page {
    private final CartPrinter cartPrinter = new CartPrinter(); // printer

    // constructor
    public CartPage() {
        super(PageName.CART_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) {
        pageHeader("CART");
        ActionManager.tryActionUpdate();
        cartPrinter.printCart();
        return cartPrinter.cartHandler(scanner);
    }
}
