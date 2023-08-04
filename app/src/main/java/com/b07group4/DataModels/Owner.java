package com.b07group4.DataModels;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    String username;
    String storeName;
    String password;

    private List<Product> products;

    public Owner() {
        products = new ArrayList<>();
    }

    public Owner(String username, String storeName, String password) {
        this.username = username;
        this.storeName = storeName;
        this.password = password;
        products = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
