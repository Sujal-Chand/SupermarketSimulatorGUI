/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.Enums.Difficulty;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.Enums.ProductStorageType;
import com.pdcgame.GameState;
import com.pdcgame.Managers.CartManager;
import com.pdcgame.ProductTypes.Product;
import com.pdcgame.ProductTypes.PurchasableProduct;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static org.junit.Assert.*;

public class ProductInfoPanelTest {

    private ProductInfoPanel panel;
    private Product testProduct;
    
    private boolean componentContainsText(JComponent component, String text) {
    for (Component comp : component.getComponents()) {
        if (comp instanceof JLabel label && label.getText().contains(text)) {
            return true;
        } else if (comp instanceof JComponent inner) {
            if (componentContainsText(inner, text)) {
                return true;
            }
        }
    }
    return false;
    }

    @Before
    public void setUp() {
        panel = new ProductInfoPanel();
        CartManager.instance().clear();
        GameState.instance().setDifficulty(Difficulty.Easy);
        testProduct = new PurchasableProduct("Test Type", "TestProduct", "Test Description", 10.0, 5, ProductStorageType.SHELF);
    }

    @After
    public void tearDown() {
        CartManager.instance().clear();
    }

    @Test
    public void testShowProductInfoDisplaysCorrectName() {
        Product mockProduct = new Product("Basic Products","Apple", "Fresh red apple", 1.5, 10, ProductStorageType.SHELF);
        panel.showProductInfo(mockProduct);

        assertTrue("Product name 'Apple' not displayed", componentContainsText(panel, "Apple"));
    }

    @Test
    public void testShowProductInfoDisplaysDescription() {
        Product mockProduct = new Product("Basic Products","Banana", "Organic bananas from Ecuador", 0.99, 20, ProductStorageType.SHELF);
        panel.showProductInfo(mockProduct);

        assertTrue("Product description not displayed", componentContainsText(panel, "Organic bananas from Ecuador"));
    }

    @Test
    public void testShowProductInfoDisplaysPrice() {
        Product mockProduct = new Product("Dairy Products","Milk", "Full cream milk", 3.49, 5, ProductStorageType.FRIDGE);
        panel.showProductInfo(mockProduct);

        assertTrue("Product price not displayed", componentContainsText(panel, "3.49"));
    }

    @Test
    public void testShowProductInfoDisplaysQuantity() {
        Product mockProduct = new Product("Basic Products","Bread", "Wholegrain", 2.20, 15, ProductStorageType.SHELF);
        panel.showProductInfo(mockProduct);

        assertTrue("Product quantity not displayed", componentContainsText(panel, "15"));
    }

    @Test
    public void testShowProductInfoDisplaysStorageType() {
        Product mockProduct = new Product("Freezer Products","Ice Cream", "Chocolate flavour", 5.99, 3, ProductStorageType.FROZEN);
        panel.showProductInfo(mockProduct);

        assertTrue("Product storage type 'FROZEN' not displayed", componentContainsText(panel, "FROZEN"));
    }
    
    @Test
    public void testAddToCartIncreasesQuantity() {
        panel.showProductInfo(testProduct);
        panel.addToCartButton.doClick();

        Map<String, Integer> cartItems = CartManager.instance().getStoredProducts();
        assertTrue("Cart should contain TestProduct", cartItems.containsKey("TestProduct"));
        assertEquals("Quantity should be 1", 1, (int) cartItems.get("TestProduct"));
    }
    
    @Test
    public void testAddSameProductTwice() {
        panel.showProductInfo(testProduct);
        panel.addToCartButton.doClick();
        panel.addToCartButton.doClick();

        assertEquals("Quantity should be 2 after adding twice",
                2, (int) CartManager.instance().getStoredProducts().get("TestProduct"));
    }
    
    @Test
    public void testSuccessfulCheckoutClearsCart() {
        panel.showProductInfo(testProduct);
        panel.getPurchaseCartButton().doClick();

        GameState.instance().setBalance(100);
        GameState.instance().setActions(5);

        InternalCases result = CartManager.instance().canCheckoutCart();
        assertEquals("Precondition failed, expected SUCCESS", InternalCases.SUCCESS, result);

        panel.getPurchaseCartButton().doClick();

        assertTrue("Cart should be empty after purchase", CartManager.instance().cartEmpty());
    }
}
