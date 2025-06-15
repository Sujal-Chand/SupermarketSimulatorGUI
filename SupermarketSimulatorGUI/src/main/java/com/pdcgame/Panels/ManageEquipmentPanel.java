package com.pdcgame.Panels;

import com.pdcgame.GameState;

import javax.swing.*;
import java.awt.*;

public class ManageEquipmentPanel extends JPanel {

    public ManageEquipmentPanel() {
        int[] cell = GameState.instance().getBoardManager().getSelectedCell();
        String cellName = GameState.instance().getBoardManager().getCell(cell[0], cell[1]).name();
        setLayout(null);

        JLabel titleLabel = new JLabel(cellName);
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        JLabel subLabel = new JLabel("Selected Cell at x = " + cell[0] + " and y = " + cell[1]);
        subLabel.setBounds(20, 60, 400, 30);
        subLabel.setForeground(new Color(66, 62, 55));
        subLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(subLabel);
    }
}
