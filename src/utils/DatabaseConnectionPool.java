package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionPool {
    private static HikariDataSource dataSource;

    public static void initialize() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();

            // Connect to local MySQL instance on port 3306 and target 'quiz_db'
            config.setJdbcUrl("jdbc:mysql://localhost:3306/quiz_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
            config.setUsername("root");
            config.setPassword("mySQL!18");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            // Pool Optimization Settings
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setConnectionTimeout(20000);

            dataSource = new HikariDataSource(config);
            System.out.println(">>> MySQL Database Connection Pool initialized successfully! <<<");
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Database Connection Pool has not been initialized!");
        }
        return dataSource.getConnection();
    }

    public static void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            System.out.println(">>> MySQL Database Connection Pool cleanly shut down. <<<");
        }
    }
}