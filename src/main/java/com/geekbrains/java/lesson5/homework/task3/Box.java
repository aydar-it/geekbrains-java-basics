package com.geekbrains.java.lesson5.homework.task3;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    private final List<T> fruitBox;

    public Box() {
        this(new ArrayList<>());
    }

    public Box(List<T> fruitBox) {
        this.fruitBox = fruitBox;
    }

    public <X extends Fruit> boolean compare(Box<X> box) {
        return this.getWeight() == box.getWeight();
    }

    public double getWeight() {
        if (fruitBox == null || fruitBox.size() == 0) {
            return 0;
        }
        return fruitBox.size() * this.fruitBox.get(0).getWeight();
    }

    public void addFruit(T fruit) {
        fruitBox.add(fruit);
    }

    public void pourBox(Box<T> box) {
        if (this == box) {
            return;
        }

        fruitBox.forEach(box::addFruit);
//        for (T fruit : fruitBox) {
//            box.addFruit(fruit);
//        }
        fruitBox.clear();
    }
}
