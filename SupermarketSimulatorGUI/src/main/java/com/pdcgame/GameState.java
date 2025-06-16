package com.pdcgame;

import com.pdcgame.Enums.ActionState;
import com.pdcgame.Enums.Difficulty;

import java.util.Random;
import com.pdcgame.Managers.BoardManager;
import com.pdcgame.Managers.EndOfDayManager;
import com.pdcgame.Managers.FloorStorageManager;
import com.pdcgame.Managers.InventoryManager;
import com.pdcgame.Managers.ProductManager;

/**
 * @author prisha, sujal
 */
// this class contains global variables that may be required by other functions in the project
public class GameState {
    // singleton instance for GameState - to get one instance everywhere
    private static final GameState gameInstance = new GameState();

    private final BoardManager boardManager; // the games board manager
    private final ProductManager productManager; // the games product manager (all purchasable products from the products.csv)
    private final InventoryManager inventoryManager; // the games product inventory (products that are not currently on the floor but purchased)
    private final FloorStorageManager floorStorageManager; // the games products that are currently on the floor
    private ActionState gameState = ActionState.NEW_GAME; // start program assuming new game
    private Difficulty difficulty; // game difficulty
    private final EndOfDayManager endOfDayManager = new EndOfDayManager();

    private double balance, rating; // balance and rating. rating stored as a double but displayed as rounded value
    private int totalActions, actionsRemaining; // total actions, actions in current round, and current day
    public long day = 0; // used for economy rate, and popular products
    public long gameSeed = new Random().nextLong(1000000000); // game economy seed


    // constructor
    public GameState() {
        this.boardManager = new BoardManager();
        this.productManager = new ProductManager();
        this.inventoryManager = new InventoryManager();
        this.floorStorageManager = new FloorStorageManager();
    }

    public boolean isGameOver() {
        if(balance <= 0) return true;
        if(rating > 4.5) return true;
        if(rating < 1) return true;
        return false;
    }
    // return the singleton instance
    public static GameState instance() {
        return gameInstance;
    }

    public EndOfDayManager getEndOfDayManager (){
        return endOfDayManager;
    }

    // return the current action state in this instance
    public ActionState getGameState() {
        return gameState;
    }

    // get difficulty
    public Difficulty getDifficulty() {
        return difficulty;
    }
    // set difficulty
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    // get balance
    public double getBalance() {
        return balance;
    }
    // set balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // get rating
    public double getRating() {
        return rating;
    }
    // set rating
    public void setRating(double rating) {
        this.rating = rating;
    }

    // get actions remaining
    public int getActions() {
        return actionsRemaining;
    }
    // set actions remaining
    public void setActions(int actions) {
        this.actionsRemaining = actions;
    }

    // get economy rate
    public long getDay() {
        return day;
    }
    // set economy rate
    public void setDay(long day) {
        this.day = day;
    }

    // get game seed
    public long getGameSeed() {
        return gameSeed;
    }
    // set game seed
    public void setGameSeed(long gameSeed) {
        this.gameSeed = gameSeed;
    }

    // get total actions
    public int getTotalActions() {
        return totalActions;
    }
    // set total actions
    public void setTotalActions(int totalActions) {
        this.totalActions = totalActions;
    }

    // set action state
    public void setActionState(ActionState actionState) {
        this.gameState = actionState;
    }

    // get board manager
    public BoardManager getBoardManager() {
        return boardManager;
    }
    // get product manager
    public ProductManager getProductManager() {
        return productManager;
    }
    // get inventory manager
    public InventoryManager getInventoryManager() {
        return inventoryManager;}
    // get floor storage manager
    public FloorStorageManager getFloorStorageManager() {
        return floorStorageManager;}
}
