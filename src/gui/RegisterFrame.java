package gui;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {

        setTitle("Register");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JTextField username = new JTextField();
        JTextField email = new JTextField();
        JPasswordField password = new JPasswordField();

        JButton registerBtn = new JButton("Register");

        registerBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Registration Successful");
            new LoginFrame();
            dispose();
        });

        panel.add(new JLabel("Username"));
        panel.add(username);
        panel.add(email);
        panel.add(password);
        panel.add(registerBtn);

        add(panel);
        setVisible(true);
    }
}