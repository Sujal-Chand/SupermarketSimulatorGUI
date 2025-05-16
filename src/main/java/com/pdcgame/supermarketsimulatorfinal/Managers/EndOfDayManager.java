package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.GamePersistence;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Printers.EndOfDayPrinter;

/**
 * @author prisha, sujal
 */
public class EndOfDayManager {

    private final EndOfDayPrinter printer = new EndOfDayPrinter();
    private int salesAmount = 0;
    private double salesValue = 0.0;
    private int robberyAmount = 0;
    private double robberyValue = 0.0;
    private int robberyQty = 0;

    //add sale stats
    public void addSale(double value) {
        salesAmount++;
        salesValue += value;
    }

    //add robbery stats
    public void addRobbery(int qty, double value) {
        robberyAmount++;
        robberyQty += qty;
        robberyValue += value;
    }

    //reset counts
    public void reset() {
        salesAmount = 0;
        salesValue = 0.0;
        robberyAmount = 0;
        robberyQty = 0;
        robberyValue = 0.0;
    }

    //getters
    public int getSalesAmount() {
        return salesAmount;
    }

    public double getSalesValue() {
        return salesValue;
    }

    public int getRobberyAmount() {
        return robberyAmount;
    }

    public double getRobberyValue() {
        return robberyValue;
    }

    public int getRobberyQty() {
        return robberyQty;
    }

    //methods after store closes
    public void handleEndOfDay() {
        printer.printReport(this);//prints report when store closes
        BankManager.addBalance(salesValue); //adds sales value to players balance
        reset(); //resets counts
        ActionManager.resetActions(); //resets action count
        EconomyManager.getInstance().newDay(); //generates new day
        GamePersistence.saveGame(); //saves changes to files
    }
}
