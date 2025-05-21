package com.pdcgame.Scenarios;

import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Interfaces.Scenario;
import static com.pdcgame.Printers.Printer.*;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class WeatherScenario implements Scenario {
    private final Random random = new Random();

    @Override
    public void execute(String product) {
        printWithDelay("\n[WEATHER] ", SMALL_DELAY);
        if (random.nextBoolean()) {
            printWithDelay("Good weather increases foot traffic! Sales volume boosted by 20%.", BIG_DELAY);
            ScenarioType.SALE.setWeight((int)(ScenarioType.SALE.getWeight() * 1.2)); //apply sales boost
        } else {
            printWithDelay("Bad weather keeps customers at home. Sales volume reduced by 20%.", BIG_DELAY);
            ScenarioType.SALE.setWeight((int)(ScenarioType.SALE.getWeight() * 0.8)); //apply sales penalty
        }
    }

    @Override
    public boolean needsProduct() {
        return false;
    }
}
