package com.b07group4.DataModels;

import java.util.ArrayList;
import java.util.List;

public class Owner extends User{
    String storeName;

    private List<Product> products;

    public Owner() {
        products = new ArrayList<>();
    }

    public Owner(String username, String password, String storeName) {
        super(username, password);
        this.storeName = storeName;
        products = new ArrayList<>();
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
