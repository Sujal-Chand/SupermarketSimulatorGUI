package com.pdcgame.supermarketsimulatorfinal.PageInputs;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.PageInputs;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
// game menu input to decide what page to go to next
public class GameMenuInput extends PageInputs {
    // what page to go to based on input
    @Override
    public PageName getPageName(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "x":
                    return PageName.MENU_PAGE;
                case "1":
                case "equipment":
                    return PageName.EQUIPMENT_PAGE;
                case "2":
                case "storage":
                    return PageName.STORAGE_PAGE;
                case "3":
                case "product":
                    return PageName.PRODUCTS_PAGE;
                case "4":
                case "open":
                    return PageName.SCENARIO_PAGE;
                default:
                    invalid(input);
            }
        }
    }
}
