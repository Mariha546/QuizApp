package services;

import utils.DatabaseConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ScoringPersistenceRepository {

    /**
     * Matches the asynchronous signature requested by ResultFrame line 58.
     * Automatically saves a score to MySQL in the background.
     */
    public CompletableFuture<Boolean> persistScorecardAsync(String userId, models.Result processedDiagnosticReport) {
        return CompletableFuture.supplyAsync(() -> {
            String insertSQL = "INSERT INTO quiz_results (id, user_id, score, total_questions, topic) VALUES (?, ?, ?, ?, ?)";

            try (Connection conn = DatabaseConnectionPool.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

                // Map the required inputs directly to our database values
                stmt.setString(1, UUID.randomUUID().toString());
                stmt.setString(2, userId);
                stmt.setInt(3, processedDiagnosticReport.getVerifiedScore());
                stmt.setInt(4, processedDiagnosticReport.getMaximumQuestions());
                stmt.setString(5, processedDiagnosticReport.getSubjectMatterTopic());

                stmt.executeUpdate();
                System.out.println(">>> AUTOMATION SUCCESS: User scorecard successfully pushed to MySQL! <<<");
                return true; // Returns true to trigger .thenAccept(status -> ...) in your GUI

            } catch (SQLException e) {
                System.err.println(">>> AUTOMATION ERROR: Scorecard streaming transaction dropped. <<<");
                e.printStackTrace();
                return false;
            }
        });
    }
}