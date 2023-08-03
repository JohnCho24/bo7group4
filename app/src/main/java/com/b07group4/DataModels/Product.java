package com.b07group4.DataModels;

public class Product {
    private String name;
    private double price;
    private String brand;
    private String info;
    private String id;
    private String owner_id;
    private static final Product placeholder = new Product("Hello", "World", 12.34, "Java is bad!");

    public Product() {
    }

    private Product(String name, String brand, double price, String info) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.info = info;
    }

    public static Product getPlaceholder() {
        return placeholder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}
