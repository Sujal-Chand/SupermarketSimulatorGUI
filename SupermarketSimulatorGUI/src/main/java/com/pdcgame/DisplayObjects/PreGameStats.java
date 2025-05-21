package com.pdcgame.DisplayObjects;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.GameObject;
import com.pdcgame.Printers.StoreRatingPrinter;

/**
 * @author prisha, sujal
 */
public class PreGameStats implements GameObject {
    private final StoreRatingPrinter storeRatingPrinter = new StoreRatingPrinter();
    private final GameState gameInstance = GameState.instance();
    // print the actions, balance, and store rating
    @Override
    public void display() {
        System.out.println();
        storeRatingPrinter.printRating(gameInstance.getRating());
        System.out.printf("-->  Remaining Actions: %d/%d, Balance: $%.2f, Rating %s<--\n",
                gameInstance.getActions(), gameInstance.getTotalActions(),
                gameInstance.getBalance(), printRating(gameInstance.getRating()));

    }

    // format the rating to round to .5 floor increments and format the decimals before converting to string
    public String printRating(double value) {
        double rounded = Math.floor(value * 2) / 2.0;
        if (rounded % 1 == 0) {
            return String.format("%.0f/5  ", rounded);
        } else {
            return String.format("%.1f/5  ", rounded);
        }
    }
}
