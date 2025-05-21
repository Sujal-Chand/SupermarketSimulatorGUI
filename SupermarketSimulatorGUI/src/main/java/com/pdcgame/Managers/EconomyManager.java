package com.pdcgame.Managers;

import com.pdcgame.GameState;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class EconomyManager {
    private final GameState gameInstance = GameState.instance();
    private static final EconomyManager instance = new EconomyManager();

    public static EconomyManager getInstance(){
        return instance;
    }

    // create a new random object each time with the same seed so prices are consistent with the day
    public Random todayRandom() {
        return new Random(getSeed());  // the economy rate determines prices for items
    }

    // changes the seed for each new day
    public void newDay() {
        gameInstance.setDay(gameInstance.getDay() + 1);
    }

    // returns a seed for economy pricing
    public long getSeed() {
        int multiplier = switch (gameInstance.getDifficulty()) {
            case Normal -> 500000;
            case Hard -> 1000000;
            default -> 100000;
        };
        return gameInstance.getDay() * multiplier;
    }

    // gets the economy rate (how much each item is worth for that day)
    public double getEconomyRate() {
        double min,max;
        switch(gameInstance.getDifficulty()) {
            case Easy: {
                min = 0.98;
                max = 1.02;
                break;
            }
            case Normal: {
                min = 0.95;
                max = 1.3;
                break;
            }
            case Hard: {
                min = 0.90;
                max = 2.3;
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid difficulty");
        }
        return min + (max - min) * todayRandom().nextDouble();
    }
}
