package com.example.rms;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name;
    private double price;
    private int imageResource;
    private String category;

    public MenuItem(String name, double price, int imageResource, String category) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.category = category;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getImageResource() { return imageResource; }
    public void setImageResource(int imageResource) { this.imageResource = imageResource; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
