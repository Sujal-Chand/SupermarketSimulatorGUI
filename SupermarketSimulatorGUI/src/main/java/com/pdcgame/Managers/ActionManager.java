package com.pdcgame.Managers;

import com.pdcgame.Enums.ActionSource;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;

/**
 * @author prisha, sujal
 */
public class ActionManager {
    private static final double ACTION_COST = 1000;
    private static final int STARTING = 3;
    private static boolean actionBuffer = false;
    private static ActionSource lastActionSource = null;

    public static void setInitialActions() {
        GameState gameInstance = GameState.instance();
        gameInstance.setTotalActions(STARTING);
        gameInstance.setActions(STARTING);
    }

    // gets the cost of one action
    public static double getCost() {
        return ACTION_COST;
    }

    // sets action buffer to true when a task that consumes a round action is preformed
    public static void futureConsume() {
        actionBuffer = true;
    }

    public static void futureConsume(ActionSource source) {
        GameState gameInstance = GameState.instance();

        if (lastActionSource == null) {
            // First call: just set lastActionSource, no action reduction
            lastActionSource = source;
        } else if (!lastActionSource.equals(source)) {
            // Different source: reduce actions by 1 (with grace day check)
            if (gameInstance.getDay() != 0) {
                int newActions = Math.max(0, gameInstance.getActions() - 1);
                gameInstance.setActions(newActions);
            }
            lastActionSource = source;
            GamePersistence.saveGame();
        }
        // If same source, do nothing
    }

    // when pages call this function an action is reduced if action buffer is true
    public static void tryActionUpdate() {
        GameState gameInstance = GameState.instance();
        int newAction = Math.max(0, gameInstance.getActions() - 1);
        if(actionBuffer) {
            if(gameInstance.getDay() != 0) gameInstance.setActions(newAction); // first day grace period - prevents soft locking the game
            actionBuffer = false;
        }
        // save the game after updating actions
        GamePersistence.saveGame();
    }

    // Resets lastActionSource to allow next action to reduce again
    public static void resetLastActionSource() {
        lastActionSource = null;
    }

    // reset actions for a new day
    public static void resetActions() {
        GameState gameInstance = GameState.instance();
        gameInstance.setActions(gameInstance.getTotalActions());
        resetLastActionSource();
    }

    // returns true if there are enough actions still left in the round
    public static boolean enoughActions() {
        GameState gameInstance = GameState.instance();
        return gameInstance.getActions() > 0;
    }

    // purchases an action
    public static void purchaseAction() {
        GameState gameInstance = GameState.instance();
        if(BankManager.possiblePurchase(ACTION_COST)){
            BankManager.subtractBalance(ACTION_COST);
            int currentActions = gameInstance.getTotalActions() + 1;
            gameInstance.setTotalActions(currentActions);
            gameInstance.setActions(gameInstance.getActions() + 1);
            GamePersistence.saveGame();
        }
    }

}
