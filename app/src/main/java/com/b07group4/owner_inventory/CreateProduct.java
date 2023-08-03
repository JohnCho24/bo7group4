package com.b07group4.owner_inventory;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Product;
import com.b07group4.R;

public class CreateProduct extends AppCompatActivity {

    ProductManager pm;
    EditText name, brand, price, info;
    String owner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        owner_id = getIntent().getStringExtra("OWNER_NAME");

        pm = ProductManager.getInstance();
        name = findViewById(R.id.prdName);
        brand = findViewById(R.id.prdBrand);
        price = findViewById(R.id.prdPrice);
        info = findViewById(R.id.prdInfo);
    }

    public void clickAdd(View v) {
        Product p = new Product();
        p.setName(name.getText().toString());
        p.setBrand(brand.getText().toString());
        p.setPrice(parseDouble(price.getText().toString()));
        p.setInfo(info.getText().toString());
        p.setOwner_id(owner_id);

        try {
            p = pm.Create(p);
            // TODO redirect to OwnerInventory
            Log.d("DBG", "Created product: " + p.getId());
        } catch (Exception e) {
            Log.d("DBG", "Something went wrong when creating product: " + e.getMessage());
        }
    }
}