/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.pdcgame;

import java.util.Scanner;

/**
 * @author prisha, sujal
 */
public class SupermarketGameCLI {

    public static void main(String[] args) throws InterruptedException {
        LogSettings logSettings = new LogSettings(); // turns off Hibernate auto logging and warnings
        Scanner scanner = new Scanner(System.in);
        PageNavigator.instance().startNavigation(scanner);
        
        GamePersistence.shutdownSession();
        scanner.close();
    }
}
