package com.pdcgame.Managers;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.FileProcessor;
import com.pdcgame.Models.GameSave;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gamePU");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void load() {
        GameSave save = em.find(GameSave.class, 1);
        if(save != null) {
            gameInstance.setDifficulty(save.getDifficulty());
            gameInstance.setBalance(save.getBalance());
            gameInstance.setTotalActions(save.getTotalActions());
            gameInstance.setActions(save.getActions());
            gameInstance.setRating(save.getRating());
            gameInstance.setDay(save.getDay());
            gameInstance.setGameSeed(save.getGameSeed());
        }
    }

    @Override
    public void save() {
        GameSave save = new GameSave();
        save.setId(1);
        save.setDifficulty(gameInstance.getDifficulty());
        save.setBalance(gameInstance.getBalance());
        save.setTotalActions(gameInstance.getTotalActions());
        save.setActions(gameInstance.getActions());
        save.setRating(gameInstance.getRating());
        save.setDay(gameInstance.getDay());
        save.setGameSeed(gameInstance.getGameSeed());
    }

    @Override
    public boolean fileExists() {
        return em.find(GameSave.class, 1) != null;
    }
}
