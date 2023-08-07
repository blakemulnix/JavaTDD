package com.tdd.app;

import static org.junit.Assert.assertEquals;

import java.util.List;

import static com.tdd.app.PointOfSale.scanBarcode;
import static com.tdd.app.PointOfSale.total;

import org.junit.Test;

public class PointOfSaleTest {
    @Test
    public void shouldReturn725For12345() {
        String result = scanBarcode("12345");
        assertEquals("$7.25", result);
    }

    @Test
    public void shouldReturn1250For23456() {
        String result = scanBarcode("23456");
        assertEquals("$12.50", result);
    }

    @Test
    public void shouldReturnErrorNotFoundFor99999() {
        String result = scanBarcode("99999");
        assertEquals("Error: barcode not found", result);
    }

    @Test
    public void shouldReturnTotalForMultipleBarcodes() {
        String result = total(List.of("12345", "23456"));
        assertEquals("$19.75", result);
    }
}
