package models;

import java.util.List;

public class MCQQuestion extends Question {
    private List<String> options;

    public MCQQuestion() {
        super();
    }

    public MCQQuestion(String id, String questionText, List<String> options, String correctAnswer) {
        super(id, questionText, correctAnswer);
        this.options = options;
    }

    @Override
    public List<String> getOptions() {
        if (this.options != null && !this.options.isEmpty()) {
            return this.options;
        }
        return super.getOptions();
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}