package com.pdcgame.Pages;

import com.pdcgame.Abstracts.Page;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Printers.CartPrinter;

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
