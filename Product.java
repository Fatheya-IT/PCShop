package com.example.pcshop;

public class Product {
    private final String name, category, description;
    private final double price;
    private final int imageID;

    public Product(String name, String category, String description, double price, int imageID) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageID = imageID;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getImageID() { return imageID; }
}
