package com.b07group4.owner_orders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class OwnersOrders extends AppCompatActivity {

    private DatabaseReference db;
    private List<String> productIdList;

    private String username, storeName;

    OrderAdapter myAdapter;
    ExpandableListView expandableListView;
    private List<String> orderId;
    private List<String> orderStatus;
    private HashMap<String, List<String>> itemIdHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("ORDER", "IN ORDER");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);
        db = FirebaseDatabase.getInstance().getReference();

        username = getIntent().getStringExtra("OWNER_NAME");
        storeName = getIntent().getStringExtra("STORE_NAME");

        expandableListView = findViewById(R.id.expandableListView);
        productIdList = new ArrayList<>();

        showList();

        myAdapter = new OrderAdapter(this, orderId, orderStatus, itemIdHash);
        expandableListView.setAdapter(myAdapter);

    }

    public void showList(){

        orderId = new ArrayList<String>();
        orderStatus = new ArrayList<String>();
        itemIdHash = new HashMap<String, List<String>>();

        DatabaseReference ordersRef = db.child("Orders");

        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    DataSnapshot ownerOrderSnapshot = orderSnapshot.child("subStoreOrders").child(username);

                    // Not found
                    if (!ownerOrderSnapshot.exists()) {
                        continue;
                    }

                    // Get
                    String id = ownerOrderSnapshot.child("productIdList").getValue(String.class);
                    String orderStatusValue = ownerOrderSnapshot.child("orderStatus").getValue(String.class);

                    // Order status & id
                    orderId.add(orderSnapshot.getKey());
                    orderStatus.add(ownerOrderSnapshot.child("orderStatus").getValue(String.class));


                    if (id != null) {
                        // Split
                        String[] productIds = id.split(",\\s*");
                        for (String productId : productIds) {
                            productIdList.add(productId);
                        }

                        // Populate hash map
                        itemIdHash.put(orderStatusValue, new ArrayList<>(productIdList));
                        productIdList.clear();
                    }
                }

                myAdapter.notifyDataSetChanged();

                // No orders
                if (itemIdHash.isEmpty()) {
                    TextView tv = findViewById(R.id.textViewHeader);
                    tv.setText("NO ONGOING ORDERS");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    // Back button
    public void onClickBack(View view){
        finish();
    }
}


