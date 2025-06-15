package com.pdcgame.Scenarios;

import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Interfaces.Scenario;
import static com.pdcgame.Printers.Printer.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class WeatherScenario implements Scenario {
    private final Random random = new Random();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear();

        messages.add("[WEATHER]");

        if (random.nextBoolean()) {
            messages.add("Good weather increases foot traffic! Sales volume boosted by 20%.");
            ScenarioType.SALE.setWeight((int) (ScenarioType.SALE.getWeight() * 1.2));
        } else {
            messages.add("Bad weather keeps customers at home. Sales volume reduced by 20%.");
            ScenarioType.SALE.setWeight((int) (ScenarioType.SALE.getWeight() * 0.8));
        }
    }

    @Override
    public boolean needsProduct() {
        return false;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
