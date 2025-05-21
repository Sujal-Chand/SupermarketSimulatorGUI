package main.java.Managers;

import main.java.Enums.Difficulty;
import main.java.GameState;
import main.java.Interfaces.FileProcessor;

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
public class GameSaveManager implements FileProcessor {
    private static final String SAVE_PATH = "./save_folder/game_save.txt";
    private final GameState gameInstance = GameState.instance();

    /**
     * Example of file read and saved by GameSaveManager (Left of colon = value, right of colon = meaning)
     * Easy : Difficulty
     * 5000.0 : Balance
     * 5 : Total actions
     * 3 : Actions remaining before starting round
     * 2.58... : Double format of store rating; Rounded to floor(.5) increments when displayed
     * 0 : Long format of the day
     * 3848411 : Long format of game seed
     */
    @Override
    public void load() {
        try(BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH))) {
            // loads all values and passes them as correct datatypes
            gameInstance.setDifficulty(Difficulty.valueOf(reader.readLine()));
            gameInstance.setBalance(Double.parseDouble(reader.readLine()));
            gameInstance.setTotalActions(Integer.parseInt(reader.readLine()));
            gameInstance.setActions(Integer.parseInt(reader.readLine()));
            gameInstance.setRating(Double.parseDouble(reader.readLine()));
            gameInstance.setDay(Long.parseLong(reader.readLine()));
            gameInstance.setGameSeed(Long.parseLong(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(SAVE_PATH)) {
            // reads all values and passes them as strings to save to the file
            pw.println(gameInstance.getDifficulty());
            pw.println(gameInstance.getBalance());
            pw.println(gameInstance.getTotalActions());
            pw.println(gameInstance.getActions());
            pw.println(gameInstance.getRating());
            pw.println(gameInstance.getDay());
            pw.println(gameInstance.getGameSeed());
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
