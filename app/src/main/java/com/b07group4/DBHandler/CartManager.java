package com.b07group4.DBHandler;

import com.b07group4.DataModels.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CartManager {
    private static CartManager instance_;

    List<Product> myCart;

    private CartManager() {
        myCart = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance_ == null)
            instance_ = new CartManager();

        return instance_;
    }

    public void Add(Product p) {
        // TODO
    }

    public void Remove(Product p) {
        // TODO
    }

    public void Checkout() {
        // TODO
    }
}
