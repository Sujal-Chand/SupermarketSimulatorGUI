package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Printers.CartPrinter;

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
