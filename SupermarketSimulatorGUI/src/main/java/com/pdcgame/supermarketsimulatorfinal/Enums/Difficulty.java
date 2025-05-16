package com.pdcgame.supermarketsimulatorfinal.Enums;

/**
 * @author prisha, sujal
 */
public enum Difficulty {
    Easy(5000, "Staff & customers are chill. Fewer unlucky events."),
    Normal(3500, "A balanced game. Unlucky events happen regularly."),
    Hard(2500, "It’s chaos. Difficult people & frequent disasters.");

    private final double startingBalance;
    private final String description;

    // enum constructor
    Difficulty(double startingBalance, String description) {
        this.startingBalance = startingBalance;
        this.description = description;
    }

    public double getStartingBalance() {
        return this.startingBalance;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name().substring(0,1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
