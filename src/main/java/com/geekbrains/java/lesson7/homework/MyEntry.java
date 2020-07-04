package com.geekbrains.java.lesson7.homework;

import java.util.*;

public class MyEntry {
    private static final Random RANDOM = new Random();

    private final Integer key;
    private final Integer value;

    public MyEntry(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public static void main(String[] args) {
        List<MyEntry> list = new ArrayList<>();
        for (int i = 0; i < 50_000; i++) {
            list.add(new MyEntry(i, RANDOM.nextInt()));
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (MyEntry entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            Integer k = RANDOM.nextInt(49_999);
            for (MyEntry entry : list) {
                if (entry.getKey().equals(k)) {
                    break;
                }
            }
        }
        System.out.println("Time: list " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            Integer k = RANDOM.nextInt(49_999);
            map.get(k);
        }
        System.out.println("Time: map " + (System.currentTimeMillis() - time));
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
