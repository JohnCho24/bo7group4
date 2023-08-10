package com.b07group4.shopper_orders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.b07group4.DBHandler.OrderManager;
import com.b07group4.DBHandler.ProductManager;
import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Product;
import com.b07group4.DataModels.SubStoreOrder;
import com.b07group4.LoginOwner;
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

public class ShopperOrders extends AppCompatActivity {

    private DatabaseReference db;
    private ProductManager pm;
    private OrderManager om;
    private String username;

    OrderAdapter myAdapter;
    ExpandableListView expandableListView;
    private List<Order> orders;
    private List<String> storeOwner;
    private HashMap<String, List<Product>> itemIdHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopper_orders);
        pm = ProductManager.getInstance();
        om = OrderManager.getInstance();

        username = getIntent().getStringExtra("SHOPPER_NAME");

        expandableListView = findViewById(R.id.expandableListView);


        itemIdHash = new HashMap<>();
        orders = new ArrayList<>();
        myAdapter = new OrderAdapter(this, username, orders, itemIdHash, storeOwner);
//        myAdapter.setOnClick(oid -> {
//            om.Finish(oid, storeOwner, o -> {
//                for(Order rd : orders){
//                    if(rd.getId().equals(oid)){
//                        rd.getSubStoreOrders().get(storeOwner).setOrderStatus(Order.OrderStatus.DONE);
//                        myAdapter.notifyDataSetChanged();
//                        break;
//                    }
//                }
//
//
//                // Refresh & Success
//                Toast.makeText(this, "Order Status Changed", Toast.LENGTH_SHORT).show();
//            });
//        });
        expandableListView.setAdapter(myAdapter);

        showList();
    }

    public void showList(){

        orders.clear();
        itemIdHash.clear();

        pm.GetAll(allProducts -> {
            om.GetAll(list -> {
                for (Order o : list) {
                    String userid = o.getShopperId();
                    String ord = o.getId();
                    if (userid.equals(username)) {
                        String [] storeOwner = db.child(ord).child("subStoreOrders").get
                        orders.add(o);
                        itemIdHash.put(o.getId(), new ArrayList<>());

                        SubStoreOrder sso = o.getSubStoreOrders().get(storeOwner);

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
