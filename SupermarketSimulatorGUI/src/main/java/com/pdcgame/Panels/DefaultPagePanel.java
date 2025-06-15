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

    // Constructor for full layout: SubPage + FunctionPage + StoreStatus
    public DefaultPagePanel(SubPagePanel subPage, FunctionPagePanel functionPage) {
        setLayout(new BorderLayout());
        add(subPage, BorderLayout.CENTER);
        add(functionPage, BorderLayout.EAST);
        add(new StoreStatusPanel(), BorderLayout.SOUTH);
    }

    // Constructor for simpler layout: Just a plain JPanel + StoreStatus
    public DefaultPagePanel(JPanel simplePage) {
        setLayout(new BorderLayout());
        add(simplePage, BorderLayout.CENTER);
        add(new StoreStatusPanel(), BorderLayout.SOUTH);
    }
}