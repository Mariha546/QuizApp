package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Theme {
    // Premium AI / Cyber Gaming Theme Palette
    public static final Color BACKGROUND = new Color(24, 20, 36);      // Deep Purple-Black
    public static final Color SURFACE = new Color(36, 32, 56);         // Medium Tech Purple
    public static final Color ACCENT_CYAN = new Color(0, 229, 255);    // Neon Blue Accent
    public static final Color ACCENT_PURPLE = new Color(157, 78, 221);  // Electric Purple
    public static final Color TEXT_PRIMARY = new Color(255, 255, 255); // Crisp White
    public static final Color TEXT_MUTED = new Color(180, 174, 201);   // Lavender Gray

    public static void applyTheme(JFrame frame) {
        frame.getContentPane().setBackground(BACKGROUND);
    }

    public static void styleButton(JButton button, Color bg, Color fg) {
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bg.brighter(), 1),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void styleTextField(JTextField field) {
        field.setBackground(SURFACE);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT_CYAN);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_PURPLE, 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
    }

    public static void styleLabel(JLabel label, Color color, Font font) {
        label.setForeground(color);
        label.setFont(font);
    }
}