package com.example.rms;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String id;
    private String name;
    private double price;
    private int imageResource;
    private String category;

    public MenuItem() {
        // Required for Firebase
    }

    public MenuItem(String id, String name, double price, int imageResource, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.category = category;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getImageResource() { return imageResource; }
    public void setImageResource(int imageResource) { this.imageResource = imageResource; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
