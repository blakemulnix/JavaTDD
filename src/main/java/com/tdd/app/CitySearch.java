package com.tdd.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CitySearch {

    static List<String> cities = Arrays.asList("Paris", "Budapest", "Skopje", "Rotterdam",
            "Valencia", "Vancouver", "Amsterdam", "Vienna", "Sydney", "New York City",
            "London", "Bangkok", "Hong Kong", "Dubai", "Rome", "Istanbul");

    public static List<String> searchCities(String input) {
        if (input.length() < 2) {
            return new ArrayList<>();
        }

        if (input.equals("*")) {
            return cities;
        }

        String lcInput = input.toLowerCase();

        return cities.stream()
                .filter((city) -> {
                    String lcCity = city.toLowerCase();
                    return lcCity.startsWith(lcInput) ||
                            lcCity.contains(lcInput);
                })
                .collect(Collectors.toList());
    }
}
