package gui;

import javax.swing.*;
import java.awt.*;

public class TopicSelectionFrame extends JFrame {

    public TopicSelectionFrame() {

        setTitle("Select Topic");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        String[] topics = {"Java", "OOP", "Math"};

        JComboBox<String> comboBox = new JComboBox<>(topics);

        JButton start = new JButton("Start Quiz");

        start.addActionListener(e -> {
            String topic = comboBox.getSelectedItem().toString();
            new QuizFrame(topic);
            dispose();
        });

        panel.add(new JLabel("Choose Topic"));
        panel.add(comboBox);
        panel.add(start);

        add(panel);
        setVisible(true);
    }
}