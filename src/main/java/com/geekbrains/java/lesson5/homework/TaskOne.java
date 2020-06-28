package com.geekbrains.java.lesson5.homework;

import java.util.Arrays;

public class TaskOne {
    public static void main(String[] args) {
        String[] ar = {"1", "2", "3"};
        System.out.println(Arrays.deepToString(ar));
        swapElementsInArray(ar, 0, 1);
        System.out.println(Arrays.deepToString(ar));
    }

    public static <T> void swapElementsInArray(T[] array, int posOne, int posTwo) {
        T tmp = array[posOne];
        array[posOne] = array[posTwo];
        array[posTwo] = tmp;
    }
}
