package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.PageInputs.PopularProductInput;
import com.pdcgame.supermarketsimulatorfinal.Printers.PopularProductPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class PopularProductPage extends Page {
    private final PopularProductPrinter popularProductPrinter = new PopularProductPrinter(); // printer
    private final PopularProductInput popularProductInput = new PopularProductInput();

    // constructor
    public PopularProductPage(){
        super(PageName.POPULAR_PRODUCTS_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) throws InterruptedException {
        pageHeader("This Weeks Popular Products");
        popularProductPrinter.printBody(); // print all the popular products for the week
        popularProductPrinter.printChoices("buy", "buy products for your store",
                "x", "go to the previous page.");
        return popularProductInput.getPageName(scanner);
    }
}
