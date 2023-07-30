package com.b07group4;

public class Product {
    String productId;
    double price;

    public Product() {
        // Empty constructor needed for Firebase
    }

    public Product(String productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }
}
