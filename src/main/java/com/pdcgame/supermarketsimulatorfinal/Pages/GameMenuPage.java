package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.DisplayObjects.GameBoard;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.GamePersistence;
import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Managers.ProductManager;
import com.pdcgame.supermarketsimulatorfinal.PageInputs.GameMenuInput;
import com.pdcgame.supermarketsimulatorfinal.Printers.GameMenuPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class GameMenuPage extends Page {
    private final GameMenuPrinter gameMenuPrinter = new GameMenuPrinter(); // printer
    private final GameMenuInput gameMenuInput = new GameMenuInput();

    // constructor
    public GameMenuPage() {
        super(PageName.GAME_MENU_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner){
        pageHeader("Game Menu");
        GamePersistence.loadGame();
        if(!gameMenuPrinter.isGameOver()) return PageName.GAME_OVER_PAGE; // try seeing if user reached Game over screen
        ActionManager.tryActionUpdate();
        gameMenuPrinter.printBody();
        gameMenuPrinter.printChoices("equipment", "go to Equipment page.",
                "storage", "go to Storage/Inventory page.",
                "product", "go to Product Purchase page.",
                "open", "open your store.",
                "x", "save and quit to menu.");
        return gameMenuInput.getPageName(scanner);
    }

}
