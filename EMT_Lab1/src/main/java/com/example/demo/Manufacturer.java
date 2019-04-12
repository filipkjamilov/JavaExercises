package com.example.demo;

public class Manufacturer {

    private long manID;
    private String manName;

    public Manufacturer(){}

    public Manufacturer(long manID, String manName) {
        this.manID = manID;
        this.manName = manName;
    }

    public long getManID() {
        return manID;
    }

    public void setManID(long manID) {
        this.manID = manID;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "manID=" + manID +
                ", manName='" + manName + '\'' +
                '}';
    }
}
