package gui;

import models.MCQQuestion;
import models.Question;
import models.Quiz;
import services.EvaluationService;
import services.QuizGenerator;

import javax.swing.*;
import java.awt.*;

public class QuizFrame extends JFrame {

    private Quiz quiz;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private int currentIndex = 0;

    public QuizFrame(String topic) {

        // Create object because generateQuiz is non-static
        QuizGenerator generator = new QuizGenerator();
        quiz = generator.generateQuiz(topic);

        setTitle("Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        optionButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();

            int index = i;

            optionButtons[i].addActionListener(e -> checkAnswer(index));

            optionsPanel.add(optionButtons[i]);
        }

        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(optionsPanel, BorderLayout.CENTER);

        add(panel);

        loadQuestion();

        setVisible(true);
    }

    private void loadQuestion() {

        if (currentIndex >= quiz.getQuestions().size()) {

            EvaluationService evaluationService =
                    new EvaluationService();

            int score = evaluationService.evaluateQuiz(quiz);

            new ResultFrame(score);
            dispose();
            return;
        }

        Question q = quiz.getQuestions().get(currentIndex);

        questionLabel.setText(q.getQuestionText());

        if (q instanceof MCQQuestion) {

            MCQQuestion mcq = (MCQQuestion) q;
            String[] options = mcq.getOptions();

            for (int i = 0; i < options.length; i++) {
                optionButtons[i].setText(options[i]);
            }
        }
    }

    private void checkAnswer(int optionIndex) {

        Question q = quiz.getQuestions().get(currentIndex);

        if (q instanceof MCQQuestion) {

            MCQQuestion mcq = (MCQQuestion) q;

            String selectedAnswer =
                    mcq.getOptions()[optionIndex];

            if (q.checkAnswer(selectedAnswer)) {
                quiz.incrementScore();
            }
        }

        currentIndex++;
        loadQuestion();
    }
}