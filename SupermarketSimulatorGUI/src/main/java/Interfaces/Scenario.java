package main.java.Interfaces;

/**
 * @author prisha, sujal
 */
public interface Scenario {
    void execute(String product); //sends a random product name through to scenarios using scenario manager
    boolean needsProduct(); //for future use, checks if scenario uses product or not
}
