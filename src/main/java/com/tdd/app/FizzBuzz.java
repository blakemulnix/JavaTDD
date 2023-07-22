package com.tdd.app;

public class FizzBuzz {
    public static String fizzBuzz(int n) {
        if (n == 0) {
            return String.valueOf(n);
        }

        boolean divBy3 = n % 3 == 0;
        boolean divBy5 = n % 5 == 0;

        if (divBy3 && divBy5) {
            return "FizzBuzz";
        }

        if (divBy3) {
            return "Fizz";
        }

        if (divBy5) {
            return "Buzz";
        }

        return String.valueOf(n);
    }
}
