package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.GamePersistence;
import com.pdcgame.supermarketsimulatorfinal.GameState;

import static com.pdcgame.supermarketsimulatorfinal.GameState.instance;

/**
 * @author prisha, sujal
 */
public class BankManager {
    private static final GameState gameInstance = instance();

    public static void setInitialBalance() {
        gameInstance.setBalance(gameInstance.getDifficulty().getStartingBalance()); // sets initial balance for new game
    }

    public static void subtractBalance(double fund) {
        gameInstance.setBalance(gameInstance.getBalance() - fund); // subtracts balance by fund
        GamePersistence.saveGame();

    }

    public static void addBalance(double fund) {
        gameInstance.setBalance(gameInstance.getBalance() + fund);  // adds balance by fund
        GamePersistence.saveGame();

    }

    public static boolean possiblePurchase(double fund) {
        return gameInstance.getBalance() >= fund; // returns boolean TRUE if purchase is possible, otherwise FALSE
    }

    public static String displayBalance() {
        return String.format("\n> Current balance: $%.2f <", GameState.instance().getBalance());
    }
}
