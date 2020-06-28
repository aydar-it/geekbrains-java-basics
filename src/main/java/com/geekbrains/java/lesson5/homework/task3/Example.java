package com.geekbrains.java.lesson5.homework.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example {
    public static void main(String[] args) {
        showExamples();
    }

    public static void showExamples() {
        List<Apple> apples = new ArrayList<>();
        Collections.addAll(apples, new Apple(), new Apple());
        Box<Apple> box = new Box<>(apples);

        List<Orange> orange1 = new ArrayList<>();
        Collections.addAll(orange1, new Orange(), new Orange());
        Box<Orange> box1 = new Box<>(orange1);

        System.out.println(box.getWeight());
        System.out.println(box1.getWeight());
        System.out.println(box.compare(box1));
        box.addFruit(new Apple());
        System.out.println(box.compare(box1));

        List<Apple> apples2 = new ArrayList<>();
        Collections.addAll(apples, new Apple(), new Apple());
        Box<Apple> box2 = new Box<>(apples2);
        box.pourBox(box2);
        System.out.println(box.getWeight());
        System.out.println(box2.getWeight());
    }
}
