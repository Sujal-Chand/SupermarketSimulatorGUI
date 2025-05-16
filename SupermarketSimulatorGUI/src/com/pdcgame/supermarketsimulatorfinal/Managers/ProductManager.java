package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.Enums.ProductStorageType;
import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Loaders.ProductLoader;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author prisha, sujal
 */
public class ProductManager {
    private final String PRODUCT_PATH = "./resources/products.csv";
    private final ProductLoader productLoader = new ProductLoader();
    // where String is the product name (key) and PurchasableProduct is information about that product
    private final Map<String, PurchasableProduct> purchasableProducts = new HashMap<>();
    private final Map<String, PurchasableProduct> popularProducts = new HashMap<>();
    // try reading from csv upon initialization
    public ProductManager(){
        loadFromCSV();
    }

    // load each product from the csv into the hashmap of purchasable products
    public void loadFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_PATH))) {
            String line;
            boolean firstLine = true;
            while((line = reader.readLine()) != null) {
                if(firstLine) {
                    firstLine = false;
                    continue;
                }
                PurchasableProduct product = productLoader.fromCSV(line);
                purchasableProducts.put(product.getName(),product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // returns a PurchasableProduct object in the hashmap from the productName string
    public PurchasableProduct getProduct(String productName){
        if(purchasableProducts.containsKey(productName)) {
            return purchasableProducts.get(productName);
        }
        return null;
    }

    // set the sell price for a product in the HashMap
    public void setSellPrice(String productName, double sellPrice){
        if(purchasableProducts.containsKey(productName)){
            purchasableProducts.get(productName).setSellPrice(sellPrice);
        }
    }

    //gets the user sell price for a product
    public double getSellPrice(String productName) {
        return purchasableProducts.get(productName).getSellPrice();
    }

    // get all purchasable products as a collection
    public Collection<PurchasableProduct> getPurchasableProducts() {
        return purchasableProducts.values();
    }

    // get all popular products as a collection
    public Collection<PurchasableProduct> getPopularProducts() {
        if(popularProducts.isEmpty()) updatePopularProducts();
        return popularProducts.values();
    }

    // get a filtered version of all purchasable products based on a product storage type filter
    public Collection<PurchasableProduct> getFilteredPurchasableProducts(ProductStorageType filter){
        Collection<PurchasableProduct> filteredProducts = new ArrayList<>();
        for(PurchasableProduct product : this.getPurchasableProducts()) {
            if(product.getStorageType().equals(filter) || filter.equals(ProductStorageType.ALL)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    public void updatePopularProducts() {
        final GameState gameInstance = GameState.instance();
        long day = gameInstance.getDay();
        long seed = (day - (day % 7)) + gameInstance.getGameSeed(); // generate a seed based of the current week

        popularProducts.clear();
        List<Map.Entry<String, PurchasableProduct>> entries = new ArrayList<>(purchasableProducts.entrySet());
        Collections.shuffle(entries, new Random(seed)); // shuffle the list based of the seed

        // pick three products from all purchasable products to add to popular product
        int count = Math.min(3, entries.size());
        for (int i = 0; i < count; i++) {
            Map.Entry<String, PurchasableProduct> entry = entries.get(i);
            popularProducts.put(entry.getKey(), entry.getValue());
        }
    }

    // gets a random product name in the popular products hashmap
    public String getRandomPopularProduct(){
        if(popularProducts.isEmpty()) updatePopularProducts(); // update popular products if its empty
        List<Map.Entry<String, PurchasableProduct>> list = new ArrayList<>(popularProducts.entrySet()); // turn the hashmap into a list
        Collections.shuffle(list); // shuffle the list randomly
        return list.getFirst().getValue().getName(); // get the first item in the shuffled list
    }
}
