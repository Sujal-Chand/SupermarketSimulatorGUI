package com.pdcgame.Managers;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.EquipmentStorage;
import com.pdcgame.GameState;

import java.util.HashMap;
import java.util.Map;
import com.pdcgame.Interfaces.DataProcessor;
import com.pdcgame.Models.BoardSave;
import com.pdcgame.Models.StorageItemSave;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author prisha, sujal
 */
public class StorageSaveManager implements DataProcessor {
    private static final GameState gameInstance = GameState.instance();
    private final SessionFactory sessionFactory;
    
    public StorageSaveManager() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = cfg.buildSessionFactory();
       
    }
    

    @Override
    public void load() {
        try (Session session = sessionFactory.openSession()) {
            List<BoardSave> allBoardLocations = session
                    .createQuery("from BoardSave", BoardSave.class)
                    .list();
            
            for(BoardSave location : allBoardLocations) {
                List<StorageItemSave> storageItems = session
                        .createQuery("from StorageItemSave where coordinateID = :coordId", StorageItemSave.class)
                        .setParameter("coordId", location.getCoordinateID())
                        .list();
                
                Map<String, Integer> loadedProducts = new HashMap<>();
                for(StorageItemSave storageItem : storageItems) {
                    loadedProducts.put(storageItem.getProductName(), storageItem.getQuantity());
                }
                
                BoardCell cell = location.getBoardCell();
                gameInstance.getFloorStorageManager().addLocation(location.getCoordinateID(), cell, true);
                
                EquipmentStorage storage = gameInstance.getFloorStorageManager().getLocationWithStorage().get(location.getCoordinateID());
                storage.setStoredProducts(loadedProducts);
            }
        }
    }
    
    
    @Override
    public void save() {
        Map<String, EquipmentStorage> locationWithStorage = gameInstance.getFloorStorageManager().getLocationWithStorage();
        Transaction tx = null;
        
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            
            flushStorageItem(session);
            
            for(String coordinateID : locationWithStorage.keySet()) {
                
                EquipmentStorage equipment = locationWithStorage.get(coordinateID);
                Map<String, Integer> storedProducts = equipment.getStoredProducts();
                

                if(storedProducts != null || storedProducts.isEmpty()) {
                    for(Map.Entry<String, Integer> product : storedProducts.entrySet()) {
                        StorageItemSave storageItem = new StorageItemSave(coordinateID, product.getKey(), product.getValue());
                        session.persist(storageItem);
                    }
                }
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("Failed to save storage: " + e.getMessage());
        }
    }
    
    @Override
    public boolean fileExists() {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("select count(i) from StorageItemSave i", Long.class)
                    .uniqueResult();
            
            if(count == null || count == 0) {
                Long itemCount = session.createQuery("select count(i) from BoardSave i", Long.class)
                    .uniqueResult();
                return itemCount != null && itemCount > 0;
            }
            
            return true; 
        } catch (Exception e) {
            System.err.println("Failed to check if storage save exists: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public void flushStorageItem(Session session) {
        // bulk delete all rows from StorageItemSave
        session.createQuery("delete from StorageItemSave").executeUpdate();
    }
    public void flushStorageItem() {
        Transaction tx = null;
        
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            StorageSaveManager.this.flushStorageItem(session);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) tx.rollback();
            System.err.println("Failed to flush board");
            e.printStackTrace();
        }
    }
}
