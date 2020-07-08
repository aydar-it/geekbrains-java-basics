package com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race;

import com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Race {
    public static final int COMPETITORS_COUNT = 4;
    public static final CyclicBarrier READY = new CyclicBarrier(COMPETITORS_COUNT);
    public static final CountDownLatch START = new CountDownLatch(COMPETITORS_COUNT);
    public static final CountDownLatch START_SIGN = new CountDownLatch(1);
    public static final CountDownLatch END_SIGN = new CountDownLatch(COMPETITORS_COUNT);
    public static final Semaphore TUNNEL = new Semaphore(COMPETITORS_COUNT / 2);

    private final List<Stage> stages;

    public List<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(this, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            START.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            START_SIGN.countDown();
            END_SIGN.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
