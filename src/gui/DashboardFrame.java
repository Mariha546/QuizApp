package gui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));

        JButton startQuiz = new JButton("Start Quiz");
        JButton exit = new JButton("Exit");

        startQuiz.addActionListener(e -> {
            new TopicSelectionFrame();
            dispose();
        });

        exit.addActionListener(e -> System.exit(0));

        panel.add(startQuiz);
        panel.add(exit);

        add(panel);
        setVisible(true);
    }
}