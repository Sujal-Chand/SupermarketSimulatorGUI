package main.java;

import main.java.Enums.BoardCell;
import main.java.Enums.ProductStorageType;

import java.util.Map;

/**
 * @author prisha, sujal
 */
public class EquipmentStorage extends ProductStorage {
    private final BoardCell equipmentType;
    private boolean isFunctioning;

    // constructor
    public EquipmentStorage(BoardCell equipmentType, boolean isFunctioning) {
        this.equipmentType = equipmentType;
        this.isFunctioning = isFunctioning;
    }

    // get equipment type
    public BoardCell getEquipmentType() {
        return equipmentType;
    }

    @Override
    public void addProduct(String productName, int quantity) {
        if(equipmentType.getStorageType() == null) return; // don't allow adding of product if the equipment can't store products (Cashiers)

        ProductStorageType destinationStorageType = GameState.instance().getProductManager().getProduct(productName).getStorageType();

        // only add the product if it matches storage type with the boardCell and has an available amount of spaces
        if((equipmentType.getStorageType() == destinationStorageType) && canFit(quantity)) super.addProduct(productName, quantity);
    }

    // get the amount of empty spaces available
    public int getEmptySpaces() {
        int takenSpaces = storedProducts.values().stream().mapToInt(Integer::intValue).sum();
        return Math.max(0, equipmentType.getCapacity() - takenSpaces);
    }

    public boolean canFit(int quantity) {
        return getEmptySpaces() >= quantity; // returns true if there is enough space to fit that quantity of product; false otherwise
    }

    public boolean getFunctionStatus() {
        return isFunctioning; // return function status
    }

    public void setFunctioning(boolean isFunctioning) {
        this.isFunctioning = isFunctioning; // set function status
    }

    // set stored products to existing hashmap
    public void setStoredProducts(Map<String, Integer> newProducts) {
        storedProducts.clear();
        storedProducts.putAll(newProducts);
    }
}
