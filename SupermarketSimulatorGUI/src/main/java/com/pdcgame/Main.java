/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.pdcgame;

import com.pdcgame.PageNavigator;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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
