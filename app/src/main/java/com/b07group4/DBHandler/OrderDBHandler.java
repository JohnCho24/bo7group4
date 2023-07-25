package com.b07group4.DBHandler;

import com.b07group4.DataModels.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderDBHandler {
    private DatabaseReference ordersRef;

    public OrderDBHandler() {
        ordersRef = FirebaseDatabase.getInstance().getReference("Orders");
    }
    // Adding a new order to the database
    public void addOrder(Order order) {
        ordersRef.child(order.getOrderId()).setValue(order);
    }
}

