package services;

public class RecommendationService {
    public static String generateFeedback(int score, int total, String topic) {
        double percentage = total > 0 ? ((double) score / total) * 100 : 0;

        if (percentage >= 80) {
            return "Great job! You have an excellent understanding of " + topic + ". Keep up the wonderful work!";
        } else if (percentage >= 50) {
            return "Good effort! You know the basics of " + topic + ", but it would be a good idea to review your mistakes and practice a bit more.";
        } else {
            return "Don't worry! Everyone starts somewhere. We recommend reading through the basic lessons on " + topic + " and trying the quiz again.";
        }
    }
}