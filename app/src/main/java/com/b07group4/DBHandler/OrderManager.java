package com.b07group4.DBHandler;

import com.b07group4.DataModels.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public void Create(Order order, DBCallback<Order> cb) {
        // TODO
    }

    public void GetAll(DBCallback<List<Order>> cb) {
        // TODO
    }

    public void Get(String id, DBCallback<Order> cb) {
        // TODO
    }

    public void Update(String id, Order data, DBCallback<Order> cb) {
        // TODO
    }

    public void Delete(String id, DBCallback<Void> cb) {
        // TODO
    }
}

