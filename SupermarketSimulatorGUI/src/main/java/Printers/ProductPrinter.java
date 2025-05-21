package main.java.Printers;

import main.java.Enums.PageName;
import main.java.Enums.ProductStorageType;
import main.java.GameState;
import main.java.IOHandler;
import main.java.ProductTypes.PurchasableProduct;

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
