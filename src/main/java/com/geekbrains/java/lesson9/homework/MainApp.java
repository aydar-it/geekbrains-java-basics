package com.geekbrains.java.lesson9.homework;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class MainApp {
//    Time for ForkJoinPool: 966
//Time for one thread: 252
//Time for Stream: 59
//Time for ParallelStream: 21
    public static void main(String[] args) {
        Random random = new Random();
        int[] data = new int[100_000_000];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100_000);
        }
        checkForkJoinPool(data);
        checkOneThread(data);
        checkStream(data);
        checkParallelStream(data);
    }

    private static void checkParallelStream(int[] data) {
        long time = new Date().getTime();
        int result = IntStream.of(data)
                .parallel()
                .max()
                .orElseThrow(() -> new RuntimeException("Empty array"));
        System.out.println("Time for ParallelStream: " + (new Date().getTime() - time));
        System.out.println(result);
    }

    private static void checkStream(int[] data) {
        long time = new Date().getTime();
        int result = IntStream.of(data)
                .max()
                .orElseThrow(() -> new RuntimeException("Empty array"));
        System.out.println("Time for Stream: " + (new Date().getTime() - time));
        System.out.println(result);
    }

    private static void checkOneThread(int[] data) {
        long time = new Date().getTime();
        int result = data[0];
        for (int i = 1; i < data.length; i++) {
            if (result < data[i]) {
                result = data[i];
            }
        }
        System.out.println("Time for one thread: " + (new Date().getTime() - time));
        System.out.println(result);
    }

    private static void checkForkJoinPool(int[] data) {
        Recursive demoRecursiveTask = new Recursive(data);
        long time = new Date().getTime();
        long result = ForkJoinPool.commonPool().invoke(demoRecursiveTask);
        System.out.println("Time for ForkJoinPool: " + (new Date().getTime() - time));
        System.out.println(result);
    }
}
