package com.example.demo;

public class Category {

    private long catID;
    private String catName;

    public Category(){}

    public Category(long catID, String catName) {
        this.catID = catID;
        this.catName = catName;
    }

    public long getCatID() {
        return catID;
    }

    public void setCatID(long catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catID=" + catID +
                ", catName='" + catName + '\'' +
                '}';
    }
}
