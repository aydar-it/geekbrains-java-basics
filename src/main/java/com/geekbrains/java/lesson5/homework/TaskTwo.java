package com.geekbrains.java.lesson5.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskTwo {
    public static void main(String[] args) {
        String[] strings = {"1", "2", "3"};
        System.out.println(getArrayListFromArray(strings).getClass());
    }

    //Почему просто Arrays.asList(array),
    //возвращает лист фиксированного размера
    //(нельзя использовать add и remove)?
    public static <T> List<T> getArrayListFromArray(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}
