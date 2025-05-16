package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Finders.FindProductName;
import com.pdcgame.supermarketsimulatorfinal.GamePersistence;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Managers.BankManager;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

import java.util.Collection;
import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class SetPricePrinter extends Printer {
    private final GameState gameInstance = GameState.instance();
    private final FindProductName finder = new FindProductName();
    private final Collection<PurchasableProduct> allProductsCopy = gameInstance.getProductManager().getPurchasableProducts();

    @Override
    public void printBody() {
        System.out.println("\non this page you can change the prices you sell products");
        System.out.println("tip: if your prices are too high your store rating will be impacted!");
        System.out.println("*note* you will consume one action to change one or more prices on this page.");
    }

    // print each product in the store that prices can be set for
    public void showProducts() {
        System.out.printf("| %-20s | %-17s | %-18s |\n", "product name", "recommended price", "your selling price");
        for (PurchasableProduct product : allProductsCopy) {
            System.out.printf("| %-20s | $%-16.2f | $%-17.2f |\n",
                    product.getName(),
                    product.getRecommendedSinglePrice(),
                    product.getSellPrice());
        }
    }

    // main handler for setting product prices
    public PageName priceSetHandler(Scanner scanner) {
        while (true) {
            printChoices();
            System.out.print(">> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("x")) {
                GamePersistence.saveGame();
                return PageName.PRODUCTS_PAGE;
            }

            if (input.equalsIgnoreCase("auto")) {
                if (handleAutoPricing()) {
                    return PageName.PRODUCTS_PAGE;
                }
                continue;
            }

            finder.changeSearchCollection(allProductsCopy);
            String selectedProduct = finder.find(input);

            if (selectedProduct == null || input.isEmpty()) {
                invalid(input);
                continue;
            }

            if (!ActionManager.enoughActions()) {
                System.out.println("You don't have enough actions to change this item's price!");
                continue;
            }

            promptForPriceChange(scanner, selectedProduct);
        }
    }

    // handles the 'auto' command: sets all prices to recommended values for $50 and an action
    private boolean handleAutoPricing() {
        if (!ActionManager.enoughActions()) {
            System.out.println("\nyou don't have enough actions so i won't set prices for you!");
            return false;
        }

        if (!BankManager.possiblePurchase(30.00)) {
            System.out.println("\nyou don't have enough money for me to set these prices automatically!");
            return false;
        }

        System.out.println("alright, i'll change the prices for you then... *takes $30.00*");
        for (PurchasableProduct product : allProductsCopy) {
            product.setSellPrice(product.getRecommendedSinglePrice());
        }

        ActionManager.futureConsume();
        BankManager.subtractBalance(50.00);
        return true;
    }

    // asks user to input a new price for the selected product
    private void promptForPriceChange(Scanner scanner, String productName) {
        System.out.printf("Enter the new price you would like to sell %s. >> ", productName);
        String doubleAsString = scanner.nextLine().trim().toLowerCase();

        try {
            double price = Double.parseDouble(doubleAsString);
            if (price < 0) {
                invalid(doubleAsString);
                return;
            }

            gameInstance.getProductManager().setSellPrice(productName, price);
            System.out.printf("Changed price of %s successfully!\n", productName.toUpperCase());
            ActionManager.futureConsume();
            showProducts();

        } catch (NumberFormatException e) {
            if (!doubleAsString.equals("x")) {
                invalid(doubleAsString);
            }
        }
    }

    // generic invalid input message
    public void invalid(String input) {
        System.out.printf("We're not sure what you mean by '%s'.\n", input);
    }

    // show instructions for setting prices
    public void printChoices() {
        System.out.println("\ntype the first part of the product's name, then when asked, enter the new price.");
        System.out.println("type 'auto' to pay an unemployed, job seeking, comp sci major $30.00 to change the prices.");
        System.out.println("type 'x' to go back to the previous page.");
    }
}
