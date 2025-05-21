package com.pdcgame.Enums;

/**
 * @author prisha, sujal
 */
public enum PageName {
    MENU_PAGE,
    DIFFICULTY_PAGE,
    GAME_MENU_PAGE,
    EQUIPMENT_PAGE,
    BUY_EQUIPMENT_PAGE,
    SELL_EQUIPMENT_PAGE,
    REPAIR_PAGE,
    STORAGE_PAGE,
    INVENTORY_STORAGE_PAGE,
    PRODUCTS_PAGE,
    POPULAR_PRODUCTS_PAGE,
    BUY_PRODUCTS_PAGE,
    SET_PRICE_PAGE,
    CART_PAGE,
    SCENARIO_PAGE,
    GAME_OVER_PAGE;

    // separates the enum name by "_"
    @Override
    public String toString() {
        String[] words = this.name().split("_"); // split words by underscore
        StringBuilder formatted = new StringBuilder(); // StringBuilder to build a formatted page name

        // add each word seperated by a space
        for(String word : words) {
            formatted.append(word).append(" ");
        }

        // return the formatted page name
        return formatted.toString().trim();
    }
}

