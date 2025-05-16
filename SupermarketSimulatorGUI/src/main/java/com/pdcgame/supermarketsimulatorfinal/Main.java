/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.pdcgame.supermarketsimulatorfinal;

import java.util.Scanner;
/**
 * @author prisha, sujal
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // test
        Scanner scanner = new Scanner(System.in); // scanner object that can be passed around to classes
        PageNavigator.instance().startNavigation(scanner); // start at the menu page, with the scanner
        scanner.close(); // close the scanner
    }
}
