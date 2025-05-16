package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.ProductStorage;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

import java.util.*;

/**
 * @author prisha, sujal
 */
// manages inventory for purchasable products in the game
public class InventoryManager extends ProductStorage {

    // initializes inventory with all purchasable products, starting at 0
    public void initializeInventory() {
        GameState gameInstance = GameState.instance();
        Collection<PurchasableProduct> products = gameInstance.getProductManager().getPurchasableProducts();
        storedProducts.clear();
        for (PurchasableProduct product : products) {
            addToInventory(product.getName());
        }
    }

    // adds a specific quantity of product to inventory
    public void addToInventory(String productName, int quantity) {
        if (quantity >= 0) {
            int currentQuantity = storedProducts.getOrDefault(productName, 0);
            storedProducts.put(productName, currentQuantity + quantity);
        }
    }

    // adds a product to inventory with default quantity 0
    public void addToInventory(String productName) {
        addToInventory(productName, 0);
    }

    // gets quantity of a product in inventory
    public int getQuantity(String productName) {
        return storedProducts.get(productName);
    }

    // sets the quantity of a product in inventory
    public void setQuantity(String productName, int quantity) {
        storedProducts.put(productName, quantity);
    }

    @Override
    // removes a specific quantity from inventory if possible
    public void removeProduct(String productName, int quantity) {
        if (storedProducts.containsKey(productName)) {
            int newQuantity = storedProducts.get(productName) - quantity;
            if(newQuantity >= 0) {
                storedProducts.put(productName, newQuantity);
            }
        }
    }

    // returns a random product name from the inventory
    public String getRandomProduct() {
        List<Map.Entry<String, Integer>> inventoryList = new ArrayList<>(storedProducts.entrySet());
        Collections.shuffle(inventoryList);
        Map.Entry<String, Integer> firstEntryShuffled = inventoryList.getFirst();
        return firstEntryShuffled.getKey();
    }
}
