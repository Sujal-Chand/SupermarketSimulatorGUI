package com.pdcgame.supermarketsimulatorfinal.Printers;

import com.pdcgame.supermarketsimulatorfinal.GameState;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class GameOverPrinter extends Printer {
    private final StoreRatingPrinter storeRatingPrinter = new StoreRatingPrinter();

    public void printEnding(Scanner scanner) {
        if(GameState.instance().getRating() > 4.5) {
            // good ending
            System.out.println("CONGRATULATIONS YOU'VE WON!");
            System.out.println("YOU'VE BECOME A MASTER CAPITALIST AND CUSTOMERS LOVE YOUR STORE");
            storeRatingPrinter.fiveStars();
        } else {
            // bad endings
            System.out.println("OH NO! GAME OVER YOU LOST.");
            if(GameState.instance().getRating() > 0.5) System.out.println("YOU FILED FOR BANKRUPTCY AND THE BANK HAS ORDERED TO SHUT DOWN YOUR STORE"); // the bankruptcy ending
            else System.out.println("CUSTOMERS DECIDED TO MASS REPORT YOUR STORE AND SPREAD NEWS TO NEVER SHOP HERE AGAIN!"); // bad rating ending
        }
        System.out.print("\nTHANKS FOR PLAYING. CREATED BY SUJAL AND PRISHA\nType anything to go back to menu >>");
        String input = scanner.nextLine();
    }
}
