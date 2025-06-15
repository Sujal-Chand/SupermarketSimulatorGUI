package com.pdcgame.Enums;

/**
 * @author prisha, sujal
 */
public enum BoardCell {
    EMPTY("[ E ]", 0, 0, null),
    SHELF("[ S ]", 200, 50, ProductStorageType.SHELF),
    LARGE_SHELF("[L S]", 400, 100, ProductStorageType.SHELF),
    FRIDGE("[ F ]", 600, 50, ProductStorageType.FRIDGE),
    DOUBLE_FRIDGE("[D F]", 1000, 100, ProductStorageType.FRIDGE),
    CHEST_FREEZER("[C F]", 600, 80, ProductStorageType.FROZEN),
    LARGE_CHEST_FREEZER("[LCF]", 1200, 160, ProductStorageType.FROZEN),
    CASHIER("[C A]", 1000, 0, null);


    final String icon;
    final double cost;
    final int capacity;
    final ProductStorageType storageType;

    // constructor
    BoardCell(String icon, double cost, int capacity, ProductStorageType storageType) {
        this.icon = icon;
        this.cost = cost;
        this.capacity = capacity;
        this.storageType = storageType;
    }
    // get icon
    public String getIcon() {
        return icon;
    }
    // get cell cost
    public double getItemCost() {
        return cost;
    }
    // get storage capacity for cell
    public int getCapacity() {
        return capacity;
    }
    // get cell storage type
    public ProductStorageType getStorageType() {
        return storageType;
    }


    @Override
    // returns name in nice formatted manner, capital first letters, rest lower-case, with spaces replacing dashes.
    public String toString() {
        String[] words = this.name().toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
        return sb.toString().trim();
    }

    public boolean canStoreProducts() {
        return this != CASHIER && this != EMPTY;
    }

}
