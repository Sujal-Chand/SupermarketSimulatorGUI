package com.pdcgame.Panels;

import com.pdcgame.Enums.ActionSource;
import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Managers.BankManager;
import com.pdcgame.Managers.BuilderManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuyEquipmentPanel extends JPanel {

    public BuyEquipmentPanel() {
        setLayout(null);

        JLabel titleLabel = new JLabel("Buy Equipment");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        int[] cell = GameState.instance().getBoardManager().getSelectedCell();
        JLabel subLabel = new JLabel("Selected Cell at x = " + cell[0] + " and y = " + cell[1]);
        subLabel.setBounds(20, 60, 400, 30);
        subLabel.setForeground(new Color(66, 62, 55));
        subLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(subLabel);

        // Container panel for product panels with vertical BoxLayout
        JPanel productContainer = new JPanel();
        productContainer.setLayout(new BoxLayout(productContainer, BoxLayout.Y_AXIS));
        productContainer.setBackground(Color.WHITE);

        for (BoardCell boardCell : BoardCell.values()) {
            if (!boardCell.getIcon().equalsIgnoreCase("[ E ]")) {
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
                productPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                productPanel.setBackground(Color.WHITE);
                productPanel.setMaximumSize(new Dimension(360, 90));
                productPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel nameLabel = new JLabel(boardCell.name());
                nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel iconLabel = new JLabel(boardCell.getIcon());
                iconLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
                iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                String costText = "Cost: $" + String.format("%.2f", boardCell.getItemCost());
                if (boardCell.getCapacity() > 0) {
                    costText += " | Capacity: " + boardCell.getCapacity();
                }
                JLabel costLabel = new JLabel(costText);
                costLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                costLabel.setForeground(Color.DARK_GRAY);
                costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                productPanel.add(Box.createVerticalGlue());
                productPanel.add(nameLabel);
                productPanel.add(Box.createVerticalStrut(5));
                productPanel.add(iconLabel);
                productPanel.add(Box.createVerticalStrut(5));
                productPanel.add(costLabel);
                productPanel.add(Box.createVerticalGlue());
                productPanel.setMaximumSize(new Dimension(800, 90));

                productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                productPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        purchase(boardCell, cell[0], cell[1]);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        productPanel.setBackground(new Color(220, 220, 220));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        productPanel.setBackground(Color.WHITE);
                    }
                });

                productContainer.add(productPanel);
                productContainer.add(Box.createVerticalStrut(10)); // spacing between products
            }
        }

        // JScrollPane with the product container inside
        JScrollPane scrollPane = new JScrollPane(productContainer);
        scrollPane.setBounds(15, 110, 820, 450);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll speed
        add(scrollPane);
    }

    private void purchase(BoardCell selectedItem, int x, int y) {
        InternalCases outcome = BuilderManager.purchaseItem(selectedItem, x, y);

        switch (outcome) {
            case SUCCESS -> {
                JOptionPane.showMessageDialog(this, "Purchase successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            case SPACE_TAKEN -> JOptionPane.showMessageDialog(this, "This space is already taken.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case NO_FUNDS -> JOptionPane.showMessageDialog(this, "Not enough funds to complete purchase.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case NO_ACTIONS -> JOptionPane.showMessageDialog(this, "You don't have enough actions.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case MAX_AMOUNT -> JOptionPane.showMessageDialog(this, "Max amount of this item reached.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case null, default -> JOptionPane.showMessageDialog(this, "Purchase cancelled.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
