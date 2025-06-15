package com.pdcgame.Interfaces;

/**
 * @author prisha, sujal
 */

import java.util.List;  

public interface Scenario {
    void execute(String product); //sends a random product name through to scenarios using scenario manager
    boolean needsProduct(); //for future use, checks if scenario uses product or not
    default List<String> getMessages() {
        return List.of();
    }
}
