package com.example.apifxejemplo;

public class Product {
    private int id;
    private String name;
    private String category;
    private String image;
    private String description;
    private double price;

    public Product() {
        this.name = "Zapatillas";
        this.category = "Deporte";
        //this.image = image;
        this.description = "zapatillas molonas";
        this.price = 100000;

    }

    public Product(int id, String name, String category, String image, String description, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return this.category +": "+this.name;
    }
}
