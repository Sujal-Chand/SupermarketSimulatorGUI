package com.pdcgame.Managers;

import com.pdcgame.Enums.BoardCell;
import com.pdcgame.EquipmentStorage;
import com.pdcgame.GameState;
import com.pdcgame.IOHandler;
import com.pdcgame.Interfaces.FileProcessor;
import com.pdcgame.Loaders.StorageLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author prisha, sujal
 */
public class StorageSaveManager implements FileProcessor {
    private static final String SAVE_PATH = "./save_folder/storage_save.csv";
    private static final GameState gameInstance = GameState.instance();
    private final StorageLoader storageLoader = new StorageLoader();
    @Override
    public void load() {
        try(BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH))) {
            String line;
            int count = 0;

            String coordinate = "";
            BoardCell cell = null;
            boolean functionStatus = false;
            Map<String, Integer> loadedProducts = new HashMap<>();

            while((line = reader.readLine()) != null)  {
                switch (count) {
                    case 0 -> coordinate = line; // set the string coordinate to line being read
                    case 1 -> cell = BoardCell.valueOf(line); // set board sell to line being read
                    case 2 -> functionStatus = Boolean.parseBoolean(line); // set function status to line being read
                    case 3 -> {
                        loadedProducts.clear();
                        if(!line.isEmpty()) {
                            // load stored products from the file line
                            loadedProducts.putAll(storageLoader.fromCSV(line));
                        }
                        // add the location
                        gameInstance.getFloorStorageManager().addLocation(coordinate, cell, functionStatus);

                        EquipmentStorage storage = gameInstance.getFloorStorageManager().getLocationWithStorage().get(coordinate);
                        storage.setStoredProducts(loadedProducts);

                        count = -1; // reset for next block of items
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try(PrintWriter pw = new PrintWriter(SAVE_PATH)) {
            Map<String, EquipmentStorage> locationWithStorage = gameInstance.getFloorStorageManager().getLocationWithStorage();
            for(String location : locationWithStorage.keySet()) {
                EquipmentStorage equipment = locationWithStorage.get(location); // get the equipment storage object at location key value
                pw.println(location); // write the location coordinate
                pw.println(equipment.getEquipmentType().name()); // write the name (boardcell)
                pw.println(equipment.getFunctionStatus()); // write the functional status
                if(equipment.storedProducts == null || equipment.storedProducts.isEmpty()) {
                    pw.println(""); // if there are no products just write a blank line
                } else {
                    // load the products stored in this storage location as a string
                    String storageLine = storageLoader.toCSV(equipment.storedProducts);
                    pw.println(storageLine); // write the csv string representing the stored products
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean fileExists() {
        Path save_path = Paths.get(SAVE_PATH);
        return Files.exists(save_path);
    }
}
