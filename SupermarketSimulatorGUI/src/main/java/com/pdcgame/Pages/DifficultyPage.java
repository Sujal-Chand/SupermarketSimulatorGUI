package com.pdcgame.Pages;

import com.pdcgame.Enums.ActionState;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Abstracts.Page;
import com.pdcgame.GameState;
import com.pdcgame.PageInputs.DifficultyInput;
import com.pdcgame.Printers.DifficultyPrinter;

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
