package com.pdcgame.Scenarios;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.GameState;
import com.pdcgame.Interfaces.Scenario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author prisha, sujal
 */
public class LongLineScenario implements Scenario {
    private final Random random = new Random();
    private final GameState gameInstance = GameState.instance();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear(); // Clear previous messages

        List<String> reasons = new ArrayList<>(Arrays.asList(
            "the checkout decided to update Windows mid-transaction",
            "the cashier is speedrunning how slow they can scan items",
            "everyone deciding to shop at the same time and the manager is still 'looking into it'",
            "a trainee on till #1 and it’s their first day",
            "the only open register is also doing returns, lotto, and passport photos",
            "the cashier got into a deep conversation about the meaning of life with a customer",
            "the manager is trying to fix the till with duct tape",
            "the store thought one cashier would be 'more than enough'",
            "half the staff calling in sick after taco night"
        ));

        // Add a special reason if there's only one cashier
        if (gameInstance.getBoardManager().getEquipmentCount(BoardCell.CASHIER) == 1) {
            reasons.add("only one cashier is working and they're on their lunch break");
        }

        String selectedReason = reasons.get(random.nextInt(reasons.size()));

        messages.add("[LONG LINES]");
        messages.add("Due to " + selectedReason + ".");
        messages.add("Customer left the store!");
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
