package com.pdcgame.supermarketsimulatorfinal.PageInputs;

import com.pdcgame.supermarketsimulatorfinal.Abstracts.PageInputs;
import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class PopularProductInput extends PageInputs {
    // decide what page to go to next
    @Override
    public PageName getPageName(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "x": return PageName.PRODUCTS_PAGE;
                case "buy": return PageName.BUY_PRODUCTS_PAGE;
                default: invalid(input);
            }
        }
    }
}
