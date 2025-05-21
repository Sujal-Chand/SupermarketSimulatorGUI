package main.java.Loaders;

import main.java.Enums.BoardCell;
import main.java.Interfaces.CSVReader;

/**
 * @author prisha, sujal
 */
public class BoardCellLoader implements CSVReader<BoardCell[][]> {

    @Override
    public String toCSV(BoardCell[][] board) {
        // builds csv string from 2D board array
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++ ) {
            for (int j = 0; j < board[i].length; j++) {
                // append cell name
                sb.append(board[i][j].name());
                // add comma between columns
                if(j < board[i].length - 1) sb.append(",");
            }
            // add semicolon between rows
            if(i < board.length - 1) sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public BoardCell[][] fromCSV(String csvData) {
        // splits input string into rows
        String[] rowStrings = csvData.split(";");

        // get row and column count
        int rows = rowStrings.length;
        int cols = rowStrings[0].split(",").length;

        // initialize new 2d board array
        BoardCell[][] board = new BoardCell[rows][cols];
        for(int i = 0; i < rows; i++) {
            // split current row into cell values
            String[] cellValues = rowStrings[i].split(",");
            for(int j = 0; j < cols; j++) {
                // convert string to board cell enum
                board[i][j] = BoardCell.valueOf(cellValues[j]);
            }
        }
        return board;
    }
}
