package com.b07group4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.owner_inventory.OwnerInventory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Shop extends AppCompatActivity {

    private String ownerName, storeName;
    private TextView textViewStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        textViewStore = findViewById(R.id.textViewStore);

        // Get the owner's name from the intent extra
        ownerName = getIntent().getStringExtra("OWNER_NAME");
        storeName = getIntent().getStringExtra("STORE_NAME");

        textViewStore.setText(storeName + " shop home page");

    }

    // Button to go to orders page
    public void onClickOrders(View view){
        Intent intent = new Intent(this, OwnersOrders.class);
        intent.putExtra("OWNER_NAME", ownerName);
        intent.putExtra("STORE_NAME", storeName);
        startActivity(intent);
    }

    // Button to go to inventory page
    public void onClickInventory(View view){
        Intent intent = new Intent(this, OwnerInventory.class);
        intent.putExtra("OWNER_NAME", ownerName);
        intent.putExtra("STORE_NAME", storeName);
        startActivity(intent);
    }

    // Logout button that goes to home page
    public void onClickLogout(View v){
        Intent i = new Intent(this, HomePage.class);
        startActivity(i);
    }

}