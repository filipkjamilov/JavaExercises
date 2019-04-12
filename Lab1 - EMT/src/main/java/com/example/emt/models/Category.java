package com.example.emt.models;

public class Category {

    private Long ID;
    private String name;

    public Category(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public Category(){}
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
