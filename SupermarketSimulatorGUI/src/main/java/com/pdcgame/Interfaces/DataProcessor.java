package com.pdcgame.Interfaces;

/**
 * @author prisha, sujal
 */
public interface DataProcessor {
    void load(); // loads a file
    void save(); // saves a file
    boolean fileExists(); // checks if a file exists
}
