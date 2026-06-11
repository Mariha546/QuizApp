package models;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private String id;
    private String topic;
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public Quiz(String id, String topic, List<Question> questions) {
        this.id = id;
        this.topic = topic;
        this.questions = questions != null ? questions : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}