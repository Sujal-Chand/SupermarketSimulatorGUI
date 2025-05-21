package com.pdcgame.Managers;

import com.pdcgame.GameState;
import com.pdcgame.ProductTypes.PurchasableProduct;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class RatingManager {
    private static final double startingRating = 2.5;
    private static final GameState gameInstance = GameState.instance();

    // set the initial store rating to the starting rating value
    public static void setInitialRating() {
        gameInstance.setRating(startingRating);
    }

    // add to the store rating ensuring it doesn't go over 5
    public static void addRating(double rating) {
        rating = Math.abs(rating);
        double newRating = Math.min(5, gameInstance.getRating() + rating);
        gameInstance.setRating(newRating);
    }

    // reduce the store rating ensuring it doesn't go below 0
    public static void removeRating(double rating) {
        rating = Math.abs(rating);
        double newRating = Math.max(0, gameInstance.getRating() - rating);
        gameInstance.setRating(newRating);
    }

    // calculates the customers satisfaction and rating impact when purchasing an item
    public static boolean purchaseHappiness(String productName) {
        Random rand = new Random();
        int WEIGHT = rand.nextInt(3) + 1;
        PurchasableProduct soldProduct = gameInstance.getProductManager().getProduct(productName);
        double purchaseHappiness = 0.05 + (soldProduct.getRecommendedSinglePrice() - soldProduct.getSellPrice()) / WEIGHT; // calculate happiness based of price difference

        purchaseHappiness = Math.min(0.09, purchaseHappiness); // clamp the rating to 0.09 so users don't finish the game with a very low product selling price
        // if purchase happiness is above 0 add to the rating
        if(purchaseHappiness > 0.0) {
            addRating(purchaseHappiness * 1.5);
            return true;
        }

        // pass through to reduce rating
        removeRating(purchaseHappiness / 1.5);
        return false;
    }
}
