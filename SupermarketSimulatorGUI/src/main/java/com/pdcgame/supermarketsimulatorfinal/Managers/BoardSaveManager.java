package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Interfaces.CSVReader;
import com.pdcgame.supermarketsimulatorfinal.Interfaces.FileProcessor;
import com.pdcgame.supermarketsimulatorfinal.Loaders.BoardCellLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author prisha, sujal
 */
public class BoardSaveManager implements FileProcessor {
    private static final String SAVE_PATH = "./save_folder/board_save.csv";
    private static final GameState gameInstance = GameState.instance();
    private final BoardCellLoader boardCellLoader = new BoardCellLoader();

    @Override
    // saves the board in one line CSV
    public void load() {
        try(BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH))) {
            String boardCSV = reader.readLine();
            gameInstance.getBoardManager().set2DBoard(boardCellLoader.fromCSV(boardCSV));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        try(PrintWriter pw = new PrintWriter(SAVE_PATH)) {
            pw.println(boardCellLoader.toCSV(gameInstance.getBoardManager().get2DBoard()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean fileExists() {
        Path save_path = Paths.get(SAVE_PATH);
        return Files.exists(save_path);
    }
}
