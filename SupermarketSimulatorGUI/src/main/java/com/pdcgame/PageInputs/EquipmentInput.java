package com.pdcgame.PageInputs;

import com.pdcgame.Abstracts.PageInputs;
import com.pdcgame.Enums.PageName;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class EquipmentInput extends PageInputs {
    // what page to go to based on input
    @Override
    public PageName getPageName(Scanner scanner) {
        while(true){
            String input = scanner.nextLine().trim().toLowerCase();
            switch(input){
                case "x": return PageName.GAME_MENU_PAGE; // game menu page
                case "1":
                case "buy": return PageName.BUY_EQUIPMENT_PAGE; // buy equipment page
                case "2":
                case "sell": return PageName.SELL_EQUIPMENT_PAGE; // sell equipment page
                default: invalid(input);
            }
        }
    }
}
