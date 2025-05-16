package com.pdcgame.supermarketsimulatorfinal.DisplayObjects;

import com.pdcgame.supermarketsimulatorfinal.Enums.BoardCell;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Interfaces.GameObject;

/**
 * @author prisha, sujal
 */
public class GameBoard implements GameObject {
    private final static GameBoard gameBoard = new GameBoard(); // singleton instance
    private final static GameState gameInstance = GameState.instance();
    private final int DASH_LENGTH = 49;

    public static GameBoard instance() {
        return gameBoard;
    }

    // displays the board
    @Override
    public void display() {
        yAxis();
        showBoardCells(gameInstance.getBoardManager().get2DBoard());
        xAxis();
    }

    // prints the cells
    private void showBoardCells(BoardCell[][] board){
        for(int r = 0; r < 10; r++) {
            System.out.print("\n"+(9-r)+" |");
            for(int c = 0; c < 10; c++) {
                System.out.print(board[9-r][c].getIcon());
            }
        }
    }

    // prints the X axis numbers and lines
    private void xAxis() {
        System.out.print("\n "); // blanking
        // blank space between each number in x-axis
        for(int i = 0; i < 10; i++) {
            System.out.print("    "+i);
        }
        System.out.print("\n  +");
        for(int i = 0; i < DASH_LENGTH; i++) {
            System.out.print("-");
        }
        System.out.print("+ X");
    }

    // prints the Y axis numbers and lines
    private void yAxis() {
        String LAYOUT_NAME = "STORE LAYOUT";
        int labelLength = LAYOUT_NAME.length();
        int padding = DASH_LENGTH - labelLength;
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        System.out.print("\nY +");
        for (int i = 0; i < leftPadding; i++) {
            System.out.print("-");
        }
        System.out.print(LAYOUT_NAME);
        for (int i = 0; i < rightPadding; i++) {
            System.out.print("-");
        }
        System.out.print("+");
    }
}
