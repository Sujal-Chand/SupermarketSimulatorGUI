package com.pdcgame;

import com.pdcgame.Enums.ActionState;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Managers.BankManager;
import com.pdcgame.Managers.BoardSaveManager;
import com.pdcgame.Managers.GameSaveManager;
import com.pdcgame.Managers.InventorySaveManager;
import com.pdcgame.Managers.RatingManager;
import com.pdcgame.Managers.StorageSaveManager;

/**
 * @author prisha, sujal
 */
public class GamePersistence {
    private static final GameSaveManager gameSaveManager = new GameSaveManager();
    private static final StorageSaveManager storageSaveManager = new StorageSaveManager();
    private static final InventorySaveManager inventorySaveManager = new InventorySaveManager();
    private static final BoardSaveManager boardSaveManager = new BoardSaveManager();

    // file paths used for saving player data
    public static boolean saveExists(){
        // returns boolean stating if all four save files exist in the save_folder
        return gameSaveManager.fileExists() &&
                storageSaveManager.fileExists() &&
                inventorySaveManager.fileExists() &&
                boardSaveManager.fileExists();
    }

    public static void newSave() {
        BankManager.setInitialBalance(); // set initial bank balance for a new save
        RatingManager.setInitialRating(); // set initial store rating
        GameState.instance().getBoardManager().initialiseBoard(); // initialize the board
        GameState.instance().getInventoryManager().initializeInventory(); // initialize the inventory to contain all purchasable products
        GameState.instance().setActionState(ActionState.LOAD_SAVE); // set the action state to load save from every point forward
        ActionManager.setInitialActions(); // set initial actions
        GameState.instance().setDay(0);
        saveGame(); // save the initial game
    }
    // save all game files
    public static void saveGame() {
        if(GameState.instance().getGameState() == ActionState.NEW_GAME) newSave();
        gameSaveManager.save();
        boardSaveManager.save();
        inventorySaveManager.save();
        storageSaveManager.save();
    }

    // load all game files
    public static void loadGame() {
        GameState.instance().setActionState(ActionState.LOAD_SAVE);
        gameSaveManager.load();
        boardSaveManager.load();
        inventorySaveManager.load();
        storageSaveManager.load();
    }
    
    public static void shutdownSession() {
        gameSaveManager.close();
        inventorySaveManager.close();
    }
}
