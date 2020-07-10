package com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static final Object LOCK = new Object();
    private static int CARS_COUNT;
    private static boolean WINNER = true;

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier ready;
    private CountDownLatch start;
    private CountDownLatch startSign;
    private CountDownLatch endSign;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier ready, CountDownLatch start,
               CountDownLatch startSign, CountDownLatch endSign) {
        this.race = race;
        this.speed = speed;
        this.ready = ready;
        this.start = start;
        this.startSign = startSign;
        this.endSign = endSign;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            ready.await();
            System.out.println(this.name + " готов");
            start.countDown();
            startSign.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).overcome(this);
        }
        synchronized (LOCK) {
            if (WINNER) {
                System.out.println(name + " - WIN");
            }
            WINNER = false;
        }
        endSign.countDown();
    }
}