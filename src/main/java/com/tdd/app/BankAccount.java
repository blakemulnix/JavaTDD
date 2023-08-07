package com.tdd.app;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BankAccount {

    // Use TreeMap to maintain sort order for date keys
    private Map<LocalDate, Integer> ledger = new TreeMap();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private String ledgerEntryFormat = "%s | %s | %s\n";

    public void deposit(int amount) {
        ledger.put(LocalDate.now(), amount * 100);
    }

    public void withdraw(int amount) {
        ledger.put(LocalDate.now(), amount * -100);
    }

    public void printStatement() {
        List<String> statementEntries = new ArrayList<>();
        int balance = 0;

        for (Map.Entry<LocalDate, Integer> entry : ledger.entrySet()) {
            LocalDate date = entry.getKey();
            Integer cents = entry.getValue();

            balance += cents;

            String dateStr = date.format(dateFormat);
            String dollarsStr = centsToDollars(cents);
            String balanceStr = centsToDollars(balance);

            statementEntries.add(String.format(ledgerEntryFormat, dateStr, dollarsStr, balanceStr));
        }

        Collections.reverse(statementEntries);

        String statement = "DATE | AMOUNT | BALANCE\n";
        for (String statementEntry : statementEntries) {
            statement += statementEntry;
        }

        System.out.println(statement);
    }

    public static String centsToDollars(int cents) {
        double dollars = cents / 100.0;
        DecimalFormat decimalFormat = new DecimalFormat("$#,##0.00");
        String dollarsString = decimalFormat.format(dollars);
        return dollarsString;
    }
}
