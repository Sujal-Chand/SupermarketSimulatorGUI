/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author sujal
 */
@Entity
@Table(name = "storage_save")
public class StorageItemSave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "coordinate_id")
    private String coordinateID;
    
    @Column(name = "product_name")
    private String productName; 
    
    @Column(name = "quantity")
    private int quantity;

    public StorageItemSave() {}
    
    public StorageItemSave(String coordinateID, String productName, int quantity) {
        this.coordinateID = coordinateID;
        this.productName = productName;
        this.quantity = quantity;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
