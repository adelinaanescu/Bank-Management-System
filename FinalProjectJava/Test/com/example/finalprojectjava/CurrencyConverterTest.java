package com.example.finalprojectjava;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConverterTest {
    CurrencyConverter currencyConverter;

    public CurrencyConverterTest(){
        currencyConverter=new CurrencyConverter();
    }

    @Test
    public void getConvertCoefficiant() {
        assertEquals(java.util.Optional.of(0.23),java.util.Optional.of(currencyConverter.getConvertCoefficiant("RON","DOLLAR")));
        assertEquals(java.util.Optional.of(0.20),java.util.Optional.of(currencyConverter.getConvertCoefficiant("RON","EURO")));
        assertEquals(java.util.Optional.of(4.94),java.util.Optional.of(currencyConverter.getConvertCoefficiant("EURO","RON")));
        assertEquals(java.util.Optional.of(1.14),java.util.Optional.of(currencyConverter.getConvertCoefficiant("EURO","DOLLAR")));
    }
}