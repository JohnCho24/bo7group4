package com.b07group4.DataModels;

public class Product {
    private String brand;
    private String imageURL;
    private String info;
    private String name;
    private double price;
    private String storeId;

    public Product() {
    }
    public Product(String brand, String imageURL, String info, String name, double price,  String storeId) {
        this.brand = brand;
        this.imageURL = imageURL;
        this.info = info;
        this.name = name;
        this.price = price;
        this.storeId = storeId;
    }

    public String getBrand() {
        return brand;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public String getStoreId() {
        return storeId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
