package com.pdcgame.supermarketsimulatorfinal.Scenarios;

import com.pdcgame.supermarketsimulatorfinal.Interfaces.Scenario;

import static com.pdcgame.supermarketsimulatorfinal.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class QuietScenario implements Scenario {

    @Override
    public void execute(String product) {
        printWithDelay("\n[QUIET] ", SMALL_DELAY);
        printWithDelay("Nothing happened this hour...*crickets*", BIG_DELAY);
    }

    @Override
    public boolean needsProduct() {
        return false;
    }
}
