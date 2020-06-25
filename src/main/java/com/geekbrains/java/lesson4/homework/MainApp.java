package com.geekbrains.java.lesson4.homework;

public class MainApp {
    public static void main(String[] args) {
        String[][] ar = {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "9"},
                {"1", "1", "1", "1"},
                {"1", "2", "1", "1"}
        };

        try {
            System.out.println(getMyArraySum(ar));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    private static int getMyArraySum(String[][] ar) throws MyArraySizeException, MyArrayDataException {
        if (!isCorrectArraySize(ar)) {
            throw new MyArraySizeException();
        }

        int sum = 0;
        for (String[] strings : ar) {
            for (String string : strings) {
                try {
                    sum += Integer.parseInt(string);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException();
                }
            }
        }
        return sum;
    }

    private static boolean isCorrectArraySize(String[][] array) {
        if (array.length != 4) {
            return false;
        }

        for (String[] ar : array) {
            if (ar.length != 4) {
                return false;
            }
        }

        return true;
    }
}
