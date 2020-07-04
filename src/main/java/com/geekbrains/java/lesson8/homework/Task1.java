package com.geekbrains.java.lesson8.homework;

import java.util.Arrays;

public class Task1 {
    public static void main(String[] args) {
        String str = "aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa aa aaaaaa";
        String result = Arrays.stream(str.split(" "))
                .filter(e -> e.length() > 5)
                .reduce((x, y) -> String.format("%s %s", x, y))
                .orElse("");
        System.out.println(result);
    }
}
