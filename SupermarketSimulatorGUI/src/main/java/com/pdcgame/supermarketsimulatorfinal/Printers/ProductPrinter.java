package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Enums.ProductStorageType;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.IOHandler;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

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
