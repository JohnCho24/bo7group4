package com.b07group4.DBHandler;

import com.b07group4.DataModels.Shopper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopperDBHandler {
    private DatabaseReference shoppersRef;

    public ShopperDBHandler() {
        shoppersRef = FirebaseDatabase.getInstance().getReference("Shoppers");
    }
    //Adding a new shopper to the database
    public void addShopper(Shopper shopper) {
        shoppersRef.child(shopper.getId()).setValue(shopper);
    }
}
