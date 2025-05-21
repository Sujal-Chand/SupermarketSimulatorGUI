package com.pdcgame.Interfaces;

import com.pdcgame.Enums.ProductStorageType;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public interface IOUtilities {

    // used by different ...GetInput functions when user does not specify a correct input
    void invalid(String input);

    // used to read coordinates from scanner
    int[] readCoordinates(Scanner scanner);
    // optional input pass
    int[] readCoordinates(Scanner scanner, String optionalInput);
    // turns string into a coordinate array
    int[] stringToCoordinates(String string);
    // turn an array into a type of string representation of the coordinate
    String coordinatesToString(int[] coordinates);
}
