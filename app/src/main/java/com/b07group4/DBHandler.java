package com.b07group4;

import com.b07group4.DataModels.Order;
import com.b07group4.DataModels.Owner;
import com.b07group4.DataModels.Product;
import com.b07group4.DataModels.Shopper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBHandler {
    private DatabaseReference ordersRef;
    private DatabaseReference ownersRef;
    private DatabaseReference shoppersRef;
    private DatabaseReference productsRef;

    protected DBHandler() {
        ordersRef = FirebaseDatabase.getInstance().getReference("Orders");
        ownersRef = FirebaseDatabase.getInstance().getReference("Owners");
        shoppersRef = FirebaseDatabase.getInstance().getReference("Shoppers");
        productsRef = FirebaseDatabase.getInstance().getReference("Products");
    }


    // Adding a new order to the database
    public void addOrder(Order order) {
        ordersRef.child(order.getOrderId()).setValue(order);
    }

    // Adding a new owner to the database
    public void addOwner(Owner owner) {
        ownersRef.child(owner.getId()).setValue(owner);
    }

    //Adding a new shopper to the database
    public void addShopper(Shopper shopper) {
        shoppersRef.child(shopper.getId()).setValue(shopper);
    }

    // Adding a new product to the database
    public void addProduct(Product product) {
        productsRef.child(product.getProductId()).setValue(product);
    }
}
