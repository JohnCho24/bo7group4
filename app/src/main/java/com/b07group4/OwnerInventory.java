package com.b07group4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Product;

import java.util.List;

public class OwnerInventory extends AppCompatActivity {

    private RecyclerView listView;
    private List<Product> productList;
    ProductManager productManager;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_inventory);

        username = getIntent().getStringExtra("OWNER_NAME");
        listView = findViewById(R.id.productList);
    }

    public void onClickAdd(View v) {
        Intent intent = new Intent(this, CreateProduct.class);
        intent.putExtra("OWNER_NAME", username);
        startActivity(intent);
    }
}