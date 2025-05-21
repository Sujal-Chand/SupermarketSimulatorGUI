package com.pdcgame.Printers;

/**
 * @author prisha, sujal
 */
public class EquipmentPrinter extends Printer {
    // prints the equipment page body
    @Override
    public void printBody(){
        System.out.println("Welcome to the Equipment page!");
        System.out.println("Here you can choose to buy, and sell your equipment");
    }
}
