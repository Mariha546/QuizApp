package gui;

import models.Quiz;
import services.AiGenerationEngine;
import javax.swing.*;
import java.awt.*;

public class AdaptiveTopicSelectionFrame extends JFrame {
    public AdaptiveTopicSelectionFrame() {
        setTitle("AI Quiz Configuration");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        Theme.applyTheme(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(12, 12, 12, 12);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Title Banner
        JLabel titleLabel = new JLabel("Custom AI Quiz Generator", JLabel.CENTER);
        Theme.styleLabel(titleLabel, Theme.ACCENT_CYAN, new Font("Segoe UI", Font.BOLD, 18));
        constraints.gridx = 0; constraints.gridy = 0; constraints.gridwidth = 2;
        add(titleLabel, constraints);

        constraints.gridwidth = 1;

        // Topic Selection Input
        JLabel topicLabel = new JLabel("Enter Topic / Subject:");
        Theme.styleLabel(topicLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        constraints.gridx = 0; constraints.gridy = 1;
        add(topicLabel, constraints);

        JTextField topicInputField = new JTextField(18);
        Theme.styleTextField(topicInputField);
        constraints.gridx = 1;
        add(topicInputField, constraints);

        // Difficulty Selection Dropdown
        JLabel difficultyLabel = new JLabel("Select Difficulty Level:");
        Theme.styleLabel(difficultyLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        constraints.gridx = 0; constraints.gridy = 2;
        add(difficultyLabel, constraints);

        String[] difficultyLevels = {"Beginner", "Intermediate", "Advanced"};
        JComboBox<String> difficultyDropdown = new JComboBox<>(difficultyLevels);
        difficultyDropdown.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        difficultyDropdown.setBackground(Theme.SURFACE);
        difficultyDropdown.setForeground(Theme.TEXT_PRIMARY);
        constraints.gridx = 1;
        add(difficultyDropdown, constraints);

        // Action Buttons Panel
        JButton generateButton = new JButton("Generate Quiz Live");
        Theme.styleButton(generateButton, Theme.ACCENT_CYAN, Theme.BACKGROUND);

        JButton cancelButton = new JButton("Back to Dashboard");
        Theme.styleButton(cancelButton, Theme.SURFACE, Theme.TEXT_PRIMARY);

        constraints.gridx = 0; constraints.gridy = 3;
        add(generateButton, constraints);

        constraints.gridx = 1;
        add(cancelButton, constraints);

        // Loading Progress Indicator
        JLabel loadingIndicatorLabel = new JLabel(" ", JLabel.CENTER);
        Theme.styleLabel(loadingIndicatorLabel, Theme.ACCENT_PURPLE, new Font("Segoe UI", Font.BOLD, 13));
        constraints.gridx = 0; constraints.gridy = 4; constraints.gridwidth = 2;
        add(loadingIndicatorLabel, constraints);

        // LOGIC: Fire API and launch QuizFrame asynchronously
        generateButton.addActionListener(click -> {
            String typedTopic = topicInputField.getText().trim();
            String chosenDifficulty = (String) difficultyDropdown.getSelectedItem();

            if (typedTopic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please write a topic name first!", "Missing Data", JOptionPane.WARNING_MESSAGE);
                return;
            }

            generateButton.setEnabled(false);
            cancelButton.setEnabled(false);
            loadingIndicatorLabel.setText("🤖 AI is formulating customized questions... Please wait...");

            AiGenerationEngine.getInstance()
                    .generateQuizAsync(typedTopic, chosenDifficulty)
                    .thenAccept(generatedQuiz -> SwingUtilities.invokeLater(() -> {
                        new QuizFrame(generatedQuiz).setVisible(true);
                        this.dispose();
                    }));
        });

        cancelButton.addActionListener(click -> {
            new DashboardFrame().setVisible(true);
            this.dispose();
        });
    }
}