package com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.stages;

import com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.Car;
import com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race.Race;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void overcome(Car c) {
        try {
            try {
                if (!Race.TUNNEL.tryAcquire()) {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    Race.TUNNEL.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                Race.TUNNEL.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
