package services;

import models.Result;

public class EvaluationService {
    public static Result evaluate(int score, int total, String topic) {
        String feedback = RecommendationService.generateFeedback(score, total, topic);
        return new Result(score, total, feedback);
    }
}