package com.pdcgame.Pages;

import com.pdcgame.DisplayObjects.GameBoard;
import com.pdcgame.Enums.PageName;
import com.pdcgame.GamePersistence;
import com.pdcgame.Abstracts.Page;
import com.pdcgame.GameState;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Managers.ProductManager;
import com.pdcgame.PageInputs.GameMenuInput;
import com.pdcgame.Printers.GameMenuPrinter;

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
