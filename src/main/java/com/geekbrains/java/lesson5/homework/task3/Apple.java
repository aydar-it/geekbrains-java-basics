package com.geekbrains.java.lesson5.homework.task3;

public class Apple extends Fruit {
    private static final double WEIGHT = FruitWeight.APPLE_WEIGHT;

    @Override
    public double getWeight() {
        return WEIGHT;
    }

}
