package com.geekbrains.java.lesson12.homework;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainApp {
    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/java/com/geekbrains/java/lesson12/homework/mydatabase.db");
//        ReflectionRepository<Student> reflectionRepository = new ReflectionRepository<>(Student.class, conn);
//        reflectionRepository.add(new Student("111", "SweddeGroup1", 111));
//        reflectionRepository.add(new Student("222", "SweddeGroup1", 222));
//        reflectionRepository.add(new Student("333", "SweddeGroup1", 33));
//        System.out.println(reflectionRepository.add(new Student("444", "SweddeGroup1", 44)));
//        reflectionRepository.remove(2);
//        System.out.println(reflectionRepository.getAll());
        ReflectionRepository<Phone> repository = new ReflectionRepository<>(Phone.class, conn);
        System.out.println(repository.add(new Phone("HTC", "Group", 1)));
        System.out.println(repository.add(new Phone("Samsung", "Group", 2)));
        System.out.println(repository.add(new Phone("iPhone", "Group", 3)));
        System.out.println(repository.add(new Phone("HTC", "Group", 1)));
        System.out.println(repository.add(new Phone("Samsung", "Group", 2)));
        System.out.println(repository.add(new Phone("iPhone", "Group", 3)));
        System.out.println(repository.getAll());
        repository.remove(2);
        repository.remove(5);
        System.out.println(repository.get(47));
        System.out.println(repository.getAll());
        repository.removeAll();
        System.out.println(repository.getAll());
        conn.close();
    }
}
