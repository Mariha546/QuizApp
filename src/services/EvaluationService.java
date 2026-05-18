package services;

import models.Quiz;

public class EvaluationService {

    public int evaluateQuiz(Quiz quiz) {
        return quiz.getScore();
    }
}