package com.pdcgame.supermarketsimulatorfinal.Pages;

import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;
import com.pdcgame.supermarketsimulatorfinal.Abstracts.Page;
import com.pdcgame.supermarketsimulatorfinal.PageInputs.MenuInput;
import com.pdcgame.supermarketsimulatorfinal.Printers.MenuPrinter;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class MenuPage extends Page {
    private final MenuPrinter menuPrinter = new MenuPrinter(); // object to print options
    private final MenuInput menuInput = new MenuInput();

    // constructor
    public MenuPage() {
        super(PageName.MENU_PAGE);
    }

    // display
    @Override
    public PageName display(Scanner scanner){
        final String PAGE_NAME = "Supermarket Simulator Menu";
        pageHeader(PAGE_NAME);
        menuPrinter.printBody();
        menuPrinter.printChoices("load", "load your previous save.",
                    "new game", "start a new game.",
                    "x", "exit the game.");
        return menuInput.getPageName(scanner);
    }

}
