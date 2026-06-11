package models;

import java.util.ArrayList;
import java.util.List;

public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion() {
        super();
    }

    public TrueFalseQuestion(String id, String questionText, String correctAnswer) {
        super(id, questionText, correctAnswer);
    }

    @Override
    public List<String> getOptions() {
        List<String> tfOptions = new ArrayList<>();
        tfOptions.add("True");
        tfOptions.add("False");
        return tfOptions;
    }
}