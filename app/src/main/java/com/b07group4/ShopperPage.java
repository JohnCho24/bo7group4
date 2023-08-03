package com.b07group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.b07group4.DataModels.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopperPage extends AppCompatActivity {

    private RecyclerView recyclerViewStores;
    private DatabaseReference ownersRef;
    private List<Store> storeList;
    private StoreViewAdapter storeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_page);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUser = preferences.getString("currentUser", "");
        //Log.d("user", currentUser);
        recyclerViewStores = findViewById(R.id.recyclerViewStores);
        recyclerViewStores.setLayoutManager(new GridLayoutManager(this, 1));
        ownersRef = FirebaseDatabase.getInstance().getReference("Owners");
        storeList = new ArrayList<>();
        storeViewAdapter = new StoreViewAdapter(storeList);
        recyclerViewStores.setAdapter(storeViewAdapter);

        ownersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot storeSnapshot : dataSnapshot.getChildren()) {
                    String ownerUsername = storeSnapshot.getKey();
                    Store store = new Store(ownerUsername);
                    if (store != null) {
                        storeList.add(store);
                    }
                }
                storeViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShopperPage.this, "Failed to retrieve stores.", Toast.LENGTH_SHORT).show();
            }

        });

        storeViewAdapter.setOnItemClickListener(new StoreViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String clickedStore = storeList.get(position).getStoreName();
                //Log.d("ShopperPage", "Clicked Store: " + clickedStore);
                Intent intent = new Intent(ShopperPage.this, StoreProductsPage.class);
                intent.putExtra("storeName", clickedStore);
                startActivity(intent);
            }
        });
    }
}
