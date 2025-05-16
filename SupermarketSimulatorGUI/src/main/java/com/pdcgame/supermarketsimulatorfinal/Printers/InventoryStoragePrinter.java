package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.DisplayObjects.GameBoard;
import com.pdcgame.supermarketsimulatorfinal.Enums.BoardCell;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Enums.ProductStorageType;
import com.pdcgame.supermarketsimulatorfinal.Finders.FindProductName;
import com.pdcgame.supermarketsimulatorfinal.GamePersistence;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.IOHandler;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

import java.util.Collection;
import java.util.Scanner;

/**
 * @author prisha, sujal
 */
// handles printing and managing item movement between store locations
public class InventoryStoragePrinter extends Printer {
    // gets the current game state
    private final GameState gameInstance = GameState.instance();
    // used to search products by name
    private final FindProductName finder = new FindProductName();

    @Override
    public void printBody() {
        // shows game board and movement instructions
        GameBoard.instance().display();
        System.out.println("\ntype a coordinate x,y to move items between.");
        System.out.println("type 'x' to go back.");
        System.out.println("*NOTE* You will consume ONE action to move items between areas of the store.");
    }

    // entry point for handling product movement
    public PageName movementHandler(Scanner scanner) {
        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("x")) return PageName.STORAGE_PAGE;

            int[] coordinates = IOHandler.instance().readCoordinates(scanner, input);
            if (coordinates == null) {
                printBody();
                continue;
            }

            BoardCell cell = getValidStorageCell(coordinates);
            if (cell == null) {
                printBody();
                continue;
            }

            ProductStorageType filter = cell.getStorageType();
            String locationCoordinate = IOHandler.instance().coordinatesToString(coordinates);
            boolean isFunctioning = gameInstance.getFloorStorageManager().isFunctioning(locationCoordinate);

            if (!isFunctioning) {
                System.out.println("The equipment at this location can't store products currently!");
                printBody();
                continue;
            }

            Collection<PurchasableProduct> searchProducts = gameInstance.getProductManager().getFilteredPurchasableProducts(filter);
            finder.changeSearchCollection(searchProducts);
            handleProductSelection(scanner, cell, locationCoordinate, searchProducts);
        }
    }

    // validates a board cell for storage
    private BoardCell getValidStorageCell(int[] coordinates) {
        BoardCell cell = gameInstance.getBoardManager().getCell(coordinates[0], coordinates[1]);
        if (cell == null || !cell.canStoreProducts()) {
            System.out.println("There is no item here that can store products");
            return null;
        }
        return cell;
    }

    // handles product selection and quantity transfer
    private void handleProductSelection(Scanner scanner, BoardCell cell, String locationCoordinate, Collection<PurchasableProduct> searchProducts) {
        while (true) {
            printProductQuantities(cell, locationCoordinate, searchProducts);
            int availableStorage = gameInstance.getFloorStorageManager().getAvailableSpacesAt(locationCoordinate);
            System.out.println(cell.toString().toUpperCase() + " AVAILABLE STORAGE: " + availableStorage + "\n");

            System.out.print("Enter part of the products NAME or enter 'x' to go back >> ");
            String userProductName = scanner.nextLine().trim().toLowerCase();

            if (userProductName.equalsIgnoreCase("x")) {
                printBody();
                break;
            }

            String productName = finder.find(userProductName);
            PurchasableProduct selectedProduct = gameInstance.getProductManager().getProduct(productName);

            if (selectedProduct != null && !userProductName.isEmpty()) {
                handleMoveOrRemove(scanner, productName, locationCoordinate, availableStorage);
            }

            GamePersistence.saveGame();
        }
    }

    // displays inventory and location product counts
    private void printProductQuantities(BoardCell cell, String locationCoordinate, Collection<PurchasableProduct> searchProducts) {
        System.out.printf("| %-20s | %-20s | %-40s |\n", "NAME", "INVENTORY QUANTITY", cell.toString().toUpperCase() + " QUANTITY");
        for (PurchasableProduct product : searchProducts) {
            String name = product.getName();
            int invQty = gameInstance.getInventoryManager().getQuantity(name);
            int locQty = gameInstance.getFloorStorageManager().getProductQuantityAt(locationCoordinate, name);
            System.out.printf("| %-20s | %-20d | %-40d |\n", name, invQty, locQty);
        }
    }

    // handles logic for moving/removing product
    private void handleMoveOrRemove(Scanner scanner, String productName, String locationCoordinate, int availableStorage) {
        while (true) {
            System.out.printf("Did you select %s? type 'x' to choose another item. \n\n", productName);
            System.out.println("'move 5' would move 5 from inventory to this location");
            System.out.println("'remove 5' would move 5 from this location to inventory");
            System.out.println("\n ITEMS WILL ONLY MOVE IF THERE IS SUFFICIENT QUANTITY, ACTIONS, OR AVAILABLE SPACE");
            System.out.print(">> ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("x")) break;

            String[] splitChoice = choice.split(" ");
            if (splitChoice.length != 2) {
                invalid(choice);
                continue;
            }

            if (!ActionManager.enoughActions()) {
                System.out.println("You do not have enough actions to move products!");
                break;
            }

            String action = splitChoice[0];
            int amount;
            try {
                amount = Integer.parseInt(splitChoice[1]);
                if (amount <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                invalid(choice);
                continue;
            }

            if (action.equalsIgnoreCase("move")) {
                moveProduct(productName, locationCoordinate, amount, availableStorage);
                break;
            } else if (action.equalsIgnoreCase("remove")) {
                removeProduct(productName, locationCoordinate, amount);
                break;
            }
        }
    }

    // moves product from inventory to location
    private void moveProduct(String productName, String locationCoordinate, int amount, int availableStorage) {
        if (amount > availableStorage) {
            System.out.println("There is not enough space in available storage.");
            return;
        }

        int invQty = gameInstance.getInventoryManager().getQuantity(productName);
        if (invQty < amount) {
            System.out.println("You do not have enough quantity of this item in inventory.");
            return;
        }

        gameInstance.getInventoryManager().removeProduct(productName, amount);
        gameInstance.getFloorStorageManager().addProductAt(locationCoordinate, productName, amount);
        System.out.println("SUCCESSFULLY MOVED FROM INVENTORY TO LOCATION.");
        ActionManager.futureConsume();
    }

    // removes product from location to inventory
    private void removeProduct(String productName, String locationCoordinate, int amount) {
        int locQty = gameInstance.getFloorStorageManager().getProductQuantityAt(locationCoordinate, productName);
        if (locQty < amount) {
            System.out.println("You do not have enough quantity of this item at the storage location.");
            return;
        }

        gameInstance.getFloorStorageManager().removeProductAt(locationCoordinate, productName, amount);
        gameInstance.getInventoryManager().addToInventory(productName, amount);
        System.out.println("SUCCESSFULLY REMOVED FROM LOCATION TO INVENTORY.");
        ActionManager.futureConsume();
    }

    // shows invalid input message
    public void invalid(String input) {
        System.out.println("We're not sure what you meant by '" + input + "'.");
    }
}
