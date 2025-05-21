package com.pdcgame.Loaders;

import com.pdcgame.Enums.ProductStorageType;
import com.pdcgame.Interfaces.CSVReader;
import com.pdcgame.ProductTypes.PurchasableProduct;

/**
 * @author prisha, sujal
 */
public class ProductLoader implements CSVReader<PurchasableProduct> {

    @Override
    public String toCSV(PurchasableProduct product) {
        // convert product fields to CSV string
        return String.join(",",
                product.getType(),
                product.getName(),
                product.getDescription(),
                String.valueOf(product.getBulkPrice()),
                String.valueOf(product.getQuantityInBox()),
                product.getStorageType().name()
        );
    }

    @Override
    public PurchasableProduct fromCSV(String csvLine) {
        // split input CSV line into fields
        String[] fields = csvLine.split(",");

        // validate expected number of fields
        if(fields.length < 6) {
            throw new IllegalArgumentException("Can't read products file...");
        }

        // parse fields from string to appropriate types
        String type = fields[0];
        String name = fields[1];
        String description = fields[2];
        double price = Double.parseDouble(fields[3]);
        int quantityInBox = Integer.parseInt(fields[4]);
        ProductStorageType storageType = ProductStorageType.valueOf(fields[5]);

        // create and return a new product object
        return new PurchasableProduct(type, name, description, price, quantityInBox, storageType);
    }
}
