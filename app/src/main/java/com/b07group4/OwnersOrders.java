package com.b07group4;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.DataModels.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnersOrders extends AppCompatActivity {

    ListView listView;
    DatabaseReference mDatabase;
    private ArrayAdapter<Orders> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);

        //myListView = (ListView) findViewById(R.id.ownerOrdersListView);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Products");
        listView = findViewById(R.id.ownerOrdersListView);

        ArrayList<Orders> productList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
        listView.setAdapter(adapter);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                    Orders order = productSnapshot.getValue(Orders.class);
                    if (order != null && "jed".equals(order.getStoreId())) {
                        productList.add(order);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("MainActivity", "Failed to read value.", databaseError.toException());
            }
        });
    }


}
