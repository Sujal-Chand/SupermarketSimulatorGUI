package com.pdcgame.Scenarios;

import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Interfaces.Scenario;
import static com.pdcgame.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class EconomicDownturnScenario implements Scenario {

    @Override
    public void execute(String product) {
        printWithDelay("\n[ECONOMY] ", SMALL_DELAY);
        printWithDelay("People are spending less today. Sales value reduced by 20%.", BIG_DELAY);
        ScenarioType.SALE.setWeight((int)(ScenarioType.SALE.getWeight() * 0.8)); //apply penalty
    }

    @Override
    public boolean needsProduct() {
        return false;
    }
}
