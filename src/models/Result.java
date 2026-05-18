package models;

public class Result {

    private String username;
    private int score;
    private int totalQuestions;
    private long timeTaken;

    public Result(String username, int score, int totalQuestions, long timeTaken) {
        this.username = username;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.timeTaken = timeTaken;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public double getPercentage() {
        return (double) score / totalQuestions * 100;
    }
}