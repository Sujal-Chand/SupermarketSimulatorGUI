package com.pdcgame.Scenarios;

import com.pdcgame.Interfaces.Scenario;
import static com.pdcgame.Printers.Printer.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prisha, sujal
 */
public class QuietScenario implements Scenario {

    private final List<String> messages = new ArrayList<>();

    @Override
    public void execute(String product) {
        messages.clear();
        messages.add("[QUIET]");
        messages.add("Nothing happened this hour...*crickets*");
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
