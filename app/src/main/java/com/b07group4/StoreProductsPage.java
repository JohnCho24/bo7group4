package com.b07group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.b07group4.DataModels.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreProductsPage extends AppCompatActivity {
    private RecyclerView recyclerViewProducts;
    private DatabaseReference productsRef;
    private List<Product> productList;
    private ProductViewAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_products_page);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUser = preferences.getString("currentUser", "");
        String storeName = getIntent().getStringExtra("storeName");
        //Log.d("StoreProductsPage", "Store Name: " + storeName);
        productsRef = FirebaseDatabase.getInstance().getReference("Products");
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productAdapter = new ProductViewAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);
        productsRef.orderByChild("storeId").equalTo(storeName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    if (product != null) {
                        productList.add(product);
                    }
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(StoreProductsPage.this, "Failed to retrieve products.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}