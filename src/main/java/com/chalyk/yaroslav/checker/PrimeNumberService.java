package com.chalyk.yaroslav.checker;

import com.chalyk.yaroslav.checker.util.FileReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumberService {

    private static final String REGEX_FOR_NUMBERS = "[^0-9-]+";

    public List<Integer> getPrimeNumbers(String filename) {
        return FileReader.read(filename)
                .map(line -> {
                    if (line.isEmpty()) {
                        throw new IllegalArgumentException("File line cannot be empty");
                    } else {
                        return line;
                    }
                })
                .map(line -> Integer.parseInt(line.replaceAll(REGEX_FOR_NUMBERS, "")))
                .filter(this::isPrime)
                .collect(Collectors.toList());
    }

    private boolean isPrime(int number) {
        return (number > 1) && IntStream.rangeClosed(2, number / 2)
                .noneMatch(i -> number % i == 0);
    }
}
