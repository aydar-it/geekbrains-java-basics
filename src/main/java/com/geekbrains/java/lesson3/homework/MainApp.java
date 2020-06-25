package com.geekbrains.java.lesson3.homework;

import com.geekbrains.java.lesson3.homework.barrier.RunningTrack;
import com.geekbrains.java.lesson3.homework.barrier.Wall;
import com.geekbrains.java.lesson3.homework.racemembers.Cat;
import com.geekbrains.java.lesson3.homework.racemembers.Human;
import com.geekbrains.java.lesson3.homework.racemembers.Robot;
import com.geekbrains.java.lesson3.homework.runner.Racer;

public class MainApp {
    public static void main(String[] args) {
        MainApp.startRace();
    }

    public static void startRace() {
        Human human = new Human("Human1", 5, 5);
        Robot robot = new Robot("Robot1", 3, 10);
        Cat cat = new Cat("Cat1", 10, 3);
        Wall wall = new Wall(4);
        RunningTrack track = new RunningTrack(4);

        Racer[] racers = new Racer[]{human, robot, cat};
        Object[] objects = new Object[]{wall, track};
        for (Racer racer : racers) {
            for (Object o : objects) {
                Boolean result = true;
                if (o instanceof Wall) {
                    result = racer.jump((Wall)o);
                } else if (o instanceof RunningTrack) {
                    result = racer.run((RunningTrack) o);
                }
                if (!result) {
                    break;
                }
            }
        }
    }
}
