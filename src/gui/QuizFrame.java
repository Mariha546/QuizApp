package gui;

import models.Quiz;
import models.Question;
import services.QuizGenerator;
import services.TimerService;
import utils.Constants;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizFrame extends JFrame {
    private final Quiz currentQuiz;
    private final List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int userScore = 0;

    private final JLabel questionNumberLabel;
    private final JTextArea questionTextArea;
    private final JPanel optionsPanel;
    private final ButtonGroup optionsGroup;
    private final JLabel timeLabel;
    private final JButton nextButton;
    private final TimerService timerService;

    public QuizFrame(String chosenTopicName) {
        this(QuizGenerator.generateFallbackQuiz(chosenTopicName));
    }

    public QuizFrame(Quiz quizObject) {
        this.currentQuiz = quizObject;
        this.questionList = quizObject.getQuestions();

        setTitle("Active Quiz Session");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));
        Theme.applyTheme(this);

        // Header panel setup
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(Theme.BACKGROUND);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 20));

        questionNumberLabel = new JLabel("Question 1 of " + questionList.size());
        Theme.styleLabel(questionNumberLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.BOLD, 14));
        headerPanel.add(questionNumberLabel, BorderLayout.WEST);

        timeLabel = new JLabel("Time Remaining: " + Constants.QUESTION_COUNTDOWN_TIME_SECONDS + "s", JLabel.RIGHT);
        Theme.styleLabel(timeLabel, new Color(255, 85, 85), new Font("Segoe UI", Font.BOLD, 14));
        headerPanel.add(timeLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Main Body Panel
        JPanel bodyPanel = new JPanel(new BorderLayout(15, 15));
        bodyPanel.setBackground(Theme.BACKGROUND);
        bodyPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        questionTextArea = new JTextArea(4, 40);
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        // FIXED: Question is now styled as distinctly bold and crisp white
        questionTextArea.setFont(new Font("Segoe UI", Font.BOLD, 17));
        questionTextArea.setForeground(Theme.TEXT_PRIMARY);
        questionTextArea.setBackground(Theme.SURFACE);
        questionTextArea.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        bodyPanel.add(questionTextArea, BorderLayout.NORTH);

        // Sub-panel for option buttons
        optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(Theme.BACKGROUND);
        optionsGroup = new ButtonGroup();
        bodyPanel.add(optionsPanel, BorderLayout.CENTER);
        add(bodyPanel, BorderLayout.CENTER);

        // Footer layout
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Theme.BACKGROUND);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 20));

        nextButton = new JButton("Next Question");
        Theme.styleButton(nextButton, Theme.ACCENT_CYAN, Theme.BACKGROUND);
        footerPanel.add(nextButton);
        add(footerPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(click -> handleNextQuestionAction());

        this.timerService = new TimerService(Constants.QUESTION_COUNTDOWN_TIME_SECONDS, timeLabel, () -> handleQuizTimeout());
        this.timerService.start();

        displayQuestion(currentQuestionIndex);
    }

    private void displayQuestion(int index) {
        optionsPanel.removeAll();
        optionsGroup.clearSelection();

        if (index < questionList.size()) {
            Question question = questionList.get(index);
            questionNumberLabel.setText("Question " + (index + 1) + " of " + questionList.size());
            questionTextArea.setText(question.getQuestionText());

            for (String optionText : question.getOptions()) {
                JRadioButton optionButton = new JRadioButton(optionText);
                optionButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                optionButton.setForeground(Theme.TEXT_PRIMARY);
                optionButton.setBackground(Theme.SURFACE);
                optionButton.setFocusPainted(false);
                optionButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                optionButton.setActionCommand(optionText);
                optionsGroup.add(optionButton);
                optionsPanel.add(optionButton);
            }

            optionsPanel.revalidate();
            optionsPanel.repaint();

            if (index == questionList.size() - 1) {
                nextButton.setText("Submit Quiz");
                Theme.styleButton(nextButton, Theme.ACCENT_PURPLE, Theme.TEXT_PRIMARY);
            }
        }
    }

    private void handleNextQuestionAction() {
        ButtonModel selectedRadioButtonModel = optionsGroup.getSelection();

        if (selectedRadioButtonModel == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer before moving forward.", "No Answer Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String userSelectedAnswer = selectedRadioButtonModel.getActionCommand();
        if (questionList.get(currentQuestionIndex).isCorrect(userSelectedAnswer)) {
            userScore++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questionList.size()) {
            displayQuestion(currentQuestionIndex);
        } else {
            finalizeAndExitToResults();
        }
    }

    private void handleQuizTimeout() {
        timerService.stop();
        JOptionPane.showMessageDialog(this, "Time is up! Let's see your results.", "Time Expired", JOptionPane.INFORMATION_MESSAGE);
        finalizeAndExitToResults();
    }

    private void finalizeAndExitToResults() {
        timerService.stop();
        new ResultFrame(userScore, questionList.size(), currentQuiz.getTopic()).setVisible(true);
        this.dispose();
    }
}