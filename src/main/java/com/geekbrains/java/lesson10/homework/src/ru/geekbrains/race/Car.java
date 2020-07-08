package com.geekbrains.java.lesson10.homework.src.ru.geekbrains.race;

public class Car implements Runnable {
    private static final Object LOCK = new Object();
    private static int CARS_COUNT;
    private static boolean WINNER = true;

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            Race.READY.await();
            System.out.println(this.name + " готов");
            Race.START.countDown();
            Race.START_SIGN.await();
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
        Race.END_SIGN.countDown();
    }
}