package com.geekbrains.java.lesson12.homework;

@DbTable(name = "Students")
public class Student {
    @DbId
    private long id;

    @DbColumn
    private String name;

    @DbColumn
    private String groupName;

    @DbColumn
    private Integer score;

    public Student() {

    }

    public Student(String name, String groupName, Integer score) {
        this.name = name;
        this.groupName = groupName;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupName='" + groupName + '\'' +
                ", score=" + score +
                '}';
    }
}
