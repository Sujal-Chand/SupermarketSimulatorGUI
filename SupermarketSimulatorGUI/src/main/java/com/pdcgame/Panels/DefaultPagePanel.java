/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels;


/**
 *
 * @author prish
 */

import javax.swing.*;
import java.awt.*;

public class DefaultPagePanel extends JPanel {
    private final StoreStatusPanel statusPanel;

    public DefaultPagePanel(SubPagePanel subPage, FunctionPagePanel functionPage) {
        setLayout(new BorderLayout());
        add(subPage, BorderLayout.CENTER);
        add(functionPage, BorderLayout.EAST);

        statusPanel = new StoreStatusPanel(); // Save reference
        add(statusPanel, BorderLayout.SOUTH);
    }

    public DefaultPagePanel(JPanel simplePage) {
        setLayout(new BorderLayout());
        add(simplePage, BorderLayout.CENTER);

        statusPanel = new StoreStatusPanel(); // Save reference
        add(statusPanel, BorderLayout.SOUTH);
    }
}