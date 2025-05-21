package main.java.Printers;

import main.java.Enums.ProductStorageType;

/**
 * @author prisha, sujal
 */
public class Printer {

    public static final int SMALL_DELAY = 500; //500 millisecond delay
    public static final int BIG_DELAY = 1000; //1000 millisecond delay

    // fall-back print body function
    public void printBody() {
        System.out.println("lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        System.out.println("this page has no body, consider @Override on printBody().");
    }

    // default print choices function
    public void printChoices(String... choicesWithDescriptions) {
        int count = 0;
        // fall-back if choices were not provided
        if(choicesWithDescriptions.length == 0 || choicesWithDescriptions.length % 2 != 0) {
            System.out.println("yikes! choices were not adequately supplied.");
            return;
        }

        System.out.println(); // vertical padding
        for(String text : choicesWithDescriptions) {
            count++;
            System.out.printf(count % 2 == 1 ? "type '%s' " : "to %s\n", text);
        }
        System.out.print(">> ");
    }

    // prints text with the delay specified
    public static void printWithDelay(String message, int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.print(message);
    }
}
