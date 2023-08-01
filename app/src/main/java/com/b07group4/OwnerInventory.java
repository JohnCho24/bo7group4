package com.b07group4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Product;

import java.util.List;

public class OwnerInventory extends AppCompatActivity {

    private ListView listView;
    private List<Product> productList;
    private Button addBtn;
    ProductManager productManager;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_inventory);

        username = getIntent().getStringExtra("OWNER_NAME");

        listView = (ListView) findViewById(R.id.productList);
        addBtn = (Button) findViewById(R.id.addBtn);
    }

    void ClickAdd(View v) {
        Intent i = new Intent(this, CreateProduct.class);
        i.putExtra("OWNER_NAME", "asfahsgfkd");
    }
}