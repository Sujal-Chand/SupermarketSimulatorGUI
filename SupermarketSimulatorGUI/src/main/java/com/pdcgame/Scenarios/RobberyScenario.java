package com.pdcgame.Scenarios;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.Managers.ScenarioManager;
import com.pdcgame.ProductTypes.PurchasableProduct;
import static com.pdcgame.Printers.Printer.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class RobberyScenario implements Scenario {
    private final Random random = new Random();
    private static final GameState gameInstance = GameState.instance();
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear();

        if (!scenarioManager.outOfStock(product)) {
            int limit = Math.min(gameInstance.getFloorStorageManager().totalQuantityOnFloor(product), 10);
            int stolen = random.nextInt(limit);

            if (stolen > 0) {
                PurchasableProduct productStolen = gameInstance.getProductManager().getProduct(product);
                double stolenValue = productStolen.getSinglePrice() * stolen;

                gameInstance.getEndOfDayManager().addRobbery(stolen, stolenValue);
                gameInstance.getFloorStorageManager().randomRemoveProduct(product, stolen);

                messages.add("[ROBBERY]");
                messages.add("Robbers stole " + stolen + " x " + product + " worth $" + String.format("%.2f", stolenValue));
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
