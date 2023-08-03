package com.b07group4.DBHandler;

import com.b07group4.DataModels.Product;

import java.util.List;
import java.util.Properties;

public class CartManager {
    //implement Singleton
    private static CartManager instance_;

    class Thingy {
        String storeId;
        List<Product> products;
    }

    List<Thingy> things;

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance_ == null)
            instance_ = new CartManager();

        return instance_;
    }
}
