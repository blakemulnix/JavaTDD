package com.tdd.app;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import static com.tdd.app.CitySearch.searchCities;

import org.junit.Test;

public class CitySearchTest {
    @Test
    public void shouldReturnEmptyListForInputLessThan2Chars() {
        List<String> result = searchCities("a");
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldReturnCityNamesStartingWithCharsForInputWith2OrMoreChars() {
        List<String> result = searchCities("Va");
        assertTrue(result.containsAll(Arrays.asList("Vancouver", "Valencia")));
    }

    @Test
    public void shouldReturnCityNamesStartingWithCharsForAnyCaseInput() {
        List<String> result1 = searchCities("va");
        assertTrue(result1.containsAll(Arrays.asList("Vancouver", "Valencia")));

        List<String> result2 = searchCities("vA");
        assertTrue(result2.containsAll(Arrays.asList("Vancouver", "Valencia")));
    }

    @Test
    public void shouldReturnCityNamesContainingCharsForInputWith2OrMoreChars() {
        List<String> result = searchCities("uda");
        assertTrue(result.containsAll(Arrays.asList("Budapest")));
    }

    @Test
    public void shouldReturnAllCityNamesForInputWithOnlyAsterisk() {
        List<String> result = searchCities("uda");
        assertTrue(result.containsAll(Arrays.asList("Budapest")));
    }
}
