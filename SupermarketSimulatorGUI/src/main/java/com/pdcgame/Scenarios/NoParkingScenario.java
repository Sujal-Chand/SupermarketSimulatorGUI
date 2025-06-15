package com.pdcgame.Scenarios;

import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Interfaces.Scenario;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prisha, sujal
 */
public class NoParkingScenario implements Scenario {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear(); // Clear any old messages

        messages.add("[NO PARKING]");
        messages.add("Customers are having trouble finding parking. Reduced foot traffic.");

        // Apply penalty to SALE weight
        ScenarioType.SALE.setWeight((int) (ScenarioType.SALE.getWeight() * 0.95));
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
