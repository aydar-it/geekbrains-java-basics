package com.geekbrains.java.lesson6.homework.task2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private final Map<String, Set<String>> base = new HashMap<>();

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.add("qwe", "1");
        phonebook.add("qwe", "2");
        phonebook.add("qwe", "3");
        phonebook.add("ewq", "1");
        System.out.println(phonebook.get("qwe"));
        System.out.println(phonebook.get("ewq"));
        System.out.println(phonebook.get("sdf"));
    }

    public void add(String name, String phone) {
        Set<String> value = base.get(name);
        if (value == null) {
            value = new HashSet<>();
        }
        value.add(phone);
        base.put(name, value);
    }

    public Set<String> get(String name) {
        return base.get(name);
    }
}
