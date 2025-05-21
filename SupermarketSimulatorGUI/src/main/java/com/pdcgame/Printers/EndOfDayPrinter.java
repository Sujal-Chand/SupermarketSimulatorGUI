package com.pdcgame.Printers;

import com.pdcgame.Managers.EndOfDayManager;

/**
 * @author prisha, sujal
 */
public class EndOfDayPrinter {

    // print end of day report
    public void printReport(EndOfDayManager stats) {
        System.out.println("================== END OF DAY SUMMARY ==================");
        System.out.printf(" %-25s : %d%n", "Number of Sales", stats.getSalesAmount());
        System.out.printf(" %-25s : $%.2f%n", "Sales Value", stats.getSalesValue());
        System.out.printf(" %-25s : %d%n", "Number of Robbers", stats.getRobberyAmount());
        System.out.printf(" %-25s : $%.2f%n", "Stolen Value", stats.getRobberyValue());
        System.out.printf(" %-25s : %d%n", "Items Stolen", stats.getRobberyQty());
        System.out.println("========================================================");
    }
}
