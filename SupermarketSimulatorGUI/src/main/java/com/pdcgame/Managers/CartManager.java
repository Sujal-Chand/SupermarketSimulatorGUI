package com.pdcgame.Managers;

import com.pdcgame.Enums.InternalCases;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import com.pdcgame.ProductStorage;
import com.pdcgame.ProductTypes.PurchasableProduct;

import java.util.Map;

/**
 * @author prisha, sujal
 */
public class CartManager extends ProductStorage {
    private static final CartManager instance = new CartManager();
    private final GameState gameInstance = GameState.instance();
    public static CartManager instance(){
        return instance;
    }

    // add product with quantity
    @Override
    public void addProduct(String productName, int quantity) {
        if(quantity <= 0) return;
        super.addProduct(productName, quantity);
    }

    // remove product with quantity
    @Override
    public void removeProduct(String productName, int quantity) {
        if(quantity <= 0) return;
        super.removeProduct(productName, quantity);
    }

    public void removeAllProducts() {
        super.clear(); // removes all the products
    }

    // returns the quantity of an item in the cart
    public int getItemQuantity(String productName) {
        if(storedProducts.containsKey(productName)) return storedProducts.get(productName);
        return 0;
    }

    public double cartTotalValue() {
        double total = 0;

        // gets the total cart cost for each item with how many quantity in the cart
        for(Map.Entry<String, Integer> entry: storedProducts.entrySet()) {
            PurchasableProduct product = gameInstance.getProductManager().getProduct(entry.getKey());
            total += (product.getBulkPrice() * entry.getValue());
        }
        return total;
    }

    public void checkoutCart() {
        for(Map.Entry<String, Integer> entry: storedProducts.entrySet()) {
            gameInstance.getInventoryManager().addToInventory(entry.getKey(), quantityAddForInventory(entry.getKey()));
        }
        BankManager.subtractBalance(cartTotalValue());
        GamePersistence.saveGame();
        removeAllProducts();
    }

    public boolean cartEmpty() {
        return storedProducts.isEmpty();
    }

    // the amount of quantity to add to the inventory for a product
    public int quantityAddForInventory(String productName) {
        int boxQuantity = gameInstance.getProductManager().getProduct(productName).getQuantityInBox();
        return boxQuantity *  getQuantity(productName);
    }

    // returns an internal case reflecting if cart can be 'checked out' or well all items in the cart can be purchased
    public InternalCases canCheckoutCart() {
        if (!ActionManager.enoughActions()) return InternalCases.NO_ACTIONS;
        double totalCost = cartTotalValue();
        if (!BankManager.possiblePurchase(totalCost)) return InternalCases.NO_FUNDS;
        checkoutCart();
        return InternalCases.SUCCESS;
    }
}
