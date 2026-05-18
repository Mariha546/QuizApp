package models;

public class MCQQuestion extends Question {

    private String[] options;

    public MCQQuestion(String questionText,
                       String correctAnswer,
                       String[] options) {

        super(questionText, correctAnswer);
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}