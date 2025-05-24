package com.pdcgame.Managers;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;

import com.pdcgame.Interfaces.DataProcessor;
import com.pdcgame.Models.BoardSave;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author prisha, sujal
 */
public class BoardSaveManager implements DataProcessor {
    private static final GameState gameInstance = GameState.instance();
    private final SessionFactory sessionFactory;
    
    private static final int HEIGHT = 10, WIDTH = 10;
    
    public BoardSaveManager() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = cfg.buildSessionFactory();
    }
    
    @Override
    public void load() {
        BoardCell[][] loadedBoard = gameInstance.getBoardManager().buildBlankBoard();
        
        try (Session session = sessionFactory.openSession()) {
            List<BoardSave> boardSaveList = session
                    .createQuery("from BoardSave", BoardSave.class)
                    .list();
            
            for(BoardSave boardItem : boardSaveList) {
                int[] coordinate = IOHandler.instance().stringToCoordinates(boardItem.getCoordinateID());
                loadedBoard[coordinate[1]][coordinate[0]] = boardItem.getBoardCell();
            }
            gameInstance.getBoardManager().set2DBoard(loadedBoard);
            
        } catch (Exception e) {
            System.err.println("Failed to load board save: " + e.getMessage());
        }
    }
    
    @Override
    public void save() {
        BoardCell[][] gameBoard = gameInstance.getBoardManager().get2DBoard();
        Transaction tx = null;

        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            flushBoard(session);

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    BoardCell cell = gameBoard[y][x];

                    if (cell != BoardCell.EMPTY) {
                        int[] coords = {x, y};
                        BoardSave boardItem = new BoardSave(IOHandler.instance().coordinatesToString(coords), cell);
                        session.persist(boardItem); // more efficient than merge after flush
                    }
                }
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Failed to save board" + e.getMessage());
        }
    }
    

    @Override
    public boolean fileExists() {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("select count(i) from BoardSave i", Long.class)
                                .uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            System.err.println("Failed to check if board save exists: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // function overload
    public void flushBoard(Session session) {
        // bulk delete all rows from BoardSave
        session.createQuery("delete from BoardSave").executeUpdate(); 
    }
    
    public void flushBoard() {
        Transaction tx = null;
    
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            
            flushBoard(session);
        
            tx.commit();
        } catch (Exception e) {
        if (tx != null && tx.getStatus().canRollback()) {
        try {
            tx.rollback();
        } catch (Exception rollbackEx) {
            System.err.println("Rollback failed: " + rollbackEx.getMessage());
            rollbackEx.printStackTrace();
        }
    }
    System.err.println("Failed to save board: " + e.getMessage());
    e.printStackTrace();  // <-- important to see full error
}
    }
}
