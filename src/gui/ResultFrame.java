package gui;

import services.RecommendationService;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {

    public ResultFrame(int score) {

        setTitle("Result");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JLabel scoreLabel = new JLabel(
                "Your Score: " + score,
                SwingConstants.CENTER
        );

        JLabel recommendation = new JLabel(
                RecommendationService.getRecommendation(score),
                SwingConstants.CENTER
        );

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));

        panel.add(scoreLabel);
        panel.add(recommendation);
        panel.add(exit);

        add(panel);
        setVisible(true);
    }
}