package com.b07group4.DataModels;

public class Product {
    public String name;
    public double price;
    public String brand;
    public String info;
    public int quantity;
    public String id;
    public String image;

    public Product(String n, double p, String b, String in, int q, String id, String im) {
        this.name = n;
        this.price = p;
        this.brand = b;
        this.info = in;
        this.quantity = q;
        this.id = id;
        this.image = im;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}