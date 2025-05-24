/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author sujal
 */
@Entity
@Table(name = "inventory_save")
public class InventorySave {
    
    @Id
    @Column(name = "PRODUCT_NAME")
    private String name;
    
    @Column(name = "QUANTITY")
    private int quantity;
    
    @Column(name = "SELL_PRICE")
    private double sellPrice;
    
    public InventorySave() {} // default constructor
    
    public InventorySave(String name, int quantity, double sellPrice) {
        this.name = name;
        this.quantity = quantity;
        this.sellPrice = sellPrice;
    }

    // getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSellPrice() {
        return sellPrice;
    }
    
    // setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
    
}
