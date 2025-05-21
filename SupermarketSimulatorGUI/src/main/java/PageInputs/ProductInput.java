package main.java.PageInputs;

import main.java.Abstracts.PageInputs;
import main.java.Enums.PageName;
import main.java.Managers.ActionManager;
import main.java.Managers.BankManager;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class ProductInput extends PageInputs {
    // decide what page to go to next
    @Override
    public PageName getPageName(Scanner scanner) throws InterruptedException {
        while(true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "x": return PageName.GAME_MENU_PAGE;
                case "1":
                case "buy": return PageName.BUY_PRODUCTS_PAGE;
                case "2":
                case "price":
                case "prices": return PageName.SET_PRICE_PAGE;
                case "3":
                case "popular": return PageName.POPULAR_PRODUCTS_PAGE;
                case "4":
                case "cart": return PageName.CART_PAGE;
                case "5":
                case "purchase action": {
                    if(BankManager.possiblePurchase(ActionManager.getCost())) {
                        ActionManager.purchaseAction();
                        System.out.println("Purchase successful. Going back to game menu");
                        Thread.sleep(1000);
                    } else {
                        System.out.println("Purchase failed. You don't have enough money.");
                        Thread.sleep(1000);
                    }
                    return PageName.GAME_MENU_PAGE;
                }
                default: invalid(input);
            }
        }
    }
}
