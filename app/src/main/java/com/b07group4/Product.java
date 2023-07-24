package com.b07group4;

public class Product {
    private String productId;
    private double price;
    private int quantity;
    private String imageURL;
    private String storeId;
    public Product(){

    }
    public Product(String productId, String storeId, String imageURL, double price, int quantity){
        this.productId = productId;
        this.storeId = storeId;
        this.imageURL = imageURL;
        this.price = price;
        this.quantity = quantity;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
}
