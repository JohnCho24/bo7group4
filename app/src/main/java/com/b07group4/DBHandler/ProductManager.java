package com.b07group4.DBHandler;

import androidx.annotation.NonNull;

import com.b07group4.DataModels.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    static private ProductManager instance_;
    DatabaseReference db;

    private ProductManager() {
        db = FirebaseDatabase.getInstance().getReference("Products");
    }

    static public ProductManager getInstance() {
        if (instance_ == null)
            instance_ = new ProductManager();
        return instance_;
    }

    public List<Product> GetAll() {
        List<Product> productList = new ArrayList<>();

        db.get().getResult().getChildren().forEach(dataSnapshot -> {
            productList.add(dataSnapshot.getValue(Product.class));
        });

        return productList;
    }

    public Product Create(Product product) {
        String key = db.push().getKey();
        db.child(key).setValue(product);
        return product;
    }

    public Product Update(String id, Product data) {
        db.child(id).setValue(data);
        return data;
    }

    public void Delete(String id) {
        db.child(id).removeValue();
    }

}