package models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String id;
    private String questionText;
    private String correctAnswer;
    private List<String> options;

    public Question() {
        this.options = new ArrayList<>();
    }

    public Question(String id, String questionText, String correctAnswer) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = new ArrayList<>();
    }

    public List<String> getOptions() {
        if (this.options != null && !this.options.isEmpty()) {
            return this.options;
        }
        List<String> defaultFallback = new ArrayList<>();
        defaultFallback.add(this.correctAnswer != null ? this.correctAnswer : "Option A");
        defaultFallback.add("Option B");
        defaultFallback.add("Option C");
        defaultFallback.add("Option D");
        return defaultFallback;
    }

    public boolean isCorrect(String userAnswer) {
        if (this.correctAnswer == null || userAnswer == null) return false;
        return this.correctAnswer.trim().equalsIgnoreCase(userAnswer.trim());
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public void setOptions(List<String> options) { this.options = options; }
}