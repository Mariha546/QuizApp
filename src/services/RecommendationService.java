package services;

public class RecommendationService {

    public String getRecommendation(int score) {

        if (score >= 8) {
            return "Excellent Performance!";
        }
        else if (score >= 5) {
            return "Good Job!";
        }
        else {
            return "Keep Practicing!";
        }
    }
}