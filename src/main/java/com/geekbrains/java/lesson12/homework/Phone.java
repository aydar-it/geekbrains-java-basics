package com.geekbrains.java.lesson12.homework;

@DbTable(name = "Phones")
public class Phone {
    @DbId
    private long id;

    @DbColumn
    private String model;

    private String groupName;

    @DbColumn
    private Integer price;

    public Phone() {

    }

    public Phone(String model, String groupName, Integer price) {
        this.model = model;
        this.groupName = groupName;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
