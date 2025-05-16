package com.pdcgame.supermarketsimulatorfinal.ProductTypes;

import com.pdcgame.supermarketsimulatorfinal.Enums.ProductStorageType;
import com.pdcgame.supermarketsimulatorfinal.Managers.EconomyManager;

/**
 * @author prisha, sujal
 */
public class PurchasableProduct extends Product{
    double sellPrice;
    // constructor
    public PurchasableProduct(String type, String name, String description, double price, int quantityInBox, ProductStorageType storageType) {
        super(type, name, description, price, quantityInBox, storageType);
        this.sellPrice = price / 10;
    }

    // get bulk price with current economy
    @Override
    public double getBulkPrice() {
        return this.price * EconomyManager.getInstance().getEconomyRate();
    }

    // get recommended selling price in order to make profit
    public double getRecommendedSinglePrice() {
        return (this.getBulkPrice() / getQuantityInBox()) * 1.50; // (bulk/10) - price for singe multiplied by 1.50 - profit margin
    }

    // get price per item
    public double getSinglePrice() {
        return this.getBulkPrice() / getQuantityInBox();
    }
    // get user decided sell price
    public double getSellPrice() {
        return sellPrice;
    }
    // set user decided sell price
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

}
