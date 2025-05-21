package main.java.Scenarios;

import main.java.GameState;
import main.java.Interfaces.Scenario;
import main.java.Managers.ScenarioManager;
import main.java.ProductTypes.PurchasableProduct;
import static main.java.Printers.Printer.*;

import java.util.Random;

/**
 * @author prisha, sujal
 */
public class RobberyScenario implements Scenario {
    private final Random random = new Random();
    private static final GameState gameInstance = GameState.instance();
    ScenarioManager scenarioManager = new ScenarioManager();

    @Override
    public void execute(String product) {
        //check if product is on shop floor
        if (!scenarioManager.outOfStock(product)) {
            int limit = Math.min(gameInstance.getFloorStorageManager().totalQuantityOnFloor(product), 10); //sets limit of 10 or amount of stock on shop floor
            int stolen = random.nextInt(limit); //grabs a random amount within limit
            //check if stolen amount is more than 0
            if(stolen > 0) {
                PurchasableProduct productStolen = gameInstance.getProductManager().getProduct(product);
                double stolenValue = (productStolen.getSinglePrice() * stolen); //calculates stolen value
                gameInstance.getEndOfDayManager().addRobbery(stolen, stolenValue); //adds stolen amount and value to game stats for displaying later

                printWithDelay("\n[ROBBERY] ", SMALL_DELAY);
                printWithDelay("Robbers stole " + stolen + " x " + product + " worth $" + String.format("%.2f", stolenValue), BIG_DELAY);
                gameInstance.getFloorStorageManager().randomRemoveProduct(product, stolen); //removes product from shop floor
            }
        }
    }

    @Override
    public boolean needsProduct() {
        return true;
    }
}
