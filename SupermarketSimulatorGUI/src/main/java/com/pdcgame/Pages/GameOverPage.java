package com.pdcgame.Pages;

import com.pdcgame.Abstracts.Page;
import com.pdcgame.Enums.PageName;
import com.pdcgame.Printers.GameOverPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class GameOverPage extends Page {
    private final GameOverPrinter gameOverPrinter = new GameOverPrinter(); // printer

    // constructor
    public GameOverPage(){
        super(PageName.GAME_OVER_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner) throws InterruptedException {
        pageHeader("GAME ENDING");
        gameOverPrinter.printEnding(scanner);
        return PageName.MENU_PAGE;
    }
}
