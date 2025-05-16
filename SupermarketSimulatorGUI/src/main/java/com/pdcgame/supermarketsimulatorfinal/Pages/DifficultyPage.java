package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Enums.ActionState;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.PageInputs.DifficultyInput;
import com.pdcgame.supermarketsimulatorfinal.Printers.DifficultyPrinter;

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
