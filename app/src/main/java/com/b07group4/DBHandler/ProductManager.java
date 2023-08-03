package com.b07group4.DBHandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.b07group4.DataModels.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public interface DBListener<T> {
        void OnData(T l);
    }

    public void Get(String id, DBListener<Product> listener) {
        db.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                listener.OnData(null);
                return;
            }

            listener.OnData(task.getResult().getValue(Product.class));
        });
    }

    public void GetAll(DBListener<List<Product>> listener) {
        List<Product> productList = new ArrayList<>();

        db.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                listener.OnData(productList);
                return;
            }

            task.getResult().getChildren().forEach(d -> {
                if (d.exists()) productList.add(d.getValue(Product.class));
            });

            listener.OnData(productList);
        });
    }

    public Product Create(Product product) {
        String key = db.push().getKey();
        db.child(key).setValue(product);
        product.setId(key);
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