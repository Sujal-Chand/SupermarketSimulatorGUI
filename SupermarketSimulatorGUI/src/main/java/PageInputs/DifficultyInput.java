package main.java.PageInputs;

import main.java.Abstracts.PageInputs;
import main.java.Enums.ActionState;
import main.java.Enums.Difficulty;
import main.java.Enums.PageName;
import main.java.GamePersistence;
import main.java.GameState;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */

// try getting difficulty from input before starting game
public class DifficultyInput extends PageInputs {
    // what page to go to based on input
    @Override
    public PageName getPageName(Scanner scanner) {
        while(true){
            String input = scanner.nextLine().trim().toLowerCase();
            switch(input){
                case "x":
                    // go back to menu
                    if(GameState.instance().getGameState() == ActionState.LOAD_SAVE) return PageName.GAME_MENU_PAGE;
                    return PageName.MENU_PAGE;
                case "easy":
                    GameState.instance().setDifficulty(Difficulty.Easy);
                    GamePersistence.saveGame();
                    return PageName.GAME_MENU_PAGE;
                case "normal":
                    GameState.instance().setDifficulty(Difficulty.Normal);
                    GamePersistence.saveGame();
                    return PageName.GAME_MENU_PAGE;
                case "hard":
                    GameState.instance().setDifficulty(Difficulty.Hard);
                    GamePersistence.saveGame();
                    return PageName.GAME_MENU_PAGE;
                default:
                    invalid(input);
                    break;
            }
        }
    }
}
