package com.geekbrains.java.lesson7.homework;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/*
по 10000 обращений
              10     100     1000    10000      100000
ArrayList:    0      1          1       3         148
LinkedList:   0      0          2      27        2756

 */
public class Task2 {private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        List<Integer> al = new ArrayList<>();
        List<Integer> ll = new LinkedList<>();
        for (int i = 10; i < 1_000_000; i *= 10) {
            insertList(al, i);
            insertList(ll, i);
            System.out.println(String.format("i = %d al = %d ll = %d", i, al.size(), ll.size()));
            long time = System.currentTimeMillis();
            delList(al);
            System.out.println("Time for al: " + (System.currentTimeMillis() - time));
            time = System.currentTimeMillis();
            delList(ll);
            System.out.println("Time for ll: " + (System.currentTimeMillis() - time));
        }
    }

    private static void delList(List<Integer> al) {
        int len = al.size() / 2;
        while (al.size() > len) {
            al.remove(al.size() / 2);
        }
    }

    private static void insertList(List<Integer> al, int i) {
        while (al.size() < i) {
            al.add(RANDOM.nextInt());
        }
    }
}
