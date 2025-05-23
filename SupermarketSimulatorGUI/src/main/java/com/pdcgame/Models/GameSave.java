/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Models;

import com.pdcgame.Enums.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 *
 * @author sujal
 */
@Entity
@Table(name = "game_save")
public class GameSave {
    @Id
    private int id = 1; // singleton save
    
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    
    private double balance;
    private int totalActions;
    private int actions;
    private double rating;
    private long day;
    private long gameSeed;

    // getters
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public double getBalance() {
        return balance;
    }

    public int getTotalActions() {
        return totalActions;
    }

    public int getActions() {
        return actions;
    }

    public double getRating() {
        return rating;
    }

    public long getDay() {
        return day;
    }

    public long getGameSeed() {
        return gameSeed;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTotalActions(int totalActions) {
        this.totalActions = totalActions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public void setGameSeed(long gameSeed) {
        this.gameSeed = gameSeed;
    }
    
    
}
