package com.pdcgame;

import java.util.HashMap;
import java.util.Map;

/**
 * @author prisha, sujal
 */
public class ProductStorage {
    // where String is the product name (key) and Integer is the quantity of that product.
    public Map<String, Integer> storedProducts = new HashMap<>();

    // add product or increase quantity
    public void addProduct(String productName, int quantity) {
        // the getOrDefault here makes sure to add quantity starting from 0 if the product isn't in the map
        storedProducts.put(productName, storedProducts.getOrDefault(productName, 0) + quantity);
    }

    public void removeProduct(String productName, int quantity) {
        if (storedProducts.containsKey(productName)) {
            int newQuantity = storedProducts.get(productName) - quantity; // the hypothetical new quantity
            if (newQuantity == 0) {
                // remove the product if the new quantity is 0
                // also don't allow more products to be removed than available
                storedProducts.remove(productName);
            } else {
                // if the new quantity is more than 0 then replace with new quantity
                if(newQuantity > 0 ) storedProducts.put(productName, newQuantity);
            }
        }
    }

    public int getQuantity(String productName) {
        // returns quantity or 0 if none held
        return storedProducts.getOrDefault(productName, 0);
    }

    // returns the hashmap of stored products
    public Map<String, Integer> getStoredProducts() {
        return storedProducts;
    }

    // clears the hashmap
    public void clear() {
        storedProducts.clear();
    }

}
