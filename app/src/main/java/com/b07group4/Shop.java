package com.b07group4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Shop extends AppCompatActivity {

    private String ownerName;
    private TextView textViewStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        textViewStore = findViewById(R.id.textViewStore);

        // Get the owner's name from the intent extra
        ownerName = getIntent().getStringExtra("OWNER_NAME");

        // Query the database to fetch the store ID based on the owner's name
        DatabaseReference ownersRef = FirebaseDatabase.getInstance().getReference("Owners");
        ownersRef.child(ownerName).child("StoreID").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Store ID found, set it to the textViewStore
                    String storeId = dataSnapshot.getValue(String.class);
                    textViewStore.setText(storeId);
                }

                else {
                    // Owner or store not found, handle the case
                    textViewStore.setText("Store not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database read error if needed
                textViewStore.setText("Error fetching store ID");
            }
        });

//        Button btnOrders = findViewById(R.id.btnOrders);
//        Button btnInventory = findViewById(R.id.btnInventory);
//
//        btnOrders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Redirect to OwnersOrders activity
//                Intent intent = new Intent(Shop.this, OwnersOrders.class);
//                startActivity(intent);
//            }
//        });
//
//        btnInventory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Redirect to OwnerInventory activity
//                Intent intent = new Intent(Shop.this, OwnerInventory.class);
//                startActivity(intent);
//            }
//        });

    }

    // Button to go to orders page
    public void onClickOrders(View view){
        Intent intent = new Intent(this, OwnersOrders.class);
        startActivity(intent);
    }

    // Button to go to inventory page
    public void onClickInventory(View view){
        Intent intent = new Intent(this, OwnerInventory.class);
        startActivity(intent);
    }
}