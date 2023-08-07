package com.tdd.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointOfSale {

    private static Map<String, Integer> prices = new HashMap<String, Integer>() {
        {
            put("12345", 725);
            put("23456", 1250);
        }
    };

    public static String scanBarcode(String barcode) {
        if (!prices.containsKey(barcode)) {
            return "Error: barcode not found";
        }

        return centsToDollarsString(prices.get(barcode));
    }

    public static String centsToDollarsString(int cents) {
        int dollars = cents / 100;
        int remainingCents = cents % 100;

        String dollarsPart = Integer.toString(dollars);
        String centsPart = Integer.toString(remainingCents);

        if (remainingCents < 10) {
            centsPart = "0" + centsPart;
        }

        return "$" + dollarsPart + "." + centsPart;
    }

    public static String total(List<String> barcodes) {
        int cents = barcodes.stream()
                .map(barcode -> prices.get(barcode))
                .reduce(0, (a, b) -> a + b);

        return centsToDollarsString(cents);
    }

}