package com.b07group4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Owner;
import com.b07group4.auth.home.HomePage;
import com.b07group4.shopper_orders.ShopperOrders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShopperPage extends AppCompatActivity {

    private RecyclerView recyclerViewStores;
    private DatabaseReference ownersRef;
    private ProductManager pm;
    private List<Owner> storeList;
    private StoreViewAdapter storeViewAdapter;
    private String shopperName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_page);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUser = preferences.getString("currentUser", null);
        //Log.d("user", currentUser);
        shopperName = getIntent().getStringExtra("SHOPPER_NAME");
        recyclerViewStores = findViewById(R.id.recyclerViewStores);
        recyclerViewStores.setLayoutManager(new GridLayoutManager(this, 1));
        ownersRef = FirebaseDatabase.getInstance().getReference("Owners");
        storeList = new ArrayList<>();
        storeViewAdapter = new StoreViewAdapter(storeList);
        recyclerViewStores.setAdapter(storeViewAdapter);
        pm = ProductManager.getInstance();
        ownersRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) return;

            for (DataSnapshot d : task.getResult().getChildren()) {
                if (d.exists()) {
                    Owner store = d.getValue(Owner.class);
                    if (store != null) {
                        store.setUsername(d.getKey());
                        storeList.add(store);
                    }
                }
            }

            pm.GetAll(list -> {
                list.forEach(p -> {
                    storeList.forEach(s -> {
                        if (s.getUsername().equals(p.getOwner_id()))
                            s.getProducts().add(p);
                    });
                });

                storeViewAdapter.notifyDataSetChanged();
            });

            storeViewAdapter.notifyDataSetChanged();
        });

        storeViewAdapter.setOnItemClickListener(position -> {
            String clickedStore = storeList.get(position).getStoreName();
            Intent intent = new Intent(ShopperPage.this, StoreProductsPage.class);
            intent.putExtra("storeName", clickedStore);
            startActivity(intent);
        });
    }
    public void clickCart(View view){
        Intent intent = new Intent(this, ShopperCart.class);
        startActivity(intent);
    }

    public void clickPrevOrders(View view){
        Intent intent = new Intent(this, ShopperOrders.class);
        intent.putExtra("SHOPPER_NAME", shopperName);
        startActivity(intent);
    }

    public void onClickLogout(View v){
        Intent i = new Intent(this, HomePage.class);
        Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}
