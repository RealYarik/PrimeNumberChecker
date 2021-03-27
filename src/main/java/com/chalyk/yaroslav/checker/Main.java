package com.chalyk.yaroslav.checker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PrimeNumberService primeNumberService = new PrimeNumberService();
        PrimeNumberFormatter primeNumberFormatter = new PrimeNumberFormatter();
        Scanner scanner = new Scanner(System.in);
        String fileName;

        if (args.length == 0) {
            System.out.println("Please enter the file name: ");
            fileName = scanner.nextLine();
        } else {
            fileName = args[0];
        }
        System.out.println("Prime numbers:");
        System.out.println(primeNumberFormatter.format(primeNumberService.getPrimeNumbers(fileName)));
    }
}
