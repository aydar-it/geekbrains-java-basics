package com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.stages;

import com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.Car;
import com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.Race;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore tunnelLock = new Semaphore(Race.COMPETITORS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void overcome(Car c) {
        try {
            try {
                if (!tunnelLock.tryAcquire()) {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    tunnelLock.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                tunnelLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
