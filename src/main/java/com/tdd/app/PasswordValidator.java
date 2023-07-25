package com.tdd.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PasswordValidator {

    public static class PasswordValidationResult {
        boolean validationResult;
        Optional<String> errorMessage;

        public PasswordValidationResult(boolean validationResult) {
            this.validationResult = validationResult;
            this.errorMessage = Optional.empty();
        }

        public PasswordValidationResult(boolean validationResult, String errorMessage) {
            this.validationResult = validationResult;
            this.errorMessage = Optional.of(errorMessage);
        }
    }

    public static PasswordValidationResult validatePassword(String password) {
        List<String> errorMessages = new ArrayList<>();

        if (password.length() < 8) {
            errorMessages.add("Password must be at least 8 characters");
        }

        long numberOfNumericChars = password.chars()
                .filter(Character::isDigit)
                .count();

        if (numberOfNumericChars < 2) {
            errorMessages.add("The password must contain at least 2 numbers");
        }

        long numberOfCapitalChars = password.chars()
                .filter(Character::isUpperCase)
                .count();

        if (numberOfCapitalChars < 1) {
            errorMessages.add("The password must contain at least 1 capital letter");
        }

        long numberOfSpecialChars = password.chars()
                .filter(c -> !Character.isLetterOrDigit(c))
                .count();

        if (numberOfSpecialChars < 1) {
            errorMessages.add("The password must contain at least 1 special character");
        }

        if (!errorMessages.isEmpty()) {
            return new PasswordValidationResult(false, String.join("\n", errorMessages));
        }

        return new PasswordValidationResult(true);
    }

}
