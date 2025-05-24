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
        LogSettings logSettings = new LogSettings();
        Scanner scanner = new Scanner(System.in);
        PageNavigator.instance().startNavigation(scanner);
        
        GamePersistence.shutdownSession();
        scanner.close();
    }
}
