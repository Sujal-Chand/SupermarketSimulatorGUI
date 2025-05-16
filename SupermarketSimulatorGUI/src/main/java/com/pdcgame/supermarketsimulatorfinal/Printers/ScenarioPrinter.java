package com.pdcgame.supermarketsimulatorfinal.Printers;

/**
 * @author prisha, sujal
 */
public class ScenarioPrinter extends Printer {

    // prints a scenario banner with specified text
    public void printBanner(String message) {
        final int width = 50;
        String border = "-".repeat(width);
        String spacing = " ".repeat((width - message.length()) / 2);
        String line = spacing + message + spacing;

        if (line.length() < width) {
            line += " ";
        }

        System.out.println("\n" + border);
        System.out.println(line);
        System.out.println(border);

        try {
            Thread.sleep(SMALL_DELAY);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
