package com.b07group4.owner_inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Product;
import com.b07group4.R;

public class EditProduct extends AppCompatActivity {

    TextView name, brand, price, info;
    String prodId, username, storeName;
    ProductManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        pm = ProductManager.getInstance();

        username = getIntent().getStringExtra("OWNER_NAME");
        storeName = getIntent().getStringExtra("STORE_NAME");
        prodId = getIntent().getStringExtra("PRODUCT_ID");

        name = findViewById(R.id.name);
        brand = findViewById(R.id.brand);
        price = findViewById(R.id.price);
        info = findViewById(R.id.info);

        name.setText(getIntent().getStringExtra("PRODUCT_NAME"));
        brand.setText(getIntent().getStringExtra("PRODUCT_BRAND"));
        price.setText(getIntent().getStringExtra("PRODUCT_PRICE"));
        info.setText(getIntent().getStringExtra("PRODUCT_INFO"));
    }

    public void onClickEdit(View v) {
        pm.Update(prodId, new Product(
                name.getText().toString(),
                brand.getText().toString(),
                Double.parseDouble(price.getText().toString()),
                info.getText().toString()),
                null);
        finish();
        Toast.makeText(this, "Item edited", Toast.LENGTH_SHORT).show();

    }
}