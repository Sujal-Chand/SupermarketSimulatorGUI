package com.pdcgame.supermarketsimulatorfinal.Managers;

import com.pdcgame.supermarketsimulatorfinal.GameState;
import com.pdcgame.supermarketsimulatorfinal.Interfaces.FileProcessor;
import com.pdcgame.supermarketsimulatorfinal.ProductTypes.PurchasableProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 * @author prisha, sujal
 */
public class InventorySaveManager implements FileProcessor {
    private static final String SAVE_PATH = "./save_folder/inventory_save.csv";
    private static final GameState gameInstance = GameState.instance();
    @Override
    public void load() {
        try(BufferedReader reader = new BufferedReader(new FileReader(SAVE_PATH))) {
            reader.readLine(); // skip first line (header)

            String line;
            while((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length == 3) {
                    String name = values[0]; // read product name
                    int quantity = Integer.parseInt(values[1]); // read quantity in inventory
                    double sellPrice = Double.parseDouble(values[2]); // read the user sell price for product
                    gameInstance.getInventoryManager().setQuantity(name, quantity); // set quantity
                    gameInstance.getProductManager().setSellPrice(name, sellPrice);} // set sell price
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save() {
        try (PrintWriter pw = new PrintWriter(SAVE_PATH)){
            // get all the products in the store
            Collection<PurchasableProduct> allProducts = gameInstance.getProductManager().getPurchasableProducts();
            pw.println("Name,Quantity,sellPrice"); // header

            String[] lines = new String[3]; // string array for name, quantity, price in string format
            for(PurchasableProduct product : allProducts){
                // product name
                lines[0] = product
                        .getName();
                // quantity in inventory
                lines[1] = Integer.toString(gameInstance
                        .getInventoryManager()
                        .getQuantity(product.getName()));
                // store selling price
                lines[2] = Double.toString(product.getSellPrice());
                String data = String.join(",", lines); // join the array with commas to form data line
                pw.println(data); // write the data line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean fileExists() {
        Path save_path = Paths.get(SAVE_PATH);
        return Files.exists(save_path);
    }
}
