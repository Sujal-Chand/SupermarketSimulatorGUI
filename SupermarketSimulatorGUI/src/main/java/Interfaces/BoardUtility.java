package main.java.Interfaces;

import main.java.Enums.BoardCell;

/**
 * @author prisha, sujal
 */
public interface BoardUtility {
    BoardCell getCell(int x, int y); // interface to get a cell
    void setCell(int x, int y, BoardCell cell); // interface to set cell
    BoardCell[][] get2DBoard(); // interface to get 2D board
    void set2DBoard(BoardCell[][] board); // interface to set 2D board
    void moveItem(int fromX, int fromY, int toX, int toY); // interface to move item (future implementation)
}
