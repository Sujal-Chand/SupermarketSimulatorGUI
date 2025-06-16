/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;

/**
 *
 * @author prish
 */
import java.awt.*;

public class BuyProductsPanel extends FunctionPagePanel{

    public BuyProductsPanel() {
        setLayout(new BorderLayout());

        ShelfPanel shelfPanel = new ShelfPanel();
        ProductInfoPanel productInfoPanel = new ProductInfoPanel();

        // listener that updates ProductInfoPanel
        shelfPanel.setProductClickListener(productInfoPanel::showProductInfo);

        
        // layout setup
        add(shelfPanel, BorderLayout.CENTER);
        add(productInfoPanel, BorderLayout.EAST);
    }
}
