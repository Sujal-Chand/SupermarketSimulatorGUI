package com.pdcgame.Interfaces;

/**
 * @author prisha, sujal
 */
public interface CSVReader<T> {
    String toCSV(T data); // turns csv to String
    T fromCSV(String csvData); // turns Object to csv
}
