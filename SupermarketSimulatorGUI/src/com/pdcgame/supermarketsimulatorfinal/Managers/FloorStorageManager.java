package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.Enums.BoardCell;
import com.pdcgame.supermarketsimulatorfinal.EquipmentStorage;

import java.util.*;

/**
 * @author prisha, sujal
 */
public class FloorStorageManager {
    private final Map<String, EquipmentStorage> locationWithStorage = new HashMap<>();

    public void addLocation(String locationCoordinate, BoardCell equipmentType, boolean isFunctioning) {
        locationWithStorage.put(locationCoordinate, new EquipmentStorage(equipmentType, isFunctioning)); // adds the location and type of equipment to a HashMap
        // the EquipmentStorage handles what
    }

    // removes a location from the store
    public void removeLocation(String locationCoordinate) {
        locationWithStorage.remove(locationCoordinate);
    }

    // returns the total quantity of a product on the store floor
    public int totalQuantityOnFloor(String productName) {
        int quantityOnFloor = 0;
        for(EquipmentStorage storage : locationWithStorage.values()) {
             if(!storage.storedProducts.isEmpty()) quantityOnFloor += storage.getQuantity(productName);
        }
        return quantityOnFloor;
    }

    // removes product specified from random locations in the store; also returns boolean for any future behaviour required
    public boolean randomRemoveProduct(String productName, int quantity) {
        if(quantity > totalQuantityOnFloor(productName)) return false; // cannot remove more products than there is that exist

        int quantityLeftToRemove = quantity; // how much is left to remove

        List<Map.Entry<String, EquipmentStorage>> storageLocations = new ArrayList<>(locationWithStorage.entrySet());
        Collections.shuffle(storageLocations); // shuffle all storage locations randomly
        for(Map.Entry<String, EquipmentStorage> storageLocation : storageLocations) {
            // set the storage at the location
            EquipmentStorage storage = storageLocation.getValue();
            if(storage.getFunctionStatus()) {
                // calculate how much is available at the location to determine the max that can be removed
                int maxCanRemove = Math.min(storage.getQuantity(productName), quantityLeftToRemove);

                quantityLeftToRemove -= maxCanRemove; // modify quantity left to remove
                locationWithStorage.get(storageLocation.getKey()).removeProduct(productName, maxCanRemove); // remove the maximum amount that can be removed at location
            }
            // if all quantity was removed then return true
            if(quantityLeftToRemove <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean addProductAt(String locationCoordinate, String productName, int quantity) {
        if(!locationWithStorage.get(locationCoordinate).canFit(quantity)) return false; // if the storage location is at max capacity dont allow items to be added

        locationWithStorage.get(locationCoordinate).addProduct(productName, quantity); // add the quantity specified
        return true;
    }

    public boolean removeProductAt(String locationCoordinate, String productName, int quantity) {
        if(quantity > locationWithStorage.get(locationCoordinate).getQuantity(productName)) return false; // if the storage location doesn't have enough quantity return false

        locationWithStorage.get(locationCoordinate).removeProduct(productName, quantity); // remove the quantity specified
        return true;
    }

    // get the quantity of a product at a particular location
    public int getProductQuantityAt(String locationCoordinate, String productName) {
        return locationWithStorage.get(locationCoordinate).getQuantity(productName);
    }

    // get the hashmap of locations with storage objects
    public Map<String, EquipmentStorage> getLocationWithStorage() {
        return locationWithStorage;
    }

    // get how many spaces are available at a location
    public int getAvailableSpacesAt(String locationCoordinate) {
        return locationWithStorage.get(locationCoordinate).getEmptySpaces();
    }

    // get the functionality status of equipment at a location
    public boolean isFunctioning(String locationCoordinate) {
        return locationWithStorage.get(locationCoordinate).getFunctionStatus();
    }

    public void breakItem(String locationCoordinate) {
        locationWithStorage.get(locationCoordinate).setFunctioning(false);
        locationWithStorage.get(locationCoordinate).storedProducts.clear(); // clear any stored products
    }

    public void repairItem(String locationCoordinate) {
        locationWithStorage.get(locationCoordinate).setFunctioning(true);
    }



}
