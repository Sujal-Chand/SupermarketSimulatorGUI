package com.pdcgame.Scenarios;


import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.Managers.ScenarioManager;
import static com.pdcgame.Printers.Printer.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class ProductExpiryScenario implements Scenario {

    private final Random random = new Random();
    private static final GameState gameInstance = GameState.instance();
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear(); // Clear previous messages

        if (!scenarioManager.outOfStock(product)) {
            int limit = Math.min(gameInstance.getFloorStorageManager().totalQuantityOnFloor(product), 10);
            int expired = random.nextInt(limit); // 0 to limit-1

            if (expired > 0) {
                messages.add("[EXPIRY]");
                messages.add(expired + " x " + product + " expired and were removed from shop floor.");
                gameInstance.getFloorStorageManager().randomRemoveProduct(product, expired);
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