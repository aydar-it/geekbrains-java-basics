package com.geekbrains.java.lesson6.homework.task1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TaskOne {
    public static void main(String[] args) {
        String[] strings = {"one", "two", "two", "three", "three", "three", "four", "four", "four", "four"};
        printOnlyOrig(strings);
        printCountOfArrayElements(strings);
    }

    private static void printCountOfArrayElements(String[] strings) {
        Map<String, Integer> counter = new HashMap<>();
        for (String str : strings) {
            Integer value = counter.get(str);
            if (value == null) {
                value = 1;
            } else {
                value++;
            }
            counter.put(str, value);
        }
        System.out.println(counter);
    }

    public static void printOnlyOrig(String[] strings) {
        System.out.println(new HashSet<>(Arrays.asList(strings)));
    }
}
