package com.pdcgame.Scenarios;

import com.pdcgame.Interfaces.Scenario;
import static com.pdcgame.Printers.Printer.*;

/**
 * @author prisha, sujal
 */
public class QuietScenario implements Scenario {

    @Override
    public void execute(String product) {
        printWithDelay("\n[QUIET] ", SMALL_DELAY);
        printWithDelay("Nothing happened this hour...*crickets*", BIG_DELAY);
    }

    @Override
    public boolean needsProduct() {
        return false;
    }
}
