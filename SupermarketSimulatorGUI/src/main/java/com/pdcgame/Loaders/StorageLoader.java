package com.pdcgame.Loaders;

import com.pdcgame.Interfaces.CSVReader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author prisha, sujal
 */
public class StorageLoader implements CSVReader<Map<String, Integer>> {

    @Override
    public String toCSV(Map<String, Integer> storedProducts) {
        StringBuilder stringBuilder = new StringBuilder();

        // iterate over the map and format each entry as "productName,quantity;"
        for (Map.Entry<String, Integer> entry : storedProducts.entrySet()) {
            stringBuilder.append(entry.getKey()).append(",").append(entry.getValue()).append(";");
        }

        // remove the trailing semicolon if present
        if (!stringBuilder.isEmpty()) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }

        // final output format: "Rice,10;Milk,12;Cereal,10"
        return stringBuilder.toString();
    }

    @Override
    public Map<String, Integer> fromCSV(String csvData) {
        Map<String, Integer> storedProducts = new HashMap<>();

        // return empty map if the CSV data is null or empty
        if (csvData == null || csvData.isEmpty()) return storedProducts;

        // split CSV string by ';' to separate each product entry
        String[] productWithQuantity = csvData.split(";");

        for (String productEntry : productWithQuantity) {
            // split each entry by ',' into product name and quantity
            String[] productAndQuantity = productEntry.split(",", 2);
            if (productAndQuantity.length == 2) {
                String productName = productAndQuantity[0];
                int quantity = Integer.parseInt(productAndQuantity[1]);
                storedProducts.put(productName, quantity);
            }
        }

        return storedProducts;
    }
}

