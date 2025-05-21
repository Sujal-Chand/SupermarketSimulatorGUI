package main.java.Scenarios;

import main.java.Interfaces.Scenario;
import static main.java.Printers.Printer.*;

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
