package com.b07group4.DataModels;

import com.b07group4.DataModels.Product;

import java.util.List;

public class Store {
    private String storeName;
    private List<Product> products;

    public Store(String storeName){
        this.storeName = storeName;
    }

    public Store(String storeName, List<Product> products) {
        this.storeName = storeName;
        this.products = products;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}