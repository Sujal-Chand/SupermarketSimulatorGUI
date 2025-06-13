package com.pdcgame.Panels;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author prish
 */
import com.pdcgame.ProductTypes.Product;
import javax.swing.*;
public class ShelfItem {
    
    ImageIcon icon;
    int x, y, width, height;
    Product product;

    public ShelfItem(ImageIcon icon, int x, int y, int width, int height, Product product) {
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.product = product;
    }
}
  
