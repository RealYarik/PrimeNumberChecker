package com.chalyk.yaroslav.checker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimeNumberFormatterTest {

    private PrimeNumberFormatter primeNumberFormatter;

    @BeforeEach
    public void init() {
        primeNumberFormatter = new PrimeNumberFormatter();
    }

    @Test
    public void givenNullArgument_whenFormat_thenIllegalArgumentExceptionThrow() {
        assertThrows(IllegalArgumentException.class, () ->
                primeNumberFormatter.format(null));
    }

    @Test
    public void givenNormalList_whenFormat_thenResult() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(7);
        integerList.add(13);
        String expected = "2" + lineSeparator() + "7" + lineSeparator() + "13" + lineSeparator();

        String actual = primeNumberFormatter.format(integerList);

        assertEquals(expected, actual);
    }
}
