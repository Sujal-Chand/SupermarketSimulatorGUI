package com.pdcgame.supermarketsimulatorfinal.PageInputs;

import com.pdcgame.supermarketsimulatorfinal.Enums.ActionState;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.GamePersistence;
import com.pdcgame.supermarketsimulatorfinal.Abstracts.PageInputs;
import com.pdcgame.supermarketsimulatorfinal.GameState;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class MenuInput extends PageInputs {
    private final GameState gameInstance = GameState.instance();

    // what page to go to based on input and action state
    @Override
    public PageName getPageName(Scanner scanner) {
        while(true){
            String input = scanner.nextLine().trim().toLowerCase();
            switch(input){
                case "x":
                    gameInstance.setActionState(ActionState.QUIT); // quit the game
                    return null; // returning null stops page navigator
                case "load":
                    if(!GamePersistence.saveExists()) {
                        invalid(input); // don't let user load save if it doesn't exist
                        continue;
                    }
                    gameInstance.setActionState(ActionState.LOAD_SAVE);
                    return PageName.GAME_MENU_PAGE;
                case "new game":
                    gameInstance.setActionState(ActionState.NEW_GAME); // start new game
                    return PageName.DIFFICULTY_PAGE;
                default: invalid(input);
            }
        }
    }
}
