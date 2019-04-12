package com.example.demo;


import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Product {

    private long ID;
    private String name;
    private String description;
    private String imageURL;
    private Category proCat;
    private Manufacturer proMan;

    public Product(){}

    public Product(long ID, String name, String description, String imageURL, Category proCat, Manufacturer proMan) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.proCat = proCat;
        this.proMan = proMan;
    }

    public Category getProCat() {
        return proCat;
    }

    public void setProCat(Category proCat) {
        this.proCat = proCat;
    }

    public Manufacturer getProMan() {
        return proMan;
    }

    public void setProMan(Manufacturer proMan) {
        this.proMan = proMan;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", proCat=" + proCat +
                ", proMan=" + proMan +
                '}';
    }

    @GetMapping("/product")
    public String getMapping(Product p){
        p.setDescription("DESR");
        p.setID(123l);
        p.setImageURL("URL");
        p.setName("FILIP");
        p.setProCat(new Category(1, "categorijaaa"));
        p.setProMan(new Manufacturer(1, "Sony"));
        return p.toString();
    }



}
