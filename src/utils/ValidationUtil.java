package utils;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Validate Username
    public static boolean isValidUsername(String username) {

        return username != null
                && !username.trim().isEmpty()
                && username.length() >= 3;
    }

    // Validate Password
    public static boolean isValidPassword(String password) {

        return password != null
                && password.length() >= 6;
    }

    // Validate Email
    public static boolean isValidEmail(String email) {

        String emailRegex =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return Pattern.matches(emailRegex, email);
    }

    // Check Empty Fields
    public static boolean isEmpty(String text) {

        return text == null || text.trim().isEmpty();
    }
}