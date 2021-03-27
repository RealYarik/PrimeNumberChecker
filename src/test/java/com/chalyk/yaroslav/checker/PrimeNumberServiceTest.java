package com.chalyk.yaroslav.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimeNumberServiceTest {

    private PrimeNumberService primeNumberService;

    @BeforeEach
    public void init() {
        primeNumberService = new PrimeNumberService();
    }

    @Test
    public void givenNonExistentFile_whenGetPrimeNumber_thenRuntimeExceptionThrow() {
        assertThrows(RuntimeException.class, () ->
                primeNumberService.getPrimeNumbers("nonExistentFile.xlsx"));
    }

    @Test
    public void givenNullArgument_whenGetPrimeNumber_thenIllegalArgumentExceptionThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                primeNumberService.getPrimeNumbers(null));
    }

    /**
     * File name: emptyFileTest.xlsx
     * File contents: empty
     **/
    @Test
    public void givenEmptyFile_whenGetPrimeNumber_thenIllegalArgumentExceptionThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                primeNumberService.getPrimeNumbers("emptyFileTest.xlsx"));
    }

    /**
     * File name: dataForNumberFormatExceptionTest.xlsx
     * File contents: bad format
     **/
    @Test
    public void givenFileWithBadFormat_whenGetPrimeNumber_thenNumberFormatExceptionThrow() {
        assertThrows(NumberFormatException.class, () ->
                primeNumberService.getPrimeNumbers("dataForNumberFormatExceptionTest.xlsx"));
    }

    /**
     * File name: dataForNormalTest.xlsx
     * File contents: 0,2,7,-11,15,16,13,-3,1
     **/
    @Test
    public void givenNormalFile_whenGetPrimeNumber_thenResult() {
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(7);
        expected.add(13);

        List<Integer> actual = primeNumberService.getPrimeNumbers("dataForNormalTest.xlsx");

        assertEquals(expected, actual);
    }
}
