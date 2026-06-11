package main;

import gui.LoginFrame;
import utils.DatabaseConnectionPool;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize your Database Connection Pool
        DatabaseConnectionPool.initialize();

        // 3. Launch your UI components on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginWindow = new LoginFrame();

            // 2. ALTERNATIVE COLOR FIX: Try passing your window component
            // directly to your theme if it expects an argument!
            try {
                gui.Theme.applyTheme(loginWindow);
            } catch (Exception e) {
                System.out.println(">>> Note: Theme parameter signature requires matching window context. <<<");
            }

            loginWindow.setVisible(true);
        });

        // 4. Register database shutdown safety hook
        Runtime.getRuntime().addShutdownHook(new Thread(DatabaseConnectionPool::shutdown));
    }
}