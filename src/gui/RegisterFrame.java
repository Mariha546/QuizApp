package gui;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Identity Onboarding Portal");
        setSize(450, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 15, 15));
        Theme.applyTheme(this);

        // Add framing padding
        getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel(" Full Name:");
        Theme.styleLabel(nameLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        add(nameLabel);
        JTextField entryNameField = new JTextField();
        Theme.styleTextField(entryNameField);
        add(entryNameField);

        JLabel emailLabel = new JLabel(" Primary Email:");
        Theme.styleLabel(emailLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        add(emailLabel);
        JTextField entryEmailField = new JTextField();
        Theme.styleTextField(entryEmailField);
        add(entryEmailField);

        JLabel passLabel = new JLabel(" Vault Password:");
        Theme.styleLabel(passLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        add(passLabel);
        JPasswordField vaultPasswordField = new JPasswordField();
        Theme.styleTextField(vaultPasswordField);
        add(vaultPasswordField);

        JButton dispatchRegistrationButton = new JButton("Register Profile");
        Theme.styleButton(dispatchRegistrationButton, Theme.ACCENT_CYAN, Theme.BACKGROUND);

        JButton directBackButton = new JButton("Cancel Operations");
        Theme.styleButton(directBackButton, Theme.SURFACE, Theme.TEXT_PRIMARY);

        add(dispatchRegistrationButton);
        add(directBackButton);

        dispatchRegistrationButton.addActionListener(evt -> {
            JOptionPane.showMessageDialog(this, "Profile Registration Database Commit Successful.");
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        directBackButton.addActionListener(evt -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
    }
}