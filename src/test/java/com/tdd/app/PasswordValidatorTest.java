package com.tdd.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tdd.app.PasswordValidator.PasswordValidationResult;

import static com.tdd.app.PasswordValidator.validatePassword;

public class PasswordValidatorTest {
    @Test
    public void givenPasswordLessThan8CharsLong_shouldReturnError() {
        PasswordValidationResult result = validatePassword("J@va123");
        assertEquals(false, result.validationResult);
        assertEquals(result.errorMessage.get(), "Password must be at least 8 characters");
    }

    @Test
    public void givenPasswordHasLessThan2Numbers_shouldReturnError() {
        PasswordValidationResult result = validatePassword("J@vajava1");
        assertEquals(false, result.validationResult);
        assertEquals("The password must contain at least 2 numbers", result.errorMessage.get());
    }

    @Test
    public void givenMultipleErrors_shouldReturnErrorForBoth() {
        PasswordValidationResult result = validatePassword("J@va1");
        assertEquals(result.validationResult, false);
        assertEquals("Password must be at least 8 characters\n" +
                "The password must contain at least 2 numbers",
                result.errorMessage.get());
    }

    @Test
    public void givenPasswordHasNoCapitals_shouldReturnError() {
        PasswordValidationResult result = validatePassword("j@vajava123");
        assertEquals(false, result.validationResult);
        assertEquals("The password must contain at least 1 capital letter", result.errorMessage.get());
    }

    @Test
    public void givenPasswordHasNoSpecialCharacters_shouldReturnError() {
        PasswordValidationResult result = validatePassword("Javajava123");
        assertEquals(false, result.validationResult);
        assertEquals("The password must contain at least 1 special character", result.errorMessage.get());
    }

}
