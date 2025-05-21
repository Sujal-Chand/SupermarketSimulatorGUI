package main.java.Printers;

import main.java.Enums.PageName;
import main.java.Enums.ProductStorageType;
import main.java.Finders.FindProductName;
import main.java.GameState;
import main.java.Managers.CartManager;
import main.java.ProductTypes.PurchasableProduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class BuyProductPrinter extends Printer{
    private final FindProductName finder = new FindProductName();
    private final GameState gameInstance = GameState.instance();
    private final CartManager cartInstance = CartManager.instance();

    @Override
    public void printBody() {
        System.out.println("type 'all' to see all products.");
        System.out.println("you can also filter by 'shelf', 'fridge', or 'frozen'.");
        System.out.println("type 'cart' to go to cart.");
        System.out.println("type 'x' to go back.");
    }

    public PageName purchaseMain(Scanner scanner) {
        while(true) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim().toLowerCase();
            if(input.equalsIgnoreCase("x")) {
                return PageName.PRODUCTS_PAGE;
            }
            if(input.equalsIgnoreCase("cart")) {
                return PageName.CART_PAGE;
            }

            // set filter to provided matching input
            if(input.equalsIgnoreCase("all") ||
                    input.equalsIgnoreCase("shelf") ||
                    input.equalsIgnoreCase("fridge") ||
                    input.equalsIgnoreCase("frozen")) {
                input = input.toUpperCase();
                filterSearch(scanner, ProductStorageType.valueOf(input));
                continue;
            }
            invalid(input);
        }
    }


    public void filterSearch(Scanner scanner, ProductStorageType filter) {
        // get list based on filter
        Collection<PurchasableProduct> searchProducts = gameInstance.getProductManager().getFilteredPurchasableProducts(filter);

        // update search pool
        finder.changeSearchCollection(searchProducts);

        // print header
        printSeparator();
        System.out.printf("| %-20s | %-48s | %-10s | %-9s |\n", "NAME", "DESCRIPTION", "PRICE", "QUANTITY");
        printSeparator();

        // loop through products
        for (PurchasableProduct product : searchProducts) {
            // check if type matches or all
            if((product.getStorageType().equals(filter)) || filter.equals(ProductStorageType.ALL)) {
                String productName = product.getName();
                String description = product.getDescription();
                double price = product.getBulkPrice();
                int boxQuantity = product.getQuantityInBox();

                // cut name if too long
                if(productName.length() > 20) productName = productName.substring(0, 20);
                // cut desc if too long and add ...
                if (description.length() > 48) description = description.substring(0, 45) + "...";

                // print product row
                System.out.printf("| %-20s | %-48s | $%-9.2f | %-9d |\n", productName, description, price, boxQuantity);

                // print row end
                printSeparator();
            }
        }

        while(true) {
            // ask for product name or exit
            System.out.println("Enter part of the products NAME or enter 'x' to go back");
            System.out.print(">> ");
            String input = scanner.nextLine().trim().toLowerCase();

            // go back if x
            if(input.equalsIgnoreCase("x")) {
                break;
            }

            // find product
            String productName = finder.find(input);
            PurchasableProduct selectedProduct = gameInstance.getProductManager().getProduct(productName);

            if(selectedProduct != null && (!input.isEmpty())) {
                while(true) {
                    // confirm product and quantity
                    System.out.printf("Did you select %s? Press enter to add one or type a quantity. 'x' to select another product >> ", productName);
                    String choice = scanner.nextLine().trim().toLowerCase();

                    // cancel add
                    if(choice.equalsIgnoreCase("x") || choice.equalsIgnoreCase("n")) {
                        System.out.println("Discarded item...");
                        break;
                    }

                    int quantity = 1;

                    // add one by default
                    if(choice.equalsIgnoreCase("") || choice.equalsIgnoreCase("y")) {
                        System.out.println("Added 1 to cart.");
                        cartInstance.addProduct(productName, quantity);
                        break;
                    }

                    try{
                        // try add custom amount
                        quantity = Integer.parseInt(choice);
                        if(quantity <= 0) throw new NumberFormatException(); // no 0 or less
                        System.out.println("Added " + quantity + " to cart.");
                        cartInstance.addProduct(productName, quantity);
                        break;
                    } catch (NumberFormatException e) {
                        // not a number
                        invalid(choice);
                    }
                }
            } else {
                // didn't find product
                invalid(input);
            }
        }

        // return to main view
        printBody();
    }


    private void printSeparator() {
        System.out.println("|----------------------|--------------------------------------------------|------------|-----------|");

    }

    public void invalid(String input) {
        System.out.println("We're not sure what you meant by '" + input + "'.");
    }
}
