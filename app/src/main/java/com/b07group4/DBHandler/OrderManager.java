package com.b07group4.DBHandler;

import android.util.Log;

import androidx.annotation.NonNull;

import com.b07group4.DataModels.Order;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private final DatabaseReference db;
    private static OrderManager instance_;

    private OrderManager() {
        db = FirebaseDatabase.getInstance().getReference("Orders");
    }

    public static OrderManager getInstance() {
        if (instance_ == null)
            instance_ = new OrderManager();
        return instance_;
    }
    public void AddValueEventListener(DBCallback<List<Order>> listener) {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Order> orderList = new ArrayList<>();
                if (snapshot.exists()) {
                    snapshot.getChildren().forEach(d -> {
                        Order order = d.getValue(Order.class);
                        if (d.exists()) order.setId(d.getKey());
                        orderList.add(order);
                    });
                    Log.d("DBG", "Hello order");
                    listener.OnData(orderList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DBG", "Error while doing stuff with Orders on FB: " + error.getDetails());
            }
        });
    }

    public void Create(Order order, DBCallback<Order> cb) {
        String orderId = db.push().getKey();
        db.child(orderId).setValue(order);
        order.setId(orderId);
        cb.OnData(order);
    }

    public void GetAll(DBCallback<List<Order>> cb) {
        List<Order> orders = new ArrayList<>();
        Task<DataSnapshot> t = db.get();
        if (cb != null)
            t.addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    cb.OnData(null);
                    return;
                }

                task.getResult().getChildren().forEach(d -> {
                    if (d.exists()) {
                        Order o = d.getValue(Order.class);
                        if (o != null) o.setId(d.getKey());
                        orders.add(o);
                    }
                });

                cb.OnData(orders);
            });
    }

    public void Get(String id, DBCallback<Order> cb) {
        db.child(id).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                cb.OnData(null);
                return;
            }

            cb.OnData(task.getResult().getValue(Order.class));
        });
    }

    public void Finish(String id, String username, DBCallback<Order> cb) {
        db
                .child(id)
                .child("subStoreOrders")
                .child(username)
                .child("orderStatus")
                .setValue(Order.OrderStatus.DONE);
        cb.OnData(null);
    }
}

