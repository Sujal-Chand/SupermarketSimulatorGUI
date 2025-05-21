package main.java.ProductTypes;

import main.java.Enums.ProductStorageType;

/**
 * @author prisha, sujal
 */
public class Product {
    private final String type, name, description;
    public final double price;
    private final int quantityInBox;
    private final ProductStorageType storageType;

    // constructor
    public Product(String type, String name, String description, double price, int quantityInBox, ProductStorageType storageType) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInBox = quantityInBox;
        this.storageType = storageType;
    }

    // get the type of product
    public String getType() {
        return type;
    }
    // get the product name
    public String getName() {
        return name;
    }
    // get product description
    public String getDescription() {
        return description;
    }
    // get product box quantity
    public int getQuantityInBox() {
        return quantityInBox;
    }
    // get the bulk price to purchase a product
    public double getBulkPrice() {
        return price;
    }
    // get the storage location the product can be stored
    public ProductStorageType getStorageType() {
        return storageType;
    }

    // equals and hashCode only based on name (to work correctly with HashSet)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
