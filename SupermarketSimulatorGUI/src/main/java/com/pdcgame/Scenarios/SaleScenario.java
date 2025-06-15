package com.pdcgame.Scenarios;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.Managers.RatingManager;
import com.pdcgame.Managers.ScenarioManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.pdcgame.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class SaleScenario implements Scenario {

    private static final GameState gameInstance = GameState.instance();
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private final Random random = new Random();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear();

        List<String> happyDialog = List.of(
            "Customer found your products well priced!",
            "Customer praised the quality and value!",
            "Customer left a 5-star review!",
            "Customer told their friends about your store!",
            "Customer said it's the best deal they've seen all week!",
            "Customer smiled and said, 'Now that's a bargain!'",
            "Customer appreciated the fair pricing during tough times!",
            "Customer said your store has become their go-to spot!",
            "Customer said you understand real value!",
            "Customer said, 'Deals like this restore my faith in humanity.'",
            "Customer was so happy they did a little dance.",
            "Customer said, 'You sure you're not losing money on this?'",
            "Customer called their mum to brag about the deal.",
            "Customer took a selfie with the receipt."
        );

        List<String> disappointedDialog = List.of(
            "Customer thinks you're a greedy scammer!",
            "Customer walked out muttering about daylight robbery.",
            "Customer said the prices are a joke — and not a funny one!",
            "Customer said they’ll shop at your competitor instead.",
            "Customer shook their head and said, 'Not worth it.'",
            "Customer said, 'At these prices? I'd rather starve!'",
            "Customer gave you a 1-star review out of spite.",
            "Customer said the product isn’t worth half that!",
            "Customer told their friends never to shop here again!",
            "Customer said, 'You're pricing like it's a luxury boutique!'",
            "Customer asked if gold was an ingredient.",
            "Customer stared at the price like it insulted their ancestors.",
            "Customer mumbled, 'This better come with a free yacht.'",
            "Customer asked if you take payment in kidneys.",
            "Customer said, 'This isn't a store, it's a scam simulator.'",
            "Customer whispered, 'You're the final boss of overpriced goods.'"
        );

        String happyCustomer = happyDialog.get(random.nextInt(happyDialog.size()));
        String disappointedCustomer = disappointedDialog.get(random.nextInt(disappointedDialog.size()));

        if (!scenarioManager.outOfStock(product)) {
            boolean happyPurchase = RatingManager.purchaseHappiness(product);
            int quantity = Math.min(
                gameInstance.getFloorStorageManager().totalQuantityOnFloor(product),
                random.nextInt(5) + 1
            );
            if (!happyPurchase) {
                quantity = 1;
            }

            gameInstance.getFloorStorageManager().randomRemoveProduct(product, quantity);
            double price = gameInstance.getProductManager().getSellPrice(product) * quantity;
            gameInstance.getEndOfDayManager().addSale(price);

            messages.add("[SALE] " + quantity + "x " + product + " sold for $" + String.format("%.2f", price));

            if (happyPurchase) {
                messages.add("[HAPPINESS] " + happyCustomer);
            } else {
                messages.add("[DISAPPOINTMENT] " + disappointedCustomer);
            }
        } else {
            messages.add("[LOST SALE] Tried to sell " + product + ", but it's out of stock.");
            RatingManager.removeRating(0.010);
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
