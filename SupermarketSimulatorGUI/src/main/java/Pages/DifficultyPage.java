package main.java.Pages;

import main.java.Enums.ActionState;
import main.java.Enums.PageName;
import main.java.Abstracts.Page;
import main.java.GameState;
import main.java.PageInputs.DifficultyInput;
import main.java.Printers.DifficultyPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class DifficultyPage extends Page {
    // use singleton GameState instance
    private final GameState gameInstance = GameState.instance();
    private final DifficultyPrinter difficultyPrinter = new DifficultyPrinter(); // printer
    private final DifficultyInput difficultyInput = new DifficultyInput();

    // constructor
    public DifficultyPage(){
        super(PageName.DIFFICULTY_PAGE);
    }


    // display
    @Override
    public PageName display (Scanner scanner) {
        pageHeader();
        difficultyPrinter.printBody();
        return difficultyInput.getPageName(scanner);

    }


    // header
    @Override
    public void pageHeader(){
        pageHeader(gameInstance.getGameState() == ActionState.NEW_GAME ? "CHOOSE A DIFFICULTY" : "CHANGE DIFFICULTY");
    }
}
