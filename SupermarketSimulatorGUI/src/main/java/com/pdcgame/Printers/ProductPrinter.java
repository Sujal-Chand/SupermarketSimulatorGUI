package com.pdcgame.Printers;

import com.pdcgame.Enums.PageName;
import com.pdcgame.Enums.ProductStorageType;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.ProductTypes.PurchasableProduct;

import java.util.Collection;
import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class ProductPrinter extends Printer {
    @Override
    public void printBody() {
        System.out.println("Welcome to the Product page!");
        System.out.println("Here you can choose to purchase products, see weekly popular products, and set store prices");

    }
}
