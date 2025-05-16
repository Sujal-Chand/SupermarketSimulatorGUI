package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.DisplayObjects.GameBoard;
import com.pdcgame.supermarketsimulatorfinal.DisplayObjects.PreGameStats;
import com.pdcgame.supermarketsimulatorfinal.GameState;

/**
 * @author prisha, sujal
 */
public class GameMenuPrinter extends Printer {
    private final PreGameStats preGameStats = new PreGameStats();
    private final GameBoard gameBoard = GameBoard.instance();
    @Override
    public void printBody() {
        gameBoard.display(); // display the game board in the game menu
        preGameStats.display(); // display pre game stats in the game menu
        if(GameState.instance().getDay() == 0) printTutorial();

    }

    // print tutorial
    public void printTutorial() {
        System.out.println("Explore the menus, your first day has infinite actions!");
        System.out.println("Start by purchasing a cashier and other equipment from the equipment page..");
        System.out.println("Then go to products and purchase products to display on shelves, fridges, freezers..");
        System.out.println("Make sure you display your stock on the store floor by going to the storage page..");
        System.out.println("Your goal is to get 5 stars! The game ends if rating is 0 or you run out of money!");
        System.out.println("\nTIP: Make sure your prices each day are not too high or customers will get angry!");
    }

    // check if game should end
    public boolean isGameOver() {
        if(GameState.instance().getRating() > 4.5) return false;
        return !(GameState.instance().getRating() < 0.5) && !(GameState.instance().getBalance() <= 0);
    }
}
