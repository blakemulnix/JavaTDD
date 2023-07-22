package com.tdd.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static com.tdd.app.FizzBuzz.fizzBuzz;

/**
 * Unit test for simple App.
 */
public class FizzBuzzTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldReturn0ForPos0() {
        assertEquals("0", fizzBuzz(0));
    }

    @Test
    public void shouldReturn1ForPos1() {
        assertEquals("1", fizzBuzz(1));
    }

    @Test
    public void shouldReturnFizzForPos3() {
        assertEquals("Fizz", fizzBuzz(3));
    }

    @Test
    public void shouldReturnBuzzForPos5() {
        assertEquals("Buzz", fizzBuzz(5));
    }

    @Test
    public void shouldReturnFizzForPos6() {
        assertEquals("Fizz", fizzBuzz(6));
    }

    @Test
    public void shouldReturn7ForPos7() {
        assertEquals("7", fizzBuzz(7));
    }

    @Test
    public void shouldReturnBuzzForPos10() {
        assertEquals("Buzz", fizzBuzz(10));
    }

    @Test
    public void shouldReturnFizzBuzzForPos15() {
        assertEquals("FizzBuzz", fizzBuzz(15));
    }
}
