package com.pdcgame.supermarketsimulatorfinal.Abstracts;

import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public abstract class Page {
    protected final int TOTAL_WIDTH = 100; // total width of the header line
    private final PageName thisPage;

    // page constructor that sets PageName
    public Page(PageName thisPage) {
        this.thisPage = thisPage;
    }

    // fallback for display called before being overridden
    public abstract PageName display(Scanner scanner) throws InterruptedException;

    // fallback for page name not being provided - use PageName instead
    public void pageHeader(){
        pageHeader(thisPage.toString());
    }

    // page header handle function
    public void pageHeader(String name) {
        int nameWithSpacesLength = name.length() + 2; // for the spaces around the name
        int totalPadding = TOTAL_WIDTH - nameWithSpacesLength; // total padding taking in account the header name
        // get the left and right padding
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;

        String leftEquals = "=".repeat(leftPadding);
        String rightEquals = "=".repeat(rightPadding);

        System.out.print("\n");
        System.out.println(leftEquals + " " + name + " " + rightEquals);
    }

    // page footer handle function
    public void pageFooter() {
        String equals = "=".repeat(TOTAL_WIDTH);
        System.out.println(equals);
    }

}
