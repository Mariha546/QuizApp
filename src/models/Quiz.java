package models;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private String topic;
    private List<Question> questions;
    private int score;
    private int timeLimit;

    // Default Constructor
    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    // Main Constructor
    public Quiz(String topic, List<Question> questions, int timeLimit) {
        this.topic = topic;
        this.questions = questions;
        this.timeLimit = timeLimit;
        this.score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public String getTopic() {
        return topic;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}