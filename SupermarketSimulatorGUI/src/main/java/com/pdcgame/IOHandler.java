package com.pdcgame;

import com.pdcgame.Interfaces.IOUtilities;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class IOHandler implements IOUtilities {

    private final static IOHandler inputInstance = new IOHandler();

    public static IOHandler instance() {
        return inputInstance;
    }

    // invalid input handler
    @Override
    public void invalid(String input) {
        System.out.printf("'%s' was not a valid option.\n>> ", input);
    }

    // read coordinates from scanner
    @Override
    public int[] readCoordinates(Scanner scanner) {
        return readCoordinates(scanner, null);
    }

    // see if optionalInput matches, if not just handle getting input from user
    @Override
    public int[] readCoordinates(Scanner scanner, String optionalInput) {
        while (true) {
            String input;

            if (optionalInput != null) {
                input = optionalInput.trim();
                optionalInput = null;
            }
            else input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("x")) return null;

            String[] parts = input.split("[,\\s]+");

            // unexpected part length
            if (parts.length != 2) {
                System.out.print("Please enter coordinates as x y or x,y >> ");
                continue;
            }

            try {
                final int min = 0, max = 10;
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                // coordinates out of bounts
                if (x < min || x > max || y < min || y > max) {
                    System.out.printf("Coordinates must be between %d and %d. Try again >> ", min, max);
                    continue;
                }
                return new int[]{x, y};
            } catch (NumberFormatException e) {
                System.out.print("Invalid number format. Try again >> ");
            }
        }
    }

    // turns String too X,Y coordinate array
    @Override
    public int[] stringToCoordinates(String string) {
        String[] parts = string.split(",");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new int[]{x, y};
    }

    // turns coordinate array to XX,YY string
    @Override
    public String coordinatesToString(int[] coordinates) {

        return String.format("%02d,%02d", coordinates[0], coordinates[1]);
    }
}
