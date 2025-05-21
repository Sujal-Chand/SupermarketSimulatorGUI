package com.pdcgame.Printers;

import com.pdcgame.GamePersistence;

/**
 * @author prisha, sujal
 */
public class MenuPrinter extends Printer {
    @Override
    public void printBody() {
        System.out.println("Welcome to Supermarket Simulator!");
    }

    @Override
    public void printChoices(String... choicesWithDescription) {
        if(choicesWithDescription.length % 2 != 0){
            throw new IllegalArgumentException("Choices were not paired");
        }
        System.out.println(); // vertical padding

        int count = 0;
        for(String text : choicesWithDescription) {
            count++;
            // the '|| count > 2' prevents printing the 'load' choice option if a save file doesn't exist
            if(GamePersistence.saveExists() || count > 2) System.out.printf(count % 2 == 1 ?
                    "type '%s' " : "to %s\n", text);
        }
        System.out.print(">> ");
    }
}
