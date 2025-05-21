package com.pdcgame.Pages;

import com.pdcgame.Enums.PageName;
import com.pdcgame.Abstracts.Page;
import com.pdcgame.PageInputs.MenuInput;
import com.pdcgame.Printers.MenuPrinter;

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
