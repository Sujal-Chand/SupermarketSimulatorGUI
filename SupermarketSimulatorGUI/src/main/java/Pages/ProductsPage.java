package main.java.Pages;

import main.java.Enums.PageName;
import main.java.Abstracts.Page;
import main.java.Managers.ActionManager;
import main.java.PageInputs.ProductInput;
import main.java.Printers.ProductPrinter;

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
