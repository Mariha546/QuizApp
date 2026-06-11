package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    public DashboardFrame() {
        setTitle("Student Dashboard");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        Theme.applyTheme(this);

        // Frame Padding
        getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel dashboardBanner = new JLabel("Welcome to Your Quiz App", JLabel.CENTER);
        Theme.styleLabel(dashboardBanner, Theme.TEXT_PRIMARY, new Font("Segoe UI", Font.BOLD, 22));
        add(dashboardBanner, BorderLayout.NORTH);

        JPanel contentControlLayout = new JPanel(new GridLayout(2, 1, 15, 15));
        contentControlLayout.setBackground(Theme.BACKGROUND);

        JLabel statusLabel = new JLabel("Status: Ready to Start", JLabel.CENTER);
        Theme.styleLabel(statusLabel, Theme.ACCENT_CYAN, new Font("Segoe UI", Font.PLAIN, 15));
        contentControlLayout.add(statusLabel);

        JButton proceedToQuizCreationButton = new JButton("Start AI Based Quiz");
        Theme.styleButton(proceedToQuizCreationButton, Theme.ACCENT_PURPLE, Theme.TEXT_PRIMARY);
        proceedToQuizCreationButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        contentControlLayout.add(proceedToQuizCreationButton);

        add(contentControlLayout, BorderLayout.CENTER);

        proceedToQuizCreationButton.addActionListener(clickEvent -> {
            new AdaptiveTopicSelectionFrame().setVisible(true);
            this.dispose();
        });
    }
}