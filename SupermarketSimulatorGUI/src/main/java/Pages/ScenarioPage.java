package main.java.Pages;

import main.java.Enums.BoardCell;
import main.java.Enums.PageName;
import main.java.Abstracts.Page;
import main.java.GameState;
import main.java.Managers.ScenarioManager;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class ScenarioPage extends Page {

    // constructor
    public ScenarioPage() {
        super(PageName.SCENARIO_PAGE);
    }
    ScenarioManager scenarioManager = new ScenarioManager();

    // display
    @Override
    public PageName display(Scanner scanner) throws InterruptedException {

        //checks if there is cashier before opening store
        if(GameState.instance().getBoardManager().getEquipmentCount(BoardCell.CASHIER) < 1) {
            pageHeader("STORE CANNOT OPEN");
            System.out.println("You need to purchase a cashier before starting!\nGo to equipment page for more info...");
            Thread.sleep(1000);
            return PageName.GAME_MENU_PAGE;
        }

        //opens store
        pageHeader("STORE OPENED");
        scenarioManager.runDay();
        return PageName.GAME_MENU_PAGE;
    }
}
