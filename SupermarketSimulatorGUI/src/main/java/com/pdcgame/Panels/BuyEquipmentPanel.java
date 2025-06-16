package com.pdcgame.Panels;

import com.pdcgame.Enums.ActionSource;
import com.pdcgame.Enums.BoardCell;
import com.pdcgame.Enums.InternalCases;
import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import com.pdcgame.Managers.ActionManager;
import com.pdcgame.Managers.BankManager;
import com.pdcgame.Managers.BuilderManager;
import com.pdcgame.Panels.Pages.EquipmentControllerPagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuyEquipmentPanel extends JPanel {

    private final int[] selectedCell;

    public BuyEquipmentPanel() {
        setLayout(null);
        setBackground(new Color(250, 250, 240));

        // get currently selected cell coordinates
        selectedCell = GameState.instance().getBoardManager().getSelectedCell();

        // add title and subtitle labels
        addTitleLabel();
        addSelectedCellLabel();

        // create and add the scrollable product list panel
        addProductListScrollPane();
    }

    // adds the main title label at the top
    private void addTitleLabel() {
        JLabel titleLabel = new JLabel("Buy Equipment");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);
    }

    // adds a label that shows the currently selected cell coordinates
    private void addSelectedCellLabel() {
        JLabel subLabel = new JLabel("Selected Cell at x = " + selectedCell[0] + " and y = " + selectedCell[1]);
        subLabel.setBounds(20, 60, 400, 30);
        subLabel.setForeground(new Color(66, 62, 55));
        subLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        add(subLabel);
    }

    // creates the product container panel with all product cards and adds it inside a scroll pane
    private void addProductListScrollPane() {
        JPanel productContainer = createProductContainer();
        JScrollPane scrollPane = new JScrollPane(productContainer);
        scrollPane.setBounds(15, 110, 820, 450);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smoother scroll speed
        add(scrollPane);
    }

    // creates the product container panel that holds product panels vertically
    private JPanel createProductContainer() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);

        // iterate through all board cells and add product panels except empty cells
        for (BoardCell boardCell : BoardCell.values()) {
            if (!boardCell.getIcon().equalsIgnoreCase("[ E ]")) {
                JPanel productPanel = createProductPanel(boardCell);
                container.add(productPanel);
                container.add(Box.createVerticalStrut(10)); // spacing between products
            }
        }

        return container;
    }

    // creates a panel for a single product with labels and mouse interaction
    private JPanel createProductPanel(BoardCell boardCell) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(360, 90));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // add product name label
        JLabel nameLabel = new JLabel(boardCell.name());
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add product icon label
        JLabel iconLabel = new JLabel(boardCell.getIcon());
        iconLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add cost (and capacity if applicable) label
        JLabel costLabel = new JLabel(createCostText(boardCell));
        costLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        costLabel.setForeground(Color.DARK_GRAY);
        costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add spacing and labels to the product panel
        panel.add(Box.createVerticalGlue());
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(costLabel);
        panel.add(Box.createVerticalGlue());

        // set cursor and add mouse listener for click and hover effects
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(createProductMouseListener(panel, boardCell));

        return panel;
    }

    // creates the text for cost and capacity of a product
    private String createCostText(BoardCell boardCell) {
        String costText = "Cost: $" + String.format("%.2f", boardCell.getItemCost());
        if (boardCell.getCapacity() > 0) {
            costText += " | Capacity: " + boardCell.getCapacity();
        }
        return costText;
    }

    // creates a mouse listener for a product panel to handle clicks and hover color changes
    private MouseAdapter createProductMouseListener(JPanel productPanel, BoardCell boardCell) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                purchase(boardCell, selectedCell[0], selectedCell[1]);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                productPanel.setBackground(new Color(220, 220, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                productPanel.setBackground(Color.WHITE);
            }
        };
    }

    // handles the purchase action and shows appropriate messages
    private void purchase(BoardCell selectedItem, int x, int y) {
        InternalCases outcome = BuilderManager.purchaseItem(selectedItem, x, y);

        switch (outcome) {
            case SUCCESS -> JOptionPane.showMessageDialog(this, "Purchase successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            case SPACE_TAKEN -> JOptionPane.showMessageDialog(this, "This space is already taken.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case NO_FUNDS -> JOptionPane.showMessageDialog(this, "Not enough funds to complete purchase.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case NO_ACTIONS -> JOptionPane.showMessageDialog(this, "You don't have enough actions.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case MAX_AMOUNT -> JOptionPane.showMessageDialog(this, "Max amount of this item reached.", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
            case null, default -> JOptionPane.showMessageDialog(this, "Purchase cancelled.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        EquipmentControllerPagePanel.getInstance().updateView();
    }
}

