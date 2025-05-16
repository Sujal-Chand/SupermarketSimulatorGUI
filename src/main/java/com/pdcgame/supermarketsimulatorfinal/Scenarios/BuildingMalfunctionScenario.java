package com.pdcgame.supermarketsimulatorfinal.Scenarios;

import com.pdcgame.supermarketsimulatorfinal.Interfaces.Scenario;
import com.pdcgame.supermarketsimulatorfinal.Managers.BankManager;

import static com.pdcgame.supermarketsimulatorfinal.Printers.Printer.*;

import java.util.*;

/**
 * @author prisha, sujal
 */
public class BuildingMalfunctionScenario implements Scenario {
    private final Random random = new Random();

    @Override
    public void execute(String product) {
        //all issues along with their costs to repair
        Map<String, Double> issueCosts = new HashMap<>();
        issueCosts.put("plumbing failure", 89.00);
        issueCosts.put("electrical outage", 120.00);
        issueCosts.put("roof leak", 250.00);
        issueCosts.put("broken air conditioning", 47.00);
        issueCosts.put("floorboard malfunction", 49.00);
        issueCosts.put("flickering lights", 36.00);
        issueCosts.put("blocked drainage", 60.00);
        issueCosts.put("fire alarm fault", 41.00);

        List<Map.Entry<String, Double>> entries = new ArrayList<>(issueCosts.entrySet());//get random issue
        Map.Entry<String, Double> selectedIssue = entries.get(random.nextInt(entries.size())); //get issue repair cost

        printWithDelay("\n[BUILDING ISSUE] ", SMALL_DELAY);
        printWithDelay("Store closes for an hour due to " + selectedIssue.getKey() + ".\n", BIG_DELAY);
        printWithDelay("No sales made during this time.\n", BIG_DELAY);
        printWithDelay("You are charged $" + String.format("%.2f", selectedIssue.getValue()) + " for repairs.", BIG_DELAY);

        BankManager.subtractBalance(selectedIssue.getValue()); //deduct repair cost from balance
    }

    @Override
    public boolean needsProduct() {
        return false;
    }
}
