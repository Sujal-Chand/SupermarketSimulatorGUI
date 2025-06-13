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

public class DefaultPagePanel extends JPanel{

    public DefaultPagePanel(SubPagePanel subPage) {
        setLayout(new BorderLayout());
        add(subPage, BorderLayout.CENTER);
        add(new GameBoardPanel(), BorderLayout.EAST);
        add(new StoreStatusPanel(), BorderLayout.SOUTH);
    }

}
