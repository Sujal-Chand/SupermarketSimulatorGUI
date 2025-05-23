/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author sujalchand
 */
public class LogSettings {
    
    public LogSettings() {
        // turn off all Hibernate logs via JUL programmatically
        LogManager.getLogManager().reset();

        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.SEVERE);  // set root logger to SEVERE or OFF

        for (Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.SEVERE); // set handlers level too
        }
        System.setProperty("org.jboss.logging.provider", "jdk");
    }
}
