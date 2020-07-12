package com.geekbrains.java.lesson11.homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class MainApp {

    public static void main(String[] args) throws Exception{
        MainApp.start(Test1.class);
    }

    public static void start(Class testCLass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        isCorrectTestClass(testCLass);
        Object instance = testCLass.getConstructor().newInstance();
        Method beforeSuiteMethod = Arrays.stream(testCLass.getDeclaredMethods())
                .filter(e -> e.getAnnotation(BeforeSuite.class) != null)
                .findFirst()
                .orElse(null);
        if (beforeSuiteMethod != null) {
            beforeSuiteMethod.invoke(instance);
        }
        Arrays.stream(testCLass.getDeclaredMethods())
                .filter(e -> e.getAnnotation(Test.class) != null)
                .sorted(Comparator.comparingInt(e -> e.getAnnotation(Test.class).priority()))
                .forEach(e -> {
                    try {
                        e.setAccessible(true);
                        e.invoke(instance);
                    } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                });
        Method afterSuiteMethod = Arrays.stream(testCLass.getDeclaredMethods())
                .filter(e -> e.getAnnotation(AfterSuite.class) != null)
                .findFirst()
                .orElse(null);
        if (afterSuiteMethod != null) {
            afterSuiteMethod.invoke(instance);
        }
    }

    private static void isCorrectTestClass(Class testCLass) {
        if (Arrays.stream(testCLass.getDeclaredMethods())
                .filter(e -> e.getAnnotation(BeforeSuite.class) != null)
                .count() > 1) {
            throw new RuntimeException("Count of methods annotated by BeforeSuite more than one");
        }

        if (Arrays.stream(testCLass.getDeclaredMethods())
                .filter(e -> e.getAnnotation(AfterSuite.class) != null)
                .count() > 1) {
            throw new RuntimeException("Count of methods annotated by AfterSuite more than one");
        }
    }
}
