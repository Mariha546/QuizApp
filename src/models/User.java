package models;

public class User {

    private String username;
    private String password;
    private int totalScore;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.totalScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addScore(int score) {
        this.totalScore += score;
    }
}