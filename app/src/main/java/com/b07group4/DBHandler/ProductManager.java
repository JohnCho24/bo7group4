package com.b07group4.DBHandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.b07group4.DataModels.Product;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public interface DBListener<T> {
        void OnData(T data);
    }

    public void AddValueEventListener(DBListener<List<Product>> listener) {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Product> list = new ArrayList<>();
                if (snapshot.exists()) {
                    snapshot
                            .getChildren()
                            .forEach(d -> {
                                Product p = d.getValue(Product.class);
                                if (d.exists()) p.setId(d.getKey());
                                list.add(p);
                            });
                    Log.d("DBG", "Hello world");
                    listener.OnData(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DBG", "Error while doing stuff with Products on FB: " + error.getDetails());
            }
        });
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

        Task<DataSnapshot> t = db.get();
        if (listener != null)
            t.addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    listener.OnData(productList);
                    return;
                }

                task.getResult().getChildren().forEach(d -> {
                    if (d.exists()) {
                        Product p = d.getValue(Product.class);
                        if (p != null) p.setId(d.getKey());
                        productList.add(p);
                    }
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

    public void Update(String id, Product data, DBListener<Product> listener) {

        if (id == null)
            return;

        List<Task<Void>> tasks = new ArrayList<>();
        tasks.add(db.child(id).child("name").setValue(data.getName()));
        tasks.add(db.child(id).child("brand").setValue(data.getBrand()));
        tasks.add(db.child(id).child("price").setValue(data.getPrice()));
        tasks.add(db.child(id).child("info").setValue(data.getInfo()));

        if (listener != null)
            Tasks.whenAll(tasks).addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    listener.OnData(null);
                    return;
                }

                listener.OnData(data);
            });
    }

    public void Delete(String id) {
        db.child(id).removeValue();
    }

}