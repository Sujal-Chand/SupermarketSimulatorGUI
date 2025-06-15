/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Panels.Pages;

/**
 *
 * @author prish
 */

import com.pdcgame.Managers.ScenarioManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class OpenStorePagePanel extends JPanel {

    private final JPanel messagePanel;
    private final JLabel scenarioImageLabel;
    private final JScrollPane scrollPane;
    private final Timer scenarioTimer;
    private List<String> scenarioMessages;
    private int currentMsgIndex = 0;
    private final ScenarioManager scenarioManager = new ScenarioManager();

    public OpenStorePagePanel() {
        setLayout(null);
        setBackground(new Color(236, 234, 213));

        JLabel titleLabel = new JLabel("Open Store");
        titleLabel.setBounds(20, 10, 400, 40);
        titleLabel.setForeground(new Color(66, 62, 55));
        titleLabel.setFont(new Font("Impact", Font.BOLD, 40));
        add(titleLabel);

        JButton openStoreButton = new JButton("Open Store");
        openStoreButton.setBounds(10, 60, 200, 40);
        openStoreButton.setFont(new Font("Courier New", Font.BOLD, 16));
        openStoreButton.setBackground(new Color(90, 80, 75));
        openStoreButton.setForeground(Color.WHITE);
        openStoreButton.setFocusPainted(false);
        add(openStoreButton);

        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBackground(new Color(250, 250, 240));

        scrollPane = new JScrollPane(messagePanel);
        scrollPane.setBounds(10, 110, 560, 440);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        scenarioImageLabel = new JLabel();
        scenarioImageLabel.setBounds(650, 100, 500, 500); 
        scenarioImageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(scenarioImageLabel);

        scenarioTimer = new Timer(1000, e -> showNextMessage());

        openStoreButton.addActionListener(e -> {
            messagePanel.removeAll();
            messagePanel.revalidate();
            messagePanel.repaint();
            scenarioImageLabel.setIcon(null); 

            scenarioMessages = scenarioManager.runDayAndGetMessages();
            currentMsgIndex = 0;

            if (scenarioMessages.isEmpty()) {
                addMessage("✅ Store opened normally. No issues encountered.", "/images/default.png");
            } else {
                scenarioTimer.start();
            }
        });
    }

    private void showNextMessage() {
        if (currentMsgIndex < scenarioMessages.size()) {
            String msg = scenarioMessages.get(currentMsgIndex++);
            String iconPath = determineIcon(msg);
            addMessage(msg, iconPath);
        } else {
            scenarioTimer.stop();
        }
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

        URL resource = getClass().getResource(iconPath);
        if (resource != null) {
            ImageIcon icon = new ImageIcon(resource);
            Image scaled = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            scenarioImageLabel.setIcon(new ImageIcon(scaled));
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