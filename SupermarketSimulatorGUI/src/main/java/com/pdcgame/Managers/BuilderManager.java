package com.pdcgame.Managers;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.Enums.ScenarioType;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;

/**
 * @author prisha, sujal
 */
public class BuilderManager {
    private static final GameState gameInstance = GameState.instance();

    public static InternalCases purchaseItem(BoardCell item, int x, int y) {
        // max cashier amount case
        if((item == BoardCell.CASHIER) && (gameInstance.getBoardManager().getEquipmentCount(BoardCell.CASHIER) == 3)) {
            return InternalCases.MAX_AMOUNT;
        }
        if(!ActionManager.enoughActions()) return InternalCases.NO_ACTIONS; // no actions case
        if(BankManager.possiblePurchase(item.getItemCost())) {
            if(!(isCellEmpty(x, y))) return InternalCases.SPACE_TAKEN;
            // subtract balance from bank
            BankManager.subtractBalance(item.getItemCost());
            // set the cell on the board to the item
            gameInstance.getBoardManager().setCell(x, y, item);

            int[] coordArray = {x, y}; // turn x,y coordinates to an array
            // add the board cell to the floor storage manager for item storage
            gameInstance.getFloorStorageManager().addLocation(IOHandler.instance().coordinatesToString(coordArray), item, true);

            // reduce weight of cashier due to purchase
            if(item == BoardCell.CASHIER) {
                ScenarioType.LONG_LINE.setWeight(ScenarioType.LONG_LINE.getWeight() - 2);
            }

            GamePersistence.saveGame();
            return InternalCases.SUCCESS;
        }
        return InternalCases.NO_FUNDS; // fall through no funds case
    }

    public static InternalCases trySellItem(BoardCell item, String stringCoordinates) {
        if(item == BoardCell.CASHIER && (gameInstance.getBoardManager().getEquipmentCount(BoardCell.CASHIER) == 1)) {
            // minimum amount of this product has to remain
            return InternalCases.MIN_AMOUNT;
        }
        if(!ActionManager.enoughActions()) return InternalCases.NO_ACTIONS; // not enough actions
        if(item == BoardCell.EMPTY) {
            // if the boardCell selected is already empty
            return InternalCases.EMPTY;
        }
        if(gameInstance.getFloorStorageManager().getAvailableSpacesAt(stringCoordinates) != item.getCapacity()) {
            // if the storage location isn't emptied
            return InternalCases.SPACE_TAKEN;
        }

        return InternalCases.SUCCESS;
    }

    public static void sellItem(BoardCell item, int[] coordinates, String stringCoordinates) {
        // increase weight of cashier due to sell
        if(item == BoardCell.CASHIER) {
            ScenarioType.LONG_LINE.setWeight(ScenarioType.LONG_LINE.getWeight() + 2);
        }

        gameInstance.getBoardManager().setCell(coordinates[0], coordinates[1], BoardCell.EMPTY);
        gameInstance.getFloorStorageManager().removeLocation(stringCoordinates);
        BankManager.addBalance(item.getItemCost() * 0.9);
        ActionManager.futureConsume();
        GamePersistence.saveGame();
    }

    public static boolean isCellEmpty(int x, int y) {
        return GameState.instance().getBoardManager().getCell(x, y) == BoardCell.EMPTY;
    }
}
