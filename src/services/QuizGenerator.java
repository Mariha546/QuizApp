package services;

import java.util.ArrayList;
import java.util.List;

import models.*;
import utils.Constants;

public class QuizGenerator {

    public Quiz generateQuiz(String topic) {

        List<Question> questions = new ArrayList<>();

        // Dummy Questions
        for (int i = 1; i <= Constants.TOTAL_QUESTIONS; i++) {

            String[] options = {
                    "Option A",
                    "Option B",
                    "Option C",
                    "Option D"
            };

            questions.add(new MCQQuestion(
                    "Sample question " + i + " on " + topic,
                    "Option A",
                    options
            ));
        }

        return new Quiz(topic, questions, Constants.QUIZ_TIME);
    }

    public List<Question> filterByDifficulty(List<Question> all, String level) {
        return all;
    }
}