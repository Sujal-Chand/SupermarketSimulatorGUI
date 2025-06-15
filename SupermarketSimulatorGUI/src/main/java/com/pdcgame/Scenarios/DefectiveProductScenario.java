package com.pdcgame.Scenarios;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * @author prisha, sujal
 */
public class DefectiveProductScenario implements Scenario {

    private static final GameState gameInstance = GameState.instance();
    private final Random random = new Random();
    private final List<String> messages = new ArrayList<>();

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
        messages.clear();

        int inventoryQuantity = gameInstance.getInventoryManager().getQuantity(product);
        int floorQuantity = gameInstance.getFloorStorageManager().totalQuantityOnFloor(product);
        int totalQuantity = inventoryQuantity + floorQuantity;

        if (totalQuantity > 0) {
            String reason = defectReasons.get(random.nextInt(defectReasons.size()));

            messages.add("[DEFECTIVE]");
            messages.add("A defective batch of " + product + " has been recalled due to " + reason + ".");
            messages.add(totalQuantity + " items pulled from shop floor and inventory.");

            gameInstance.getInventoryManager().removeProduct(product, inventoryQuantity);
            gameInstance.getFloorStorageManager().randomRemoveProduct(product, floorQuantity);
        } else {
            messages.add("[DEFECTIVE]");
            messages.add("No " + product + " in stock, so no recall was needed.");
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
