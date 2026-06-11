package models;

public class Result {
    private int verifiedScore;
    private int maximumQuestions;
    private String subjectMatterTopic;

    public Result() {}

    public Result(int verifiedScore, int maximumQuestions, String subjectMatterTopic) {
        this.verifiedScore = verifiedScore;
        this.maximumQuestions = maximumQuestions;
        this.subjectMatterTopic = subjectMatterTopic;
    }

    public int getVerifiedScore() {
        return verifiedScore;
    }

    public void setVerifiedScore(int verifiedScore) {
        this.verifiedScore = verifiedScore;
    }

    public int getMaximumQuestions() {
        return maximumQuestions;
    }

    public void setMaximumQuestions(int maximumQuestions) {
        this.maximumQuestions = maximumQuestions;
    }

    public String getSubjectMatterTopic() {
        return subjectMatterTopic;
    }

    public void setSubjectMatterTopic(String subjectMatterTopic) {
        this.subjectMatterTopic = subjectMatterTopic;
    }
}