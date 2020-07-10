package com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race;

import com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    public static final int COMPETITORS_COUNT = 5;
    private final CyclicBarrier ready = new CyclicBarrier(COMPETITORS_COUNT);
    private final CountDownLatch start = new CountDownLatch(COMPETITORS_COUNT);
    private final CountDownLatch startSign = new CountDownLatch(1);
    private final CountDownLatch endSign = new CountDownLatch(COMPETITORS_COUNT);
    private final List<Stage> stages;

    public List<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10), ready, start, startSign, endSign);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            start.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            startSign.countDown();
            endSign.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
