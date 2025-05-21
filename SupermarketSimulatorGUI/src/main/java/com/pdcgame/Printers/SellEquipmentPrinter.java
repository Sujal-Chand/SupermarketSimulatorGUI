package com.pdcgame.Printers;

import com.pdcgame.DisplayObjects.GameBoard;
import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
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
// handles printing and logic for selling equipment on the store map
public class SellEquipmentPrinter extends Printer {
    // references to game board and game state
    private final GameBoard gameBoard = GameBoard.instance();
    private final GameState gameInstance = GameState.instance();

    @Override
    public void printBody() {
        // displays the game board and sale instructions
        gameBoard.display();
        System.out.println("\n*NOTE* You will consume ONE action to sell equipment from the store.");
    }

    // handles user input and logic for selling equipment
    public void sellHandler(Scanner scanner) {
        while(true) {
            System.out.print("type a coordinate or enter x to go back >> ");
            int[] coordinates = IOHandler.instance().readCoordinates(scanner);
            if(coordinates == null) {
                return; // exit if input is invalid or user chose to go back
            }

            // gets the cell at given coordinates
            BoardCell cell = gameInstance.getBoardManager().getCell(coordinates[0], coordinates[1]);

            // converts coordinates to string format
            String stringCoordinates = IOHandler.instance().coordinatesToString(coordinates);


            InternalCases outcome = BuilderManager.trySellItem(cell, stringCoordinates);
            // internal case outcome
            switch(outcome) {
                case EMPTY -> {
                    System.out.println("This cell is already empty");
                }
                case NO_ACTIONS ->  {
                    System.out.println("You don't have enough actions to sell equipment!");
                }
                case MIN_AMOUNT ->  {
                    System.out.println("You need at least one cashier!");
                }
                case SPACE_TAKEN ->{
                    System.out.println("There are products in this location that need to be removed first!");
                }
                case SUCCESS -> {
                    System.out.print("Are you sure? (y/yes) >> ");
                    String answer = scanner.nextLine().trim().toLowerCase();

                    // cancels the sale
                    if(!answer.equals("yes") && !answer.equals("y")) {
                        System.out.println("Cancelled...");
                        continue;
                    }

                    // calculates and applies sale of equipment
                    double sellPrice = cell.getItemCost() * 0.9;
                    System.out.println("SOLD " + cell.toString().toLowerCase() + " FOR $" + sellPrice);
                    BuilderManager.sellItem(cell, coordinates, stringCoordinates);
                    printBody(); // refresh display after selling
                }
                case null, default -> {
                    System.out.println("Item could not be sold");
                    continue;
                }
            }
        }
    }
}
