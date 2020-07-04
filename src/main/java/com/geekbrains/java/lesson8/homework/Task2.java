package com.geekbrains.java.lesson8.homework;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {
        String[][] ar = {{"1", "2"}, {"3", "2", "4"}, {"5", "6", "1", "7"}};
        Set<String> strings = Arrays.stream(ar)
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
        System.out.println(strings);
    }
}
