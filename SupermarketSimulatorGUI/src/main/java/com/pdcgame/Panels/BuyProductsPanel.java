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
    
    private final ShelfPanel shelfPanel;
    private final ProductInfoPanel productInfoPanel;

    public BuyProductsPanel() {
        setLayout(new BorderLayout());

        shelfPanel = new ShelfPanel();
        productInfoPanel = new ProductInfoPanel();

        // Listener that updates ProductInfoPanel
        shelfPanel.setProductClickListener(product -> {
            productInfoPanel.showProductInfo(product);
        });

        
        // Layout setup - you can adjust positions
        add(shelfPanel, BorderLayout.CENTER);
        add(productInfoPanel, BorderLayout.EAST);
    }
}
