package com.chalyk.yaroslav.checker.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderTest {

    @Test
    public void givenNonExistentFile_whenRead_thenRuntimeExceptionThrow() {
        assertThrows(RuntimeException.class, () -> FileReader.read("nonExistentFile.xlsx"));
    }

    @Test
    public void givenNullArgument_whenRead_thenIllegalArgumentExceptionThrow() {
        assertThrows(IllegalArgumentException.class, () -> FileReader.read(null));
    }

    @Test
    public void givenNormalFile_whenRead_thenStreams() {
        String[] actual = new String[]{",,0", ",,2", ",,7", ",,-11", ",,15", ",,16", ",,13", ",,-3", ",,1"};

        assertTrue(Arrays.equals(actual, FileReader.read("dataForNormalTest.xlsx").toArray(String[]::new)));
    }
}
