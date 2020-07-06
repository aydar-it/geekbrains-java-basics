package com.geekbrains.java.lesson8.homework;

import org.w3c.dom.ls.LSOutput;

import java.util.stream.IntStream;

public class Task3 {
    public static void main(String[] args) {
        int sum = IntStream.range(100, 201)
                .filter(e -> e % 2 == 0)
                .sum();
        System.out.println(sum);
    }
}
