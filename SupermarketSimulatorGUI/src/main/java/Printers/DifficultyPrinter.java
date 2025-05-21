package main.java.Printers;

import main.java.Enums.ActionState;
import main.java.Enums.Difficulty;
import main.java.GameState;

/**
 * @author prisha, sujal
 */
// handles printing difficulty options in the UI
public class DifficultyPrinter extends Printer {

    @Override
    public void printBody() {
        // show different explanations based on game state
        if(GameState.instance().getGameState() == ActionState.NEW_GAME) newGameOptionsExplanation();
        else optionsExplanation();

        // print available difficulty choices
        printChoices("easy", "set difficulty to easy.",
                "normal", "set difficulty to normal.",
                "hard", "set difficulty to hard.",
                "x", "go to the previous page.");
    }

    // prints difficulty options with starting balances for new games
    private void newGameOptionsExplanation() {
        for(Difficulty difficulty : Difficulty.values()) {
            System.out.printf(
                    "%-10s | Start with: $%-8.2f | %s\n",
                    difficulty.toString(),
                    difficulty.getStartingBalance(),
                    difficulty.getDescription()
            );
        }
    }

    // prints difficulty options with only descriptions
    private void optionsExplanation() {
        for(Difficulty difficulty : Difficulty.values()) {
            System.out.printf(
                    "%-10s | %s\n",
                    difficulty.toString(),
                    difficulty.getDescription()
            );
        }
    }
}
