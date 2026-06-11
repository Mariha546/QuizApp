package gui;

import javax.swing.*;
import java.awt.*;

public class TopicSelectionFrame extends JFrame {
    public TopicSelectionFrame() {
        setTitle("Choose Your Quiz");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        Theme.applyTheme(this);

        JLabel promptLabel = new JLabel("Select the topic you want to practice:");
        Theme.styleLabel(promptLabel, Theme.TEXT_PRIMARY, new Font("Segoe UI", Font.PLAIN, 15));
        add(promptLabel);

        String[] frameworkThemesList = {"Java Polymorphism Concepts", "General Coding Basics", "Object-Oriented Programming"};
        JComboBox<String> dropdownSelectionMenu = new JComboBox<>(frameworkThemesList);
        dropdownSelectionMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dropdownSelectionMenu.setBackground(Theme.SURFACE);
        dropdownSelectionMenu.setForeground(Theme.TEXT_PRIMARY);
        add(dropdownSelectionMenu);

        JButton loadLegacyEvaluationButton = new JButton("Start Standard Quiz");
        Theme.styleButton(loadLegacyEvaluationButton, Theme.SURFACE, Theme.TEXT_PRIMARY);
        add(loadLegacyEvaluationButton);

        JButton loadAsyncAiEngineLaunchWindowButton = new JButton("Start AI Generated Quiz");
        Theme.styleButton(loadAsyncAiEngineLaunchWindowButton, Theme.ACCENT_CYAN, Theme.BACKGROUND);
        add(loadAsyncAiEngineLaunchWindowButton);

        loadLegacyEvaluationButton.addActionListener(action -> {
            new QuizFrame((String) dropdownSelectionMenu.getSelectedItem()).setVisible(true);
            this.dispose();
        });

        loadAsyncAiEngineLaunchWindowButton.addActionListener(action -> {
            new AdaptiveTopicSelectionFrame().setVisible(true);
            this.dispose();
        });
    }
}