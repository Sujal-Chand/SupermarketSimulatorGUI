package com.pdcgame.Managers;

import com.pdcgame.GameState;
import com.pdcgame.ProductTypes.PurchasableProduct;

import java.util.Collection;
import com.pdcgame.Interfaces.DataProcessor;
import com.pdcgame.Models.InventorySave;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author prisha, sujal
 */
public class InventorySaveManager implements DataProcessor {
    private final GameState gameInstance = GameState.instance();
    private final SessionFactory sessionFactory;
    
    public InventorySaveManager() {
        // load configuration from hibernate.cfg.xml 
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = cfg.buildSessionFactory();
    }
    
    @Override
    public void load() {
        try (Session session = sessionFactory.openSession()) {
            // begin read transaction
            Transaction tx = session.beginTransaction();
            List<InventorySave> inventorySave = session.createQuery("from InventorySave", InventorySave.class).list();
            
            for (InventorySave invenItem : inventorySave) {
                String name = invenItem.getName();
                int quantity = invenItem.getQuantity();
                double sellPrice = invenItem.getSellPrice();
                gameInstance.getInventoryManager().setQuantity(name, quantity);
                gameInstance.getProductManager().setSellPrice(name, sellPrice);
            }
        } catch (Exception e) {
            System.err.println("Failed to load iventory save: " + e.getMessage());
        }
    }
    @Override
    public void save() {
        // get all the products in the store
        Collection<PurchasableProduct> allProducts = gameInstance.getProductManager().getPurchasableProducts();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            for(PurchasableProduct product : allProducts) {
                
                String productName = product.getName(); // get current product name
                int quantity = gameInstance.getInventoryManager().getQuantity(productName);
                double sellPrice = product.getSellPrice();
                
                // either load existing inventory product or create newq one
                InventorySave invenItem = session.get(InventorySave.class, productName);
                if (invenItem == null) {
                    invenItem = new InventorySave(productName, quantity, sellPrice);
                } else {
                    invenItem.setQuantity(quantity);
                    invenItem.setSellPrice(sellPrice);
                }
                session.merge(invenItem);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Failed to save inventory: " + e.getMessage());
        }
    }

    @Override
    public boolean fileExists() {
        Collection<PurchasableProduct> allProducts = gameInstance.getProductManager().getPurchasableProducts();
        long productCount = allProducts.size();
        
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            // count rows in InventorySave
            Long dbCount = session.createQuery("select count(i) from InventorySave i", Long.class)
                    .uniqueResult();
            tx.commit();
            
            return (dbCount != null && dbCount == productCount);
        } catch (Exception e) {
            System.err.println("Error checking if inventory save exists: " + e.getMessage());
            return false;
        }
    }
    
    public void close() {
        if(sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
