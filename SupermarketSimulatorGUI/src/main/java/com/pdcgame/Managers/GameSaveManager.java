package com.pdcgame.Managers;

import com.pdcgame.GameState;
import com.pdcgame.Models.GameSave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.pdcgame.Interfaces.DataProcessor;

/**
 * Handles saving and loading of game state using native Hibernate API.
 * @author prisha, sujal
 */
public class GameSaveManager implements DataProcessor {
    private static final int SAVE_ID = 1;
    
    private final GameState gameInstance = GameState.instance();
    private final SessionFactory sessionFactory;

    public GameSaveManager() {
        // load configuration from hibernate.cfg.xml
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = cfg.buildSessionFactory();
    }

    @Override
    // load from database 
    public void load() {
        try (Session session = sessionFactory.openSession()) {
            GameSave save = session.get(GameSave.class, SAVE_ID);
            if (save != null) {
                gameInstance.setDifficulty(save.getDifficulty());
                gameInstance.setBalance(save.getBalance());
                gameInstance.setTotalActions(save.getTotalActions());
                gameInstance.setActions(save.getActions());
                gameInstance.setRating(save.getRating());
                gameInstance.setDay(save.getDay());
                gameInstance.setGameSeed(save.getGameSeed());
            }
        } catch (Exception e) {
            System.err.println("Failed to load game save: " + e.getMessage());
        }
    }

    @Override
    // save to database
    public void save() {
        GameSave save = new GameSave();
        save.setId(SAVE_ID);
        save.setDifficulty(gameInstance.getDifficulty());
        save.setBalance(gameInstance.getBalance());
        save.setTotalActions(gameInstance.getTotalActions());
        save.setActions(gameInstance.getActions());
        save.setRating(gameInstance.getRating());
        save.setDay(gameInstance.getDay());
        save.setGameSeed(gameInstance.getGameSeed());

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(save);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    @Override
    // check file exists
    public boolean fileExists() {
        try (Session session = sessionFactory.openSession()) {
            GameSave save = session.get(GameSave.class, SAVE_ID);
            return save != null;
        } catch (Exception e) {
            System.err.println("Error checking if game save exists: " + e.getMessage());
            return false;
        } 
    }
    
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
