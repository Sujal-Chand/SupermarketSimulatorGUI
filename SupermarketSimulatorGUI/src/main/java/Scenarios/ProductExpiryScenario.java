package main.java.Scenarios;


import main.java.GameState;
import main.java.Interfaces.Scenario;
import main.java.Managers.ScenarioManager;
import static main.java.Printers.Printer.*;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class ProductExpiryScenario implements Scenario {
    private final Random random = new Random();
    private static final GameState gameInstance = GameState.instance();
    ScenarioManager scenarioManager = new ScenarioManager();

    @Override
    public void execute(String product) {
        //check if product is on shop floor
        if (!scenarioManager.outOfStock(product)) {
            int limit = Math.min(gameInstance.getFloorStorageManager().totalQuantityOnFloor(product), 10); //sets limit of 10 or amount of stock
            int expired = random.nextInt(limit); //grabs a random amount within limit
            //check if expired amount is more than 0
            if(expired > 0) {
                printWithDelay("\n[EXPIRY] ", SMALL_DELAY);
                printWithDelay(expired + " x " + product + " expired and were removed from shop floor.", BIG_DELAY);
                gameInstance.getFloorStorageManager().randomRemoveProduct(product, expired); // removes product from shop floor
            }
        }
    }

    @Override
    public boolean needsProduct() {
        return true;
    }
}
