package com.pdcgame.Scenarios;

import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.Interfaces.Scenario;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prisha, sujal
 */
public class EconomicDownturnScenario implements Scenario {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear();

        messages.add("[ECONOMY]");
        messages.add("People are spending less today. Sales value reduced by 20%.");

        ScenarioType.SALE.setWeight((int)(ScenarioType.SALE.getWeight() * 0.8));
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
