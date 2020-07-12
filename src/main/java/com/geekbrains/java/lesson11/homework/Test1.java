package com.geekbrains.java.lesson11.homework;

public class Test1 {

    @BeforeSuite
    public void start() {
        System.out.println("Start " + getClass().getSimpleName());
    }

    @AfterSuite
    public void end() {
        System.out.println("End");
    }

    @Test(priority = 1)
    public void method1() {
        System.out.println("test 1");
    }

    @Test(priority = 9)
    private void method2() {
        System.out.println("test 9");
    }

    @Test(priority = 3)
    private void method3() {
        System.out.println("test 3");
    }

    @Test(priority = 3)
    public void method4() {
        System.out.println("test 3");
    }

    public void method5() {
        System.out.println("test 2");
    }
}
