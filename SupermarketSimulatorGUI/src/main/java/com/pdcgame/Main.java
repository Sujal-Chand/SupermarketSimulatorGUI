/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.pdcgame;

import com.pdcgame.PageNavigator;
import java.util.Scanner;
/**
 * @author prisha, sujal
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // scanner object that can be passed around to classes
        PageNavigator.instance().startNavigation(scanner); // start at the menu page, with the scanner
        scanner.close(); // close the scanner
    }
}
