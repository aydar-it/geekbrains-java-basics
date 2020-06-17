package com.geekbrains.java.lesson1.homework;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        int[] a = {1, 1, 1, 3, 1};
        System.out.println(checkBalance(a));

    }

    //    Task1
    public static boolean checkSum(int a, int b) {
        int c = a + b;
        return 10 <= c && c <= 20;
    }

    //    Task2
    public static void checkSign(int num) {
        if (num >= 0) {
            System.out.println("Положительное");
        } else {
            System.out.println("Негативное");
        }
    }

    //    Task3
    public static boolean isNegative(int num) {
        return num < 0;
    }

    //    Task4
    public static void helloName(String name) {
        System.out.println("Привет, " + name + "!");
    }

    //    Task5
    public static void arrayReplace() {
        int[] arr = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else if (arr[i] == 1) {
                arr[i] = 0;
            }
        }
    }

    //    Task6
    public static void arrFill() {
        int[] arr = new int[8];

        for (int i = 0, a = 2; i < arr.length; i++, a += 3) {
            arr[i] = a;
        }
    }

    //    Task7
    public static void task7() {
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
    }

    //    Task8
    public static void task8() {
        int l = 5;
        int[][] arr = new int[l][l];

        for (int i = 0, j = 0; i < l; i++, j++) {
            arr[i][j] = 1;
        }
    }

    //    Task9
    public static void task9() {
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Min = " + min + "\nMax = " + max);
    }

    //    Task10
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 4 == 0 && year % 400 == 0);
    }

    //    Task11
    public static boolean checkBalance(int[] arr) {
        return checkBalanceRecur(arr, 1);
    }

    private static boolean checkBalanceRecur(int[] arr, int pos) {
        if (pos == arr.length) {
            return false;
        }

        int beforePos = 0;
        int afterPos = 0;

        for (int i = 0; i < pos; i++) {
            beforePos += arr[i];
        }

        for (int j = pos; j < arr.length; j++) {
            afterPos += arr[j];
        }

        if (beforePos == afterPos) {
            return true;
        }

        return checkBalanceRecur(arr, pos + 1);
    }
}
