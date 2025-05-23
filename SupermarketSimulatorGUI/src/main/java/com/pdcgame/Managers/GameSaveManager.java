package com.pdcgame.Managers;

import com.pdcgame.GameState;
import com.pdcgame.Interfaces.FileProcessor;
import com.pdcgame.Models.GameSave;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Handles saving and loading of game state using native Hibernate API.
 * @author prisha, sujal
 */
public class GameSaveManager implements FileProcessor {
    private static final int SAVE_ID = 1;
    private final GameState gameInstance = GameState.instance();
    private final SessionFactory sessionFactory;

    public GameSaveManager() {
        // Loads configuration from hibernate.cfg.xml on the classpath
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        // Register annotated class if not declared in cfg file
        cfg.addAnnotatedClass(GameSave.class);
        this.sessionFactory = cfg.buildSessionFactory();
    }

    @Override
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
            session.saveOrUpdate(save);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Failed to save game: " + e.getMessage());
        }
    }

    @Override
    public boolean fileExists() {
        try (Session session = sessionFactory.openSession()) {
            GameSave save = session.get(GameSave.class, SAVE_ID);
            return save != null;
        } catch (Exception e) {
            System.out.println("Error checking if game save exists: " + e.getMessage());
            return false;
        } 
    }
    
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
