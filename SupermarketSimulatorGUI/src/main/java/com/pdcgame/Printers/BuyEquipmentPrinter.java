package com.pdcgame.Printers;

import com.pdcgame.DisplayObjects.GameBoard;
import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Finders.FindBoardCell;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Managers.BankManager;
import com.pdcgame.Managers.BuilderManager;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class BuyEquipmentPrinter extends Printer {
    private final FindBoardCell findBoardCell = new FindBoardCell();

    @Override
    public void printBody() {
        System.out.println("*NOTE* You will consume ONE action to purchase one or more equipment on this page.");
        String dash = "-".repeat(70);
        System.out.println(dash);
        // print all purchasable equipment
        for (BoardCell cell : BoardCell.values()) {
            if(!cell.getIcon().equalsIgnoreCase("[   ]")){
                System.out.printf("%-20s %s : Cost: $%-7.2f %s",
                        cell, cell.getIcon(), cell.getItemCost(), "");
            }
            if(cell.getCapacity() != 0) {
                System.out.printf("| Storage Capacity: %d\n", cell.getCapacity());
            }
        }
        System.out.printf("\n%s\n",dash);
        System.out.println(BankManager.displayBalance());
        System.out.print("""
                \ntype the icon or name of the item you wish to purchase.
                type 'x' to go back.
                """);
    }

    public void purchaseHandler(Scanner scanner){
        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim().toLowerCase();
            if(input.equalsIgnoreCase("x")) break;
            BoardCell selectedItem = findBoardCell.find(input);
            if(selectedItem == BoardCell.EMPTY) {
                System.out.println("We're not sure what you meant by '" + input + "'.");
                continue;
            }

            // returns confirmation message upon exact match, or returns autocomplete suggestion message
            String selectionMessage = findBoardCell.isExactMatch(selectedItem, input)
                    ? "You selected: %s | Cost: $%.2f\nAre you sure you want to purchase this item? (y/n) >> "
                    : "Did you mean to purchase: %s? | Cost: $%.2f (y/n) >> ";
            System.out.printf(selectionMessage, selectedItem, selectedItem.getItemCost());

            String confirmation = scanner.nextLine().trim().toLowerCase();
            if(!(confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))) {
                System.out.println("Cancelled...\n");
                printBody();
                continue;
            }
            // display board
            GameBoard.instance().display();

            System.out.printf("\nEnter coordinates to place your %s >> ", selectedItem);
            int[] coordinates = IOHandler.instance().readCoordinates(scanner);
            if (coordinates == null) {
                System.out.println("Cancelled...\n");
                printBody();
                continue;
            }

            int x = coordinates[0];
            int y = coordinates[1];

            InternalCases outcome = BuilderManager.purchaseItem(selectedItem, x, y);
            // switch case for different purchase outcomes
            switch (outcome) {
                case SUCCESS -> {
                    ActionManager.futureConsume();
                    System.out.println("Purchase successful.\n");
                    GamePersistence.saveGame();
                }
                case SPACE_TAKEN -> System.out.println("Purchase cancelled. This space is taken\n");
                case NO_FUNDS -> System.out.println("Purchase cancelled. You don't have enough funds.\n");
                case NO_ACTIONS -> System.out.println("Purchase cancelled. You don't have enough actions.\n");
                case MAX_AMOUNT -> System.out.println("Purchase cancelled. Max amount of this item has been reached.\n");
                case null, default -> System.out.println("Purchase cancelled...\n");
            }
            printBody();
        }
    }
}
