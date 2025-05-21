package main.java.Pages;

import main.java.DisplayObjects.GameBoard;
import main.java.Enums.PageName;
import main.java.GamePersistence;
import main.java.Abstracts.Page;
import main.java.GameState;
import main.java.Managers.ActionManager;
import main.java.Managers.ProductManager;
import main.java.PageInputs.GameMenuInput;
import main.java.Printers.GameMenuPrinter;

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
