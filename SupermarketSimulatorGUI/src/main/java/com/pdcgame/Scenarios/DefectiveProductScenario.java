package com.pdcgame.Scenarios;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.Managers.ScenarioManager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.pdcgame.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class DefectiveProductScenario implements Scenario {

    private static final GameState gameInstance = GameState.instance();
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private final Random random = new Random();

    //Defect Product Reasons
    private static final List<String> defectReasons = Arrays.asList(
            "contamination during packaging",
            "a critical safety fault identified by the supplier",
            "incorrect labelling that violates regulations",
            "customer reports of allergic reactions",
            "a manufacturing defect causing short shelf life",
            "potential choking hazard for children",
            "mould found during a routine inspection",
            "product not meeting advertised nutritional standards"
    );

    @Override
    public void execute(String product) {

        int inventoryQuantity = gameInstance.getInventoryManager().getQuantity(product);
        int floorQuantity = gameInstance.getFloorStorageManager().totalQuantityOnFloor(product);
        int totalQuantity = inventoryQuantity + floorQuantity; //gets total product quantity from inventory and shop floor

        //if product is in stock
        if (totalQuantity > 0) {
            String reason = defectReasons.get(random.nextInt(defectReasons.size())); //get random reason

            printWithDelay("\n[DEFECTIVE] ", SMALL_DELAY);
            printWithDelay("A defective batch of " + product + " has been recalled due to " + reason + ". ", BIG_DELAY);
            printWithDelay(totalQuantity + " items pulled from shop floor and inventory.", BIG_DELAY);

            gameInstance.getInventoryManager().removeProduct(product, inventoryQuantity); //remove from inventory
            gameInstance.getFloorStorageManager().randomRemoveProduct(product, floorQuantity); //remove from shop floor
        }
    }

    @Override
    public boolean needsProduct() {
        return true;
    }
}
