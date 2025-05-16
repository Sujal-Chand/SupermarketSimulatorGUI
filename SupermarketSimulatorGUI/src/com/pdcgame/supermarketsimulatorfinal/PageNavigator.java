package com.pdcgame.supermarketsimulatorfinal;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Managers.ActionManager;
import com.pdcgame.supermarketsimulatorfinal.Pages.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class PageNavigator {
    public static PageNavigator navigator = new PageNavigator();
    public final Map<PageName, Page> pageMap = new HashMap<>(); // page hashmap
    private PageName currentPage = PageName.MENU_PAGE; // start at menu page

    private PageNavigator() {
        batchRegisterPages();
    }

    private void batchRegisterPages() {
        // puts all the pages that will be used in the page hash map
        registerPage(PageName.MENU_PAGE, new MenuPage());
        registerPage(PageName.EQUIPMENT_PAGE, new EquipmentPage());
        registerPage(PageName.BUY_EQUIPMENT_PAGE, new BuyEquipmentPage());
        registerPage(PageName.SELL_EQUIPMENT_PAGE, new SellEquipmentPage());
        registerPage(PageName.STORAGE_PAGE, new StoragePage());
        registerPage(PageName.INVENTORY_STORAGE_PAGE, new InventoryStoragePage());
        registerPage(PageName.PRODUCTS_PAGE, new ProductsPage());
        registerPage(PageName.POPULAR_PRODUCTS_PAGE, new PopularProductPage());
        registerPage(PageName.BUY_PRODUCTS_PAGE, new BuyProductPage());
        registerPage(PageName.SET_PRICE_PAGE, new SetPricePage());
        registerPage(PageName.CART_PAGE, new CartPage());
        registerPage(PageName.DIFFICULTY_PAGE, new DifficultyPage());
        registerPage(PageName.GAME_MENU_PAGE, new GameMenuPage());
        registerPage(PageName.SCENARIO_PAGE, new ScenarioPage());
        registerPage(PageName.GAME_OVER_PAGE, new GameOverPage());
    }

    // get singleton instance
    public static PageNavigator instance() {
        return navigator;
    }

    public void registerPage(PageName pageName, Page page) {
        pageMap.put(pageName, page);
    }

    public void getPage(PageName pageName, Scanner scanner) throws InterruptedException {
        Page page = pageMap.get(pageName);
        if(page != null) {
            page.display(scanner);
        } else {
            System.out.println("\nCouldn't find that page.");
        }
    }


    // start the game navigation loop
    public void startNavigation(Scanner scanner) throws InterruptedException {
        while(currentPage != null) {
            Page page = pageMap.get(currentPage); // set page to directed page
            if(page == null) {
                System.out.println("\nyikes! Perhaps you forgot to register that page? (╹ -╹)"); // error message
                break;
            }
            currentPage = page.display(scanner); // display new page
            page.pageFooter();
        }
    }
}
