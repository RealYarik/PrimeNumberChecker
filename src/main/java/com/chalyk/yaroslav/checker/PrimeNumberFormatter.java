package com.chalyk.yaroslav.checker;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class PrimeNumberFormatter {

    public String format(List<Integer> primeNumbers) {
        return Optional.ofNullable(primeNumbers)
                .orElseThrow(() -> new IllegalArgumentException("Cannot be formatted null"))
                .stream()
                .map(primeNumber -> primeNumber + lineSeparator())
                .collect(Collectors.joining());
    }
}
