package com.b07group4.owner_inventory;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Product;
import com.b07group4.R;

public class CreateProduct extends AppCompatActivity {

    ProductManager pm;
    EditText name, brand, price, info;
    String owner_id;
    private String username, storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        // Get from intent extra
        storeName = getIntent().getStringExtra("STORE_NAME");
        owner_id = getIntent().getStringExtra("OWNER_NAME");

        pm = ProductManager.getInstance();
        name = findViewById(R.id.prdName);
        brand = findViewById(R.id.prdBrand);
        price = findViewById(R.id.prdPrice);
        info = findViewById(R.id.prdInfo);
    }

    public void clickAdd(View v) {

        // Make sure fields are filled
        if(TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(brand.getText().toString()) || TextUtils.isEmpty(price.getText().toString()) || TextUtils.isEmpty(info.getText().toString())){
            Toast.makeText(this, "Please complete product information", Toast.LENGTH_SHORT).show();
            return;
        }

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

            // Added to inventory success toast message
            Toast.makeText(this, "Item added successfully!", Toast.LENGTH_SHORT).show();

        }

        catch (Exception e) {
            Log.d("DBG", "Something went wrong when creating product: " + e.getMessage());
        }

        finish();
    }

    //
    public void onClickBack(View view){
        finish();
    }
}