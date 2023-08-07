package com.tdd.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDate;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class BankAccountTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    LocalDate localDateMock;

    @Test
    public void shouldPrintAccurateStatementForSingleDeposit() {
        BankAccount bankAccount = new BankAccount();

        LocalDate specifiedDate = LocalDate.of(2000, 1, 1);
        try (var mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(specifiedDate);
            bankAccount.deposit(100);
        }

        bankAccount.printStatement();

        String expectedOutput = "DATE | AMOUNT | BALANCE\n" +
                "01/01/2000 | $100.00 | $100.00";
        assertEquals(expectedOutput, systemOutRule.getLog().trim());
    }

    @Test
    public void shouldPrintAccurateStatementForMultipleDeposits() {
        BankAccount bankAccount = new BankAccount();

        LocalDate specifiedDate = LocalDate.of(2000, 1, 1);
        try (var mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(specifiedDate);
            bankAccount.deposit(50);
        }

        specifiedDate = LocalDate.of(2000, 1, 2);
        try (var mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(specifiedDate);
            bankAccount.deposit(100);
        }

        bankAccount.printStatement();

        String expectedOutput = "DATE | AMOUNT | BALANCE\n" +
                "01/02/2000 | $100.00 | $150.00\n" +
                "01/01/2000 | $50.00 | $50.00";

        assertEquals(expectedOutput, systemOutRule.getLog().trim());
    }

    @Test
    public void shouldPrintAccurateStatementForMultipleDepositsAndWithdrawal() {
        BankAccount bankAccount = new BankAccount();

        LocalDate specifiedDate = LocalDate.of(2000, 1, 1);
        try (var mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(specifiedDate);
            bankAccount.deposit(50);
        }

        specifiedDate = LocalDate.of(2000, 1, 2);
        try (var mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(specifiedDate);
            bankAccount.deposit(100);
        }

        specifiedDate = LocalDate.of(2000, 1, 3);
        try (var mockStatic = mockStatic(LocalDate.class)) {
            mockStatic.when(LocalDate::now).thenReturn(specifiedDate);
            bankAccount.withdraw(75);
        }

        bankAccount.printStatement();

        String expectedOutput = "DATE | AMOUNT | BALANCE\n" +
                "01/03/2000 | -$75.00 | $75.00\n" +
                "01/02/2000 | $100.00 | $150.00\n" +
                "01/01/2000 | $50.00 | $50.00";

        assertEquals(expectedOutput, systemOutRule.getLog().trim());
    }
}
