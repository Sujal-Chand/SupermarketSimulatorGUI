/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 *
 * @author prish
 */

import com.pdcgame.GamePersistence;
import com.pdcgame.GameState;
import com.pdcgame.Managers.ScenarioManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class StorePagePanel extends JPanel {

    private final JPanel messagePanel;
    private final JLabel scenarioImageLabel;
    private final JScrollPane scrollPane;
    private final Timer scenarioTimer;
    private List<String> scenarioMessages;
    private int currentMsgIndex = 0;
    private final ScenarioManager scenarioManager = new ScenarioManager();
    private final JLabel storeStatusBanner;
    private final JPanel scenarioImageContainer; 
    private JLabel timeLabel = new JLabel("Time: --:--");

    
    public StorePagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        JLabel titleLabel = new JLabel("Store");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);
        
        JLabel subTitleLabel = new JLabel("- open/close your store");
        subTitleLabel.setBounds(120, 10, 400, 40);
        subTitleLabel.setForeground(new Color(66, 62, 55));
        subTitleLabel.setFont(new Font("Impact", Font.PLAIN, 30));
        add(subTitleLabel);
        
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 18));
        timeLabel.setBounds(1090, 10, 175, 40);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0));
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(250, 250, 240));
        add(timeLabel);
        

        JButton openStoreButton = new JButton("Open Store");
        openStoreButton.setBounds(10, 60, 180, 40);
        openStoreButton.setFont(new Font("Courier New", Font.BOLD, 16));
        openStoreButton.setBackground(new Color(90, 80, 75));
        openStoreButton.setForeground(Color.WHITE);
        openStoreButton.setFocusPainted(false);
        add(openStoreButton);
        
        storeStatusBanner = new JLabel("STORE CLOSED", SwingConstants.CENTER);
        storeStatusBanner.setBounds(230, 60, 300, 40);
        storeStatusBanner.setFont(new Font("Courier New", Font.BOLD, 18));
        storeStatusBanner.setOpaque(true);
        storeStatusBanner.setBackground(Color.RED);
        storeStatusBanner.setForeground(Color.WHITE);
        add(storeStatusBanner);

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(new Color(250, 250, 240));

        scrollPane = new JScrollPane(messagePanel);
        scrollPane.setBounds(10, 110, 560, 440);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        scenarioImageContainer = new JPanel(new BorderLayout()); 
        scenarioImageContainer.setBounds(650, 100, 500, 500); 
        scenarioImageContainer.setBackground(new Color(236, 234, 213));
        add(scenarioImageContainer);
        
        scenarioImageLabel = new JLabel("", SwingConstants.CENTER);
        scenarioImageContainer.add(scenarioImageLabel, BorderLayout.CENTER);

        scenarioTimer = new Timer(200, e -> showNextMessage());

        openStoreButton.addActionListener(e -> {
            messagePanel.removeAll();
            messagePanel.revalidate();
            messagePanel.repaint();
            scenarioImageLabel.setIcon(null);
            scenarioImageContainer.removeAll();
            scenarioImageContainer.add(scenarioImageLabel); 
            scenarioImageContainer.revalidate();
            scenarioImageContainer.repaint();
            storeStatusBanner.setText("STORE OPEN");
            storeStatusBanner.setBackground(new Color(0, 153, 0)); 

            scenarioMessages = scenarioManager.runDayAndGetMessages();
            currentMsgIndex = 0;
            if (scenarioMessages.isEmpty()) {
                addMessage("Store opened normally. No issues encountered.", "/images/default.png");
            } else {
                scenarioTimer.start();
            }
        });
    }

    private void showNextMessage() {
        if (currentMsgIndex < scenarioMessages.size()) {
            String msg = scenarioMessages.get(currentMsgIndex++);
            String iconPath = determineIcon(msg);

        if (msg.startsWith("============ Time: ")) {
            String extractedTime = msg.replace("============ Time: ", "").replace(" ============", "").trim();
            timeLabel.setText("Time: " + extractedTime);
        }
            addMessage(msg, iconPath);
        } else {
            scenarioTimer.stop();
            endOfDay();
        }
    }

    private void endOfDay() {
        storeStatusBanner.setText("STORE CLOSED");
        storeStatusBanner.setBackground(Color.RED);

        scenarioImageContainer.removeAll();
        
        JPanel endOfDayStats = new JPanel();
        endOfDayStats.setLayout(new BoxLayout(endOfDayStats, BoxLayout.Y_AXIS));
        endOfDayStats.setBackground(new Color(250, 250, 240));
        
        JLabel titleLabel = new JLabel("End OF Day Stats", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.PLAIN, 40));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        endOfDayStats.add(titleLabel);

        JLabel revenueLabel = new JLabel("Number of Sales: " + GameState.instance().getEndOfDayManager().getSalesAmount());
        JLabel profitLabel = new JLabel("Sales Value: $" + String.format("%.2f", GameState.instance().getEndOfDayManager().getSalesValue()));
        JLabel soldLabel = new JLabel("Items Stolen: " + GameState.instance().getEndOfDayManager().getRobberyQty());
        JLabel lostSalesLabel = new JLabel("Stolen Value: $" + String.format("%.2f", GameState.instance().getEndOfDayManager().getRobberyValue()));
        GameState.instance().getEndOfDayManager().handleEndOfDay();
        Font statFont = new Font("Courier New", Font.BOLD, 20);
        for (JLabel label : new JLabel[]{revenueLabel, profitLabel, soldLabel, lostSalesLabel}) {
            label.setAlignmentX(Component.CENTER_ALIGNMENT); 
            label.setFont(statFont);
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            endOfDayStats.add(label);
        }

        scenarioImageContainer.add(endOfDayStats, BorderLayout.CENTER);
        scenarioImageContainer.revalidate();
        scenarioImageContainer.repaint();
    }
    
    private void addMessage(String msg, String iconPath) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.setBackground(new Color(250, 250, 240));

        JLabel messageLabel = new JLabel(msg);
        messageLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        row.add(messageLabel);

        messagePanel.add(row);
        messagePanel.revalidate();
        messagePanel.repaint();

        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));

        URL imgUrl = getClass().getResource(iconPath);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage();

            int originalWidth = img.getWidth(null);
            int originalHeight = img.getHeight(null);

            int maxDimension = 400;
            double scale = (double) maxDimension / Math.max(originalWidth, originalHeight);

            int newWidth = (int) (originalWidth * scale);
            int newHeight = (int) (originalHeight * scale);

            Image scaledImage = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            scenarioImageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    private String determineIcon(String msg) {
        if (msg.contains("[ROBBERY]")) return "/robbery.png";
        if (msg.contains("[BUILDING ISSUE]")) return "/building.png";
        if (msg.contains("[CHARITY DONATION]")) return "/charity.png";
        if (msg.contains("[DEFECTIVE]")) return "/defective.png";
        if (msg.contains("[ECONOMY]")) return "/economy.png";
        if (msg.contains("[EMPLOYEE ERROR]")) return "/employee.png";
        if (msg.contains("[NO PARKING]")) return "/parking.png";
        if (msg.contains("[SALE]")) return "/sale.png";
        if (msg.contains("[LOST SALE]")) return "/lostsale.png";
        if (msg.contains("[WEATHER]")) return "/weather.png";
        if (msg.contains("[EXPIRY]")) return "/expiry.png";
        if (msg.contains("[LONG LINES]")) return "/line.png";
        if (msg.contains("[QUIET]")) return "/quiet.png";
        return "/default.png";
    }
}