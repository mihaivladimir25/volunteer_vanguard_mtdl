package com.example.proiect_voluntariat;

public class Product {

    private String name;
    private String description;
    private double price;
    private byte[] image;

    public Product(String name, String description, double price, byte[] image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public byte[] getImage() {
        return image;
    }
}