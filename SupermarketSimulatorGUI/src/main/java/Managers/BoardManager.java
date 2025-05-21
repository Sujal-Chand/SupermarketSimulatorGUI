package main.java.Managers;

import main.java.Enums.BoardCell;
import main.java.Interfaces.BoardUtility;

/**
 * @author prisha, sujal
 */
public class BoardManager implements BoardUtility {
    private static final int WIDTH = 10, HEIGHT = 10; // game board dimensions
    private final BoardCell[][] board; // board object

    // constructor for making a 2D board
    public BoardManager() {
        this.board = new BoardCell[HEIGHT][WIDTH];
        initialiseBoard();
    }

    // initializes the board with empty cells
    public void initialiseBoard() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                board[y][x] = BoardCell.EMPTY;
            }
        }
    }

    @Override
    public BoardCell getCell(int x, int y) {
        return board[y][x]; // returns cell at coordinate
    }

    @Override
    public void setCell(int x, int y, BoardCell cell) {
        board[y][x] = cell; // sets cell at coordinate
    }

    @Override
    public BoardCell[][] get2DBoard() {
        return board; // returns the whole board object
    }

    // set the current board from an input
    @Override
    public void set2DBoard(BoardCell[][] board) {
        // exit if the specified board isn't square
        if(board.length != WIDTH || board[0].length != HEIGHT) {
            throw new IllegalArgumentException("The board must have the same length");
        }

        for(int y = 0; y < HEIGHT; y++) {
            for(int x = 0; x < WIDTH; x++) {
                setCell(x, y, board[y][x]);
            }
        }
    }

    // helper function to move one item to another
    @Override
    public void moveItem(int fromX, int fromY, int toX, int toY) {
        // if statement internally stops from over-writing an existing cell
        if(getCell(toX, toY) == BoardCell.EMPTY) {
            BoardCell cell = getCell(fromX, fromY);
            setCell(toX, toY, cell);
            setCell(fromX, fromY, BoardCell.EMPTY);
        }
    }

    // returns the count for particular equipment in the store for example getEquipmentCount(BoardCell.CASHIER)
    public int getEquipmentCount(BoardCell equipment) {
        int count = 0;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (getCell(x, y) == equipment) count++;
            }
        }
        return count;
    }
}
