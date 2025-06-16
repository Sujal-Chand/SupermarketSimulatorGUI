/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.pdcgame.Panels;

import com.pdcgame.GameState;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class StoreStatusPanelTest {

    private StoreStatusPanel panel;

    @Before
    public void setUp() {
        GameState game = GameState.instance();
        game.setBalance(1234.56);
        game.setActions(3);
        game.setTotalActions(5);
        game.day = 7;

        panel = new StoreStatusPanel();
        panel.refresh();  // ensure labels are updated before each test
    }

    @Test
    public void testBalanceLabelUpdatesCorrectly() {
        JLabel balanceLabel = getPrivateLabel(panel, "balanceLabel");
        assertEquals("Balance: $1234.56", balanceLabel.getText());
    }

    @Test
    public void testActionsLabelUpdatesCorrectly() {
        JLabel actionsLabel = getPrivateLabel(panel, "actionsLabel");
        assertEquals("Actions Left: 3/5", actionsLabel.getText());
    }

    @Test
    public void testDayLabelUpdatesCorrectly() {
        JLabel dayLabel = getPrivateLabel(panel, "dayLabel");
        assertEquals("Days Passed: 7", dayLabel.getText());
    }

    // access private JLabel fields
    private JLabel getPrivateLabel(StoreStatusPanel panel, String fieldName) {
        try {
            java.lang.reflect.Field field = StoreStatusPanel.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (JLabel) field.get(panel);
        } catch (Exception e) {
            fail("Unable to access field: " + fieldName);
            return null;
        }
    }
}