package com.b07group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.ActionBar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.b07group4.DBHandler.DBCallback;
import com.b07group4.DBHandler.ProductManager;
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
//    private DatabaseReference productsRef;
    private ProductManager pm;
    private List<Product> productList;
    private ProductViewAdapter productAdapter;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_products_page);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUser = preferences.getString("currentUser", "");
        String storeName = getIntent().getStringExtra("storeName");
        TextView textViewStoreName = findViewById(R.id.storeName);
        textViewStoreName.setText(storeName);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //if (actionBar != null) {
            //actionBar.setDisplayHomeAsUpEnabled(true);
        //}
        pm = ProductManager.getInstance();
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        productAdapter = new ProductViewAdapter(productList);
        recyclerViewProducts.setAdapter(productAdapter);

        DBCallback<List<Product>> myAmazingCode = l -> {
            productList.clear();
            l.forEach(p -> {
                if (p.getOwner_id().equals(storeName))
                    productList.add(p);
            });
            productAdapter.notifyDataSetChanged();
        };
        pm.GetAll(myAmazingCode);
        pm.AddValueEventListener(myAmazingCode);
    }
    public void onClickBack(View view){
        finish();
    }
}