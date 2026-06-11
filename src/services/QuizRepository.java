package services;

import models.Quiz;
import models.Question;
import utils.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuizRepository {

    public void autoSaveQuiz(Quiz quiz) {
        String insertQuizSQL = "INSERT INTO quizzes (id, topic) VALUES (?, ?)";
        String insertQuestionSQL = "INSERT INTO questions (id, quiz_id, question_text, correct_answer) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectionPool.getConnection()) {
            conn.setAutoCommit(false);

            // 1. Insert into quizzes table
            try (PreparedStatement quizStmt = conn.prepareStatement(insertQuizSQL)) {
                quizStmt.setString(1, quiz.getId());
                quizStmt.setString(2, quiz.getTopic());
                quizStmt.executeUpdate();
            }

            // 2. Insert into questions table
            try (PreparedStatement qStmt = conn.prepareStatement(insertQuestionSQL)) {
                if (quiz.getQuestions() != null) {
                    for (Question question : quiz.getQuestions()) {
                        qStmt.setString(1, question.getId());
                        qStmt.setString(2, quiz.getId());
                        qStmt.setString(3, question.getQuestionText());
                        qStmt.setString(4, question.getCorrectAnswer());
                        qStmt.addBatch();
                    }
                    qStmt.executeBatch();
                }
            }

            conn.commit();
            System.out.println(">>> AUTOMATION SUCCESS: Database entries pushed to MySQL! <<<");

        } catch (SQLException e) {
            System.err.println(">>> AUTOMATION ERROR: Transaction failure. <<<");
            e.printStackTrace();
        }
    }
}