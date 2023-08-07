package com.b07group4.owner_orders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Product;
import com.b07group4.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnersOrders extends AppCompatActivity {



    private String username, storeName;


    private DatabaseReference databaseReference;
    private ListView productListView;
    private List<Product> productList;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);

        //Database instance
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // List view

        // get from intent extra
        username = getIntent().getStringExtra("OWNER_NAME");
        storeName = getIntent().getStringExtra("STORE_NAME");


    }





    // Back button
    public void onClickBack(View view){
        finish();
    }
}


