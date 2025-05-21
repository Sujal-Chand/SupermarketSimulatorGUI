package main.java.PageInputs;

import main.java.Abstracts.PageInputs;
import main.java.Enums.PageName;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class StorageInput extends PageInputs {
    // decide what page to go to next
    @Override
    public PageName getPageName(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch(input) {
                case "x": return PageName.GAME_MENU_PAGE;
                case "1":
                case "move": return PageName.INVENTORY_STORAGE_PAGE;
                default: invalid(input);
            }
        }
    }
}
