package com.b07group4;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    DatabaseReference db;

    public interface ProductReadCallback {
        void onSuccess(List<Product> productList);
        void onFailure();
    }

    public ProductManager() {
        db = FirebaseDatabase.getInstance().getReference("Products");
    }

    public void ReadAll(ProductReadCallback callback) {
        List<Product> productList = new ArrayList<>();

        db.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        Product product = snapshot.getValue(Product.class);
                        productList.add(product);
                    }
                    callback.onSuccess(productList);
                } else {
                    callback.onFailure();
                }
            }
        });
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