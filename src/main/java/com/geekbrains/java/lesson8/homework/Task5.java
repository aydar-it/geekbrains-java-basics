package com.geekbrains.java.lesson8.homework;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task5 {
    public static void main(String[] args) {
        String[] strings = {"cccc", "dd", "bbb", "aaaa"};
        List<String> list = Arrays.stream(strings)
                .limit(3)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
