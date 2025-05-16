package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Finders.FindProductName;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Managers.BankManager;
import com.pdcgame.supermarketsimulatorfinal.Managers.CartManager;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class CartPrinter extends Printer {
    private final CartManager cartInstance = CartManager.instance();
    private final GameState gameInstance = GameState.instance();
    private final FindProductName finder = new FindProductName();
    Collection<PurchasableProduct> searchQuery = new ArrayList<>();

    public void printCart() {
        System.out.println();
        // view if cart is empty
        if(cartInstance.cartEmpty()) {
            System.out.println("YOU CURRENTLY HAVE NO ITEMS IN YOUR CART");
            printChoices("buy", "buy products for your store.",
                    "x", "go back.");
            searchQuery.clear();
            finder.changeSearchCollection(searchQuery);
            return;
        }

        // normal view
        printSeparator();
        System.out.printf("| %-20s | %-20s | %-20s | %-10s |\n", "NAME", "QUANTITY IN CART", "INDIVIDUAL QUANTITY", "PRICE");
        printSeparator();
        searchQuery.clear(); // clear the search query every time cart is updated
        for(Map.Entry<String, Integer> cartItem: cartInstance.storedProducts.entrySet()) {
            PurchasableProduct product = gameInstance.getProductManager().getProduct(cartItem.getKey()); // the product with the current Key (productName)
            searchQuery.add(product); // add the product to the search query to be passed to the FindProductName function
            int amountInCart = cartItem.getValue(); // how many boxes in cart

            System.out.printf("| %-20s | %-20d | %-20d | $%-9.2f |\n",
                    product.getName(),
                    amountInCart,
                    product.getQuantityInBox() * amountInCart,
                    product.getBulkPrice() * amountInCart); // print details
            printSeparator();
        }
        System.out.printf("TOTAL COST: $%.2f\n", cartInstance.cartTotalValue()); // total cost
        System.out.println("*NOTE* You will consume ONE action purchase the items in your cart.");
        finder.changeSearchCollection(searchQuery); // change the search query to reflect cart items
        printChoices("buy", "buy products for your store.",
                "x", "go back.",
                "purchase", "purchase items in your cart.");
        BankManager.displayBalance();

    }

    private void printSeparator() {
        System.out.println("|----------------------|----------------------|----------------------|------------|");
    }

    @Override
    public void printChoices(String... choicesWithDescriptions) {
        System.out.println(); // vertical padding
        int count = 0;
        if(choicesWithDescriptions.length == 0 || choicesWithDescriptions.length % 2 != 0) {
            System.out.println("yikes! choices were not adequately supplied.");
            return;
        }
        for(String text : choicesWithDescriptions) {
            count++;
            System.out.printf(count % 2 == 1 ? "type '%s' " : "to %s\n", text);
        }
        if(!cartInstance.cartEmpty()) System.out.println("type part of a products name to modify its quantity.");
        System.out.print(">> ");
    }

    public PageName cartHandler(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input.toLowerCase()) {
                case "buy":
                    return PageName.BUY_PRODUCTS_PAGE;
                case "x":
                    return PageName.PRODUCTS_PAGE;
                case "purchase" :
                    switch (cartInstance.canCheckoutCart()) {
                        case NO_FUNDS -> {
                            System.out.println("You don't have enough funds to complete this purchase...");
                            return PageName.PRODUCTS_PAGE;
                        }
                        case NO_ACTIONS -> {
                            System.out.println("You don't have enough actions to complete this purchase...");
                            printCart();
                            return PageName.PRODUCTS_PAGE;
                        }
                        case SUCCESS -> {
                            ActionManager.futureConsume();
                            cartInstance.checkoutCart();
                            System.out.println("Purchase successful! Returning to previous page...");
                            return PageName.PRODUCTS_PAGE;
                        }
                    }
            }

            // find product name based off search
            String productName = finder.find(input);
            PurchasableProduct selectedProduct = gameInstance.getProductManager().getProduct(productName); // find corresponding product

            // if null return to main view
            if (selectedProduct == null) {
                invalid(input);
                printCart();
                continue;
            }

            System.out.printf("Enter how much %s to modify e.g. 'add 4' or 'remove all', 'x' to go back >> ",
                    selectedProduct.getName());
            String choice = scanner.nextLine().trim().toLowerCase();
            // exit case
            if (choice.equals("x")) {
                printCart();
                continue;
            }

            // split command by space
            String[] command = choice.split(" ");
            String action = command[0]; // set action
            String amount = command.length > 1 ? command[1] : "";

            boolean validAction = false;

            if (action.equals("add")) {
                // try parsing as integer
                try {
                    int quantity = Math.abs(Integer.parseInt(amount));
                    System.out.println("Added " + quantity + " " + selectedProduct.getName());
                    cartInstance.addProduct(productName, quantity);
                    validAction = true;
                } catch (NumberFormatException e) {
                    // fall through to invalid
                }
            } else if (action.equals("remove")) {
                // remove all quantity of this product in the cart
                if (amount.equals("all")) {
                    int quantity = cartInstance.getItemQuantity(productName);
                    System.out.println("Removed " + quantity + " " + selectedProduct.getName());
                    cartInstance.removeProduct(productName, quantity);
                    validAction = true;
                } else {
                    // try parsing as integer
                    try {
                        int quantity = Math.abs(Integer.parseInt(amount));
                        quantity = Math.min(quantity, cartInstance.getItemQuantity(productName));
                        System.out.println("Removed " + quantity + " " + selectedProduct.getName());
                        cartInstance.removeProduct(productName, quantity);
                        validAction = true;
                    } catch (NumberFormatException e) {
                        // fall through to invalid
                    }
                }
            }

            if (!validAction) {
                invalid(choice);
            }

            printCart();
        }
    }

    public void invalid(String input) {
        System.out.println("We're not sure what you meant by '" + input + "'.");
    }
}
