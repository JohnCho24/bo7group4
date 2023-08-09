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

import com.b07group4.DBHandler.OrderManager;
import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Product;
import com.b07group4.DataModels.SubStoreOrder;
import com.b07group4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class OwnersOrders extends AppCompatActivity {

    private DatabaseReference db;
    private ProductManager pm;
    private OrderManager om;

    private String username, storeName;

    OrderAdapter myAdapter;
    ExpandableListView expandableListView;
    private List<Order> orders;
    private HashMap<String, List<Product>> itemIdHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_orders);
        pm = ProductManager.getInstance();
        om = OrderManager.getInstance();

        username = getIntent().getStringExtra("OWNER_NAME");
        storeName = getIntent().getStringExtra("STORE_NAME");

        expandableListView = findViewById(R.id.expandableListView);

        itemIdHash = new HashMap<>();
        orders = new ArrayList<>();
        myAdapter = new OrderAdapter(this, username, orders, itemIdHash);
        expandableListView.setAdapter(myAdapter);

        showList();
    }

    public void showList(){

        orders.clear();
        itemIdHash.clear();

        pm.GetAll(allProducts -> {
            om.GetAll(list -> {
                for (Order o : list) {
                    if (o.getSubStoreOrders().containsKey(username)) {
                        orders.add(o);
                        itemIdHash.put(o.getId(), new ArrayList<>());

                        SubStoreOrder sso = o.getSubStoreOrders().get(username);

                        String[] ids = sso != null ? sso.getProductIdList().split(",\\s*") : new String[]{};

                        for (String id : ids) {
                            for (Product pp : allProducts) {
                                if (pp.getId().equals(id)) {
                                    itemIdHash.get(o.getId()).add(pp);
                                    break;
                                }
                            }
                        }
                    }
                }
                myAdapter.notifyDataSetChanged();
            });
        });
    }




    // Back button
    public void onClickBack(View view){
        finish();
    }
}


