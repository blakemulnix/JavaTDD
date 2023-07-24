package com.tdd.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final String DEFAULT_DELIM_REGEX = "[,\n]";

    private static class DelimSpecifier {
        public String regex;
        public String unescaped;

        public DelimSpecifier(String regex, String unescaped) {
            this.regex = regex;
            this.unescaped = unescaped;
        }
    }

    private static Optional<DelimSpecifier> getOptionalDelimSpecifier(String input) {
        String delimSpecifierRegex = "//(.+?)\n";
        Matcher matcher = Pattern.compile(delimSpecifierRegex).matcher(input);
        if (matcher.find()) {
            String delimRegex = matcher.group(1);
            String unescapedDelim = delimRegex;
            delimRegex = Pattern.quote(delimRegex);
            return Optional.of(new DelimSpecifier(delimRegex, unescapedDelim));
        }

        return Optional.empty();
    }

    private static Optional<String> checkForDelimiterAtEnd(String input, String numberList, String delimRegex) {
        if (numberList.matches(".*" + delimRegex + "$")) {
            Matcher matcher = Pattern.compile(delimRegex).matcher(input);

            int lastOccurrenceIndex = -1;
            while (matcher.find()) {
                lastOccurrenceIndex = matcher.start();
            }
            String endingDelim = input.substring(lastOccurrenceIndex);

            String errorMessage = String.format("Delimiter \"%s\" occured at end of input", endingDelim);
            return Optional.of(errorMessage);
        }

        return Optional.empty();
    }

    private static Optional<String> checkForUnexpectedDelimiter(String[] numberSplit, String numberList,
            String unescapedDelim) {
        String nonNumeric = Arrays.stream(numberSplit)
                .filter(s -> !s.matches("^-?[1-9]\\d*$"))
                .findFirst()
                .orElse(null);

        Character falseDelim = null;
        if (nonNumeric != null) {
            falseDelim = nonNumeric.chars()
                    .mapToObj(c -> (char) c)
                    .filter(ch -> !Character.isDigit(ch))
                    .findFirst()
                    .orElse(null);
        }

        if (falseDelim != null) {
            int index = numberList.indexOf(falseDelim);
            String message = String.format("'%s' expected but '%c' found at position %d",
                    unescapedDelim, falseDelim, index);
            return Optional.of(message);
        }

        return Optional.empty();
    }

    private static Optional<String> checkForNegativeNumbers(String numbersList) {
        Matcher matcher = Pattern.compile("-\\d+").matcher(numbersList);

        List<String> negativeNumbers = new ArrayList<String>();

        while (matcher.find()) {
            negativeNumbers.add(matcher.group());
        }

        if (!negativeNumbers.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Negative number(s) not allowed: ");
            sb.append(negativeNumbers.get(0));

            for (int i = 1; i < negativeNumbers.size(); i++) {
                sb.append(", ").append(negativeNumbers.get(i));
            }

            return Optional.of(sb.toString());
        }

        return Optional.empty();
    }

    private static String buildErrorString(List<Optional<String>> potentialErrorList) {
        return potentialErrorList.stream()
                .filter(Optional::isPresent)
                .map(e -> e.get())
                .reduce("", (r, e) -> r.concat(e + "\n"));
    }

    public static int Add(String input) throws IOException {
        if (input.isEmpty()) {
            return 0;
        }

        // Set up defaults for case where delim specifier is not provided
        String numberList = input;
        String delimRegex = DEFAULT_DELIM_REGEX;
        String unescapedDelim = DEFAULT_DELIM_REGEX;

        Optional<DelimSpecifier> optionalDelimSpecifier = getOptionalDelimSpecifier(input);
        if (optionalDelimSpecifier.isPresent()) {
            DelimSpecifier delimSpecifier = optionalDelimSpecifier.get();
            delimRegex = delimSpecifier.regex;
            unescapedDelim = delimSpecifier.unescaped;

            numberList = input.split("\n")[1];
        }

        String[] numberSplit = numberList.split(delimRegex);

        Optional<String> negativeNumberError = checkForNegativeNumbers(numberList);
        Optional<String> unexpectedDelimError = checkForUnexpectedDelimiter(numberSplit, numberList, unescapedDelim);
        Optional<String> endingDelimError = checkForDelimiterAtEnd(input, numberList, delimRegex);

        List<Optional<String>> potentialErrorList = Arrays.asList(
                negativeNumberError,
                unexpectedDelimError,
                endingDelimError);

        String errors = buildErrorString(potentialErrorList);

        if (!errors.isEmpty()) {
            errors = errors.trim();
            throw new IOException(errors);
        }

        return Arrays.stream(numberSplit)
                .mapToInt(Integer::parseInt)
                .filter(n -> n <= 1000)
                .reduce(0, Integer::sum);
    }
}
