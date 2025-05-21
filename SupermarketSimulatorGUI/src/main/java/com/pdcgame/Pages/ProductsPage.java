package com.pdcgame.Pages;

import com.pdcgame.Enums.PageName;
import com.pdcgame.Abstracts.Page;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.PageInputs.ProductInput;
import com.pdcgame.Printers.ProductPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class ProductsPage extends Page {
    private final ProductPrinter productPrinter = new ProductPrinter(); // printer
    private final ProductInput productInput = new ProductInput();

    // constructor
    public ProductsPage(){
        super(PageName.PRODUCTS_PAGE);
    }

    // display page
    @Override
    public PageName display(Scanner scanner) throws InterruptedException {
        pageHeader("Product Page");
        ActionManager.tryActionUpdate();
        productPrinter.printBody();
        productPrinter.printChoices("buy", "buy products for your store.",
                "prices", "set prices for your products.",
                "popular", "see popular products",
                "cart", "view products in your cart.",
                "purchase action", "purchase one action for $1000",
                "x", "go to the previous page.");
        return productInput.getPageName(scanner);
    }
}
