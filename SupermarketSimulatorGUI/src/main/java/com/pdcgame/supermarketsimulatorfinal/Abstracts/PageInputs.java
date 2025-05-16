package com.pdcgame.supermarketsimulatorfinal.Abstracts;

import com.pdcgame.supermarketsimulatorfinal.Enums.PageName;

import java.util.Scanner;

public abstract class PageInputs{
     abstract public PageName getPageName(Scanner scanner) throws InterruptedException;

     // default invalid input message
     public void invalid(String input) {
          System.out.printf("'%s' was not a valid option.\n>> ", input);
     }
}
