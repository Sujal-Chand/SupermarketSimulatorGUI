package com.pdcgame.Models;

import com.pdcgame.Enums.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "game_save")
public class GameSave {
    @Id
    private int id = 1; // singleton save

    private Difficulty difficulty;
    private double balance;
    private int totalActions;
    private int actions;
    private double rating;
    private long day;
    private long gameSeed;
    
    // getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getTotalActions() {
        return totalActions;
    }

    public void setTotalActions(int totalActions) {
        this.totalActions = totalActions;
    }

    public int getActions() {
        return actions;
    }

    // setters
    public void setActions(int actions) {
        this.actions = actions;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public long getGameSeed() {
        return gameSeed;
    }

    public void setGameSeed(long gameSeed) {
        this.gameSeed = gameSeed;
    }
}
