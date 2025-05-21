package main.java.PageInputs;

import main.java.Abstracts.PageInputs;
import main.java.Enums.PageName;

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
