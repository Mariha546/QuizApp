package utils;

public class ValidationUtil {
    public static boolean isValidEmail(String sourceEmail) {
        if (sourceEmail == null) return false;
        return sourceEmail.contains("@") && sourceEmail.contains(".");
    }

    public static boolean isStringBlank(String parameterStr) {
        return parameterStr == null || parameterStr.strip().isEmpty();
    }
}