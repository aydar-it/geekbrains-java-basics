package com.geekbrains.java.lesson2.homework;

public class Group {
    private String name;
    private Employee[] employees;

    public Group(String name) {
        this.name = name;
        employees = new Employee[10];
    }

    public void insertEmployee(Employee employee, int index) {
        if (index < 0 || index > 9) {
            System.out.println("Индекс должен быть от 0 до 9");
            return;
        }
        employees[index] = employee;
    }

    public void deleteEmployee(int index) {
        if (index < 0 || index > 9) {
            System.out.println("Индекс должен быть от 0 до 9");
            return;
        }
        employees[index] = null;
    }

    public void deleteAllEmployee() {
        for (int i = 0; i < employees.length; i++) {
            employees[i] = null;
        }
    }

    public void showInfo() {
        for (Employee e : employees) {
            if (e != null) {
                e.showInfo();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
