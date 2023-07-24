package com.tdd.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import static com.tdd.app.StringCalculator.Add;

public class StringCalculatorTest {

    @Test
    public void givenEmptyString_shouldReturnIntValue() throws Exception {
        assertEquals(0, Add(""));
    }

    @Test
    public void given1_shouldReturnIntValue() throws Exception {
        assertEquals(1, Add("1"));
    }

    @Test
    public void given2_shouldReturnIntValue() throws Exception {
        assertEquals(2, Add("2"));
    }

    @Test
    public void given2NumsNewLineDelim_shouldReturnIntValueSum() throws Exception {
        assertEquals(3, Add("1,2"));
    }

    @Test
    public void given2NumsCommaDelim_shouldReturnIntValueSum() throws Exception {
        assertEquals(3, Add("1\n2"));
    }

    @Test
    public void given3NumsUsingCommaAndNewlineDelim_shouldReturnIntValueSum() throws Exception {
        assertEquals(6, Add("1,2\n3"));
    }

    @Test
    public void given2NumsWithDelimAtEnd_shouldThrowIOException() {
        try {
            assertEquals(3, Add("1,2,"));
            fail("Exception not thrown");
        } catch (IOException e) {
            assertEquals(e.getMessage(), "Delimiter \",\" occured at end of input");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void given3NumsAndDelimSpecifer_shouldReturnIntValueSum() throws Exception {
        assertEquals(6, Add("//;\n1;2;3"));
    }

    @Test
    public void given3NumsAndDelimSpeciferWithIncorrectDelimInNums_ShouldThrowIOException() throws Exception {
        try {
            assertEquals(6, Add("//|\n1|2,3"));
            fail("Exception not thrown");
        } catch (IOException e) {
            assertEquals("'|' expected but ',' found at position 3", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void given2NumsAndWith1Negative_ShouldThrowIOException() throws Exception {
        try {
            assertEquals(6, Add("1,-2"));
            fail("Exception not thrown");
        } catch (IOException e) {
            assertEquals("Negative number(s) not allowed: -2", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }

    }

    @Test
    public void given3NumsAndWith2Negative_ShouldThrowIOException() throws Exception {
        try {
            assertEquals(6, Add("1,-2,-3"));
            fail("Exception not thrown");
        } catch (IOException e) {
            assertEquals("Negative number(s) not allowed: -2, -3", e.getMessage());
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void given3NumsAndMultipleErrors_ShouldThrowIOExceptionListingErrors() throws Exception {
        try {
            assertEquals(6, Add("//|\n1|2,-3"));
            fail("Exception not thrown");
        } catch (IOException e) {
            assertEquals(e.getMessage(),
                    "Negative number(s) not allowed: -3\n'|' expected but ',' found at position 3");
        } catch (Exception e) {
            fail("Unexpected exception thrown");
        }
    }

    @Test
    public void given3NumsWithNumOver1000_shouldIgnoreNumOver1000() throws Exception {
        assertEquals(3, Add("1,1001,2"));
    }
}
