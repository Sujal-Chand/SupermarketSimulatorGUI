package com.pdcgame.Scenarios;

import com.pdcgame.Enums.Difficulty;
import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.Managers.BankManager;
import com.pdcgame.Managers.ScenarioManager;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prisha, sujal
 */
public class EmployeeErrorScenario implements Scenario {
    private final Random random = new Random();
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private static final GameState gameInstance = GameState.instance();
    private final List<String> messages = new ArrayList<>();

    private double maxIncorrectChange() {
        return switch (gameInstance.getDifficulty()) {
            case Difficulty.Easy -> 10.00;
            case Difficulty.Normal -> 20.00;
            case Difficulty.Hard -> 40.00;
        };
    }

    @Override
    public void execute(String product) {
        messages.clear();  // Clear previous messages
        double incorrectChange = 1 + random.nextDouble(maxIncorrectChange());

        if (random.nextBoolean()) {
            if (!scenarioManager.outOfStock(product)) {
                messages.add("[EMPLOYEE ERROR]");
                messages.add("Mistakenly removed 1 " + product + " from shop floor.");
                gameInstance.getFloorStorageManager().randomRemoveProduct(product, 1);
            }
        } else {
            if (BankManager.possiblePurchase(incorrectChange)) {
                messages.add("[EMPLOYEE ERROR]");
                messages.add("Mistakenly gave $" + String.format("%.2f", incorrectChange) + " of incorrect change.");
                BankManager.subtractBalance(incorrectChange);
            }
        }
    }

    @Override
    public boolean needsProduct() {
        return true;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }
}
