package com.b07group4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class OwnersOrders extends AppCompatActivity {

    private ListView ordersListView;
    private ArrayAdapter<Order> ordersAdapter;
    private DatabaseReference ordersReference;
    private List<Order> orderList;

    private String username, storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);

        // get from intent extra
        username = getIntent().getStringExtra("OWNER_NAME");
        storeName = getIntent().getStringExtra("STORE_NAME");


    }



    public void onClickBack(View view){
        Intent intent = new Intent(this, Shop.class);
        intent.putExtra("OWNER_NAME", username);
        intent.putExtra("STORE_NAME", storeName);
        startActivity(intent);
    }
}


