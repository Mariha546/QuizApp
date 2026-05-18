package services;

import java.util.ArrayList;
import java.util.List;

public class RecommendationService {

    public List<String> recommendTopics(int score, int total) {

        List<String> recommendations = new ArrayList<>();

        double percentage = (double) score / total * 100;

        if (percentage < 40) {
            recommendations.add("Revise Basics");
            recommendations.add("Start with Easy MCQs");
        }
        else if (percentage < 70) {
            recommendations.add("Practice Mixed Questions");
            recommendations.add("Focus on Weak Areas");
        }
        else {
            recommendations.add("Try Advanced Quizzes");
            recommendations.add("Attempt Timed Challenges");
        }

        return recommendations;
    }
}