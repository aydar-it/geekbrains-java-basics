package com.geekbrains.java.lesson5.homework.task3;

public class Orange extends Fruit {
    private static final double WEIGHT = FruitWeight.ORANGE_WEIGHT;

    @Override
    public double getWeight() {
        return WEIGHT;
    }
}
