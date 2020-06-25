package com.geekbrains.java.lesson3.homework.racemembers;

import com.geekbrains.java.lesson3.homework.runner.Racer;
import com.geekbrains.java.lesson3.homework.barrier.RunningTrack;
import com.geekbrains.java.lesson3.homework.barrier.Wall;

public class Human implements Racer {
    private String name;
    private int jumpHeight;
    private int runDistance;

    public Human(String name, int jumpHeight, int runDistance) {
        this.name = name;
        this.jumpHeight = jumpHeight;
        this.runDistance = runDistance;
    }

    @Override
    public boolean jump(Wall wall) {
        boolean result = wall.getHeight() <= jumpHeight;
        System.out.printf("%s jump : %s\n", name, result ? "success" : "fail");
        return result;
    }

    @Override
    public boolean run(RunningTrack track) {
        boolean result = track.getDistance() <= runDistance;
        System.out.printf("%s run : %s\n", name, result ? "success" : "fail");
        return result;
    }
}
