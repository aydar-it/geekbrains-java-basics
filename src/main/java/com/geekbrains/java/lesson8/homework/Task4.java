package com.geekbrains.java.lesson8.homework;

import java.util.Arrays;

public class Task4 {
    public static void main(String[] args) {
        String[] strings = {"123", "12", "12345"};
        int sum = Arrays.stream(strings)
                .mapToInt(e -> e.length())
                .sum();
        System.out.println(sum);
    }
}
