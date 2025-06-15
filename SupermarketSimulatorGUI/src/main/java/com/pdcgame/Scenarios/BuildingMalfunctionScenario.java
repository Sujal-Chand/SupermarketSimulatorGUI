package com.pdcgame.Scenarios;

import com.pdcgame.Interfaces.Scenario;
import com.pdcgame.Managers.BankManager;
import java.util.*;

/**
 * @author prisha, sujal
 */
public class BuildingMalfunctionScenario implements Scenario {
    private final Random random = new Random();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear();

        Map<String, Double> issueCosts = new HashMap<>();
        issueCosts.put("plumbing failure", 89.00);
        issueCosts.put("electrical outage", 120.00);
        issueCosts.put("roof leak", 250.00);
        issueCosts.put("broken air conditioning", 47.00);
        issueCosts.put("floorboard malfunction", 49.00);
        issueCosts.put("flickering lights", 36.00);
        issueCosts.put("blocked drainage", 60.00);
        issueCosts.put("fire alarm fault", 41.00);

        List<Map.Entry<String, Double>> entries = new ArrayList<>(issueCosts.entrySet());
        Map.Entry<String, Double> selectedIssue = entries.get(random.nextInt(entries.size()));

        messages.add("[BUILDING ISSUE]");
        messages.add("Store closes for an hour due to " + selectedIssue.getKey() + ".");
        messages.add("No sales made during this time.");
        messages.add("You are charged $" + String.format("%.2f", selectedIssue.getValue()) + " for repairs.");

        BankManager.subtractBalance(selectedIssue.getValue());
    }

    @Override
    public boolean needsProduct() {
        return false;
    }

    public List<String> getMessages() {
        return messages;
    }
}
