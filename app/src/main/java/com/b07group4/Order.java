package com.b07group4;

public class Order {

    String orderstatus;
    String brand;
    String info;
    String name;
    double price;
    int quantity;
    String storeId;

    public Order(String orderstatus, String brand, String info, String name, double price, int quantity, String storeId) {
        this.orderstatus = orderstatus;
        this.brand = brand;
        this.info = info;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.storeId = storeId;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
