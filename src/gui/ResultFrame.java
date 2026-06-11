package gui;

import javax.swing.*;
import java.awt.*;
import services.ScoringPersistenceRepository;

public class ResultFrame extends JFrame {
    private JLabel headingLabel;
    private JLabel scoreLabel;
    private JLabel topicLabel;
    private JButton closeButton;

    // Directly initialized for automatic background database persistence
    private final ScoringPersistenceRepository scoringPersistenceRepository = new ScoringPersistenceRepository();

    public ResultFrame(int verifiedScore, int maximumQuestions, String subjectMatterTopic) {
        // 1. Frame configurations
        setTitle("Quiz Results Summary");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 2. THE COLOR SCHEME INJECTION: Define your layout variables
        Color darkBlueBackground = new Color(22, 29, 43);   // Your core dark midnight blue color
        Color secondaryBluePanel = new Color(30, 41, 59);   // Slightly lighter blue accent panel
        Color vibrantTextWhite = new Color(248, 250, 252);  // Clean, soft off-white text color
        Color numericHighlight = new Color(56, 189, 248);   // Soft cyan/sky blue highlight hex accent

        // Configure main frame content canvas container
        Container canvas = getContentPane();
        canvas.setLayout(new BorderLayout(15, 15));
        canvas.setBackground(darkBlueBackground);

        // 3. Wrap incoming data fields inside our domain tracking object
        models.Result processedDiagnosticReport = new models.Result(verifiedScore, maximumQuestions, subjectMatterTopic);

        // 4. Build custom styled Evaluation Info panel center block
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(secondaryBluePanel);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        headingLabel = new JLabel("--- Evaluation Complete ---", SwingConstants.CENTER);
        headingLabel.setForeground(numericHighlight);
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

        topicLabel = new JLabel("Topic: " + subjectMatterTopic, SwingConstants.CENTER);
        topicLabel.setForeground(vibrantTextWhite);
        topicLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        scoreLabel = new JLabel("Your Score: " + verifiedScore + " / " + maximumQuestions, SwingConstants.CENTER);
        scoreLabel.setForeground(vibrantTextWhite);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        centerPanel.add(headingLabel);
        centerPanel.add(topicLabel);
        centerPanel.add(scoreLabel);

        // Offset padding margin adjustments
        JPanel paddingContainer = new JPanel(new BorderLayout());
        paddingContainer.setBackground(darkBlueBackground);
        paddingContainer.setBorder(BorderFactory.createEmptyBorder(15, 25, 5, 25));
        paddingContainer.add(centerPanel, BorderLayout.CENTER);
        add(paddingContainer, BorderLayout.CENTER);

        // 5. Build and style the control button footer component
        closeButton = new JButton("Close Application");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 13));
        closeButton.setForeground(vibrantTextWhite);
        closeButton.setBackground(new Color(239, 68, 68)); // Distinct crimson red dismiss button color
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));

        closeButton.addActionListener(e -> this.dispose());

        JPanel buttonWrapper = new JPanel(new BorderLayout());
        buttonWrapper.setBackground(darkBlueBackground);
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(0, 25, 20, 25));
        buttonWrapper.add(closeButton, BorderLayout.CENTER);
        add(buttonWrapper, BorderLayout.SOUTH);

        // Asynchronous background metric streaming thread execution
        scoringPersistenceRepository.persistScorecardAsync("SYSTEM_DEFAULT_USER_GUID", processedDiagnosticReport)
                .thenAccept(status -> {
                    if (!status) {
                        System.err.println("Database sync deferred (Using local memory backup mode).");
                    } else {
                        System.out.println(">>> UI Update: Result synced cleanly to MySQL server instance! <<<");
                    }
                });
    }
}