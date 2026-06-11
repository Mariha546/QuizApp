package gui;

import utils.Constants;
import utils.ValidationUtil;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle(Constants.APPLICATION_NAME + " - Identity Authorization Gateway");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        Theme.applyTheme(this);

        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.insets = new Insets(10, 10, 10, 10);
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleBanner = new JLabel("Secure Login System Portal", JLabel.CENTER);
        Theme.styleLabel(titleBanner, Theme.ACCENT_CYAN, new Font("Segoe UI", Font.BOLD, 18));
        layoutConstraints.gridx = 0; layoutConstraints.gridy = 0; layoutConstraints.gridwidth = 2;
        add(titleBanner, layoutConstraints);

        layoutConstraints.gridwidth = 1;

        JLabel emailLabel = new JLabel("Authorized Email Address:");
        Theme.styleLabel(emailLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        layoutConstraints.gridy = 1; layoutConstraints.gridx = 0; add(emailLabel, layoutConstraints);

        JTextField emailInput = new JTextField(16);
        Theme.styleTextField(emailInput);
        layoutConstraints.gridx = 1; add(emailInput, layoutConstraints);

        JLabel passLabel = new JLabel("Security Password:");
        Theme.styleLabel(passLabel, Theme.TEXT_MUTED, new Font("Segoe UI", Font.PLAIN, 13));
        layoutConstraints.gridx = 0; layoutConstraints.gridy = 2; add(passLabel, layoutConstraints);

        JPasswordField passwordInput = new JPasswordField(16);
        Theme.styleTextField(passwordInput);
        layoutConstraints.gridx = 1; add(passwordInput, layoutConstraints);

        JButton executeLoginButton = new JButton("Authenticate Session");
        Theme.styleButton(executeLoginButton, Theme.ACCENT_CYAN, Theme.BACKGROUND);
        layoutConstraints.gridx = 0; layoutConstraints.gridy = 3; add(executeLoginButton, layoutConstraints);

        JButton loadRegisterButton = new JButton("Register Account");
        Theme.styleButton(loadRegisterButton, Theme.SURFACE, Theme.TEXT_PRIMARY);
        layoutConstraints.gridx = 1; add(loadRegisterButton, layoutConstraints);

        executeLoginButton.addActionListener(event -> {
            if (!ValidationUtil.isValidEmail(emailInput.getText())) {
                JOptionPane.showMessageDialog(this, "Formatting validation mismatched. Provide standard structural emails.");
                return;
            }
            new DashboardFrame().setVisible(true);
            this.dispose();
        });

        loadRegisterButton.addActionListener(event -> {
            new RegisterFrame().setVisible(true);
            this.dispose();
        });
    }
}