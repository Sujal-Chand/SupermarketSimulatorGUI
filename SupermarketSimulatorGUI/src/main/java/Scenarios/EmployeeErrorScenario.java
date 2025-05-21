package main.java.Scenarios;

import main.java.Enums.Difficulty;
import main.java.GameState;
import main.java.Interfaces.Scenario;
import main.java.Managers.BankManager;
import main.java.Managers.ScenarioManager;
import java.util.Random;

import static main.java.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class EmployeeErrorScenario implements Scenario {
    private final Random random = new Random();
    ScenarioManager scenarioManager = new ScenarioManager();
    private static final GameState gameInstance = GameState.instance();

    //set max incorrect change based off game difficulty
    private double maxIncorrectChange() {
        switch (gameInstance.getDifficulty()) {
            case Difficulty.Easy -> {
                return 10.00;
            }
            case Difficulty.Normal -> {
                return 20.00;
            }
            case Difficulty.Hard -> {
                return 40.00;
            }
            default -> {
                return 0.0;
            }
        }
    }

    @Override
    public void execute(String product) {
        double incorrectChange = 1 + random.nextDouble(maxIncorrectChange()); // get random double for incorrect change (at least 1)

        //50% chance of either events happening
        if (random.nextBoolean()) {
            //check if product is on shop floor
            if (!scenarioManager.outOfStock(product)) {
                printWithDelay("\n[EMPLOYEE ERROR] ", SMALL_DELAY);
                printWithDelay("Mistakenly removed 1 " + product + " from shop floor.", BIG_DELAY);
                gameInstance.getFloorStorageManager().randomRemoveProduct(product, 1); //removes product from shop floor
            }
        } else {
            //check if there is enough funds for incorrect change
            if(BankManager.possiblePurchase(incorrectChange)) {
                printWithDelay("\n[EMPLOYEE ERROR] ", SMALL_DELAY);
                printWithDelay("Mistakenly gave $" + String.format("%.2f", incorrectChange) + " of incorrect change.", BIG_DELAY);
                BankManager.subtractBalance(incorrectChange); //subtract incorrect change from balance
            }

        }
    }

    @Override
    public boolean needsProduct() {
        return true;
    }
}
