package com.pdcgame.supermarketsimulatorfinal.Scenarios;

import com.pdcgame.supermarketsimulatorfinal.Enums.ScenarioType;
import com.pdcgame.supermarketsimulatorfinal.Interfaces.Scenario;

import static com.pdcgame.supermarketsimulatorfinal.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class NoParkingScenario implements Scenario {

    @Override
    public void execute(String product) {
        printWithDelay("\n[NO PARKING] ", SMALL_DELAY);
        printWithDelay("Customers are having trouble finding parking. Reduced foot traffic.", SMALL_DELAY);
        ScenarioType.SALE.setWeight((int)(ScenarioType.SALE.getWeight() * 0.95)); //apply sales penalty of 5%
    }

    @Override
    public boolean needsProduct() {
        return false;
    }
}
