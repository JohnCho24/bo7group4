package com.b07group4.DBHandler;

import androidx.annotation.NonNull;

import com.b07group4.DataModels.Owner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShopManager {
    static private ShopManager instance_;
    DatabaseReference db;

    private ShopManager() {
        db = FirebaseDatabase.getInstance().getReference("Owners");
    }

    public static ShopManager getInstance() {
        if (instance_ == null)
            instance_ = new ShopManager();
        return instance_;
    }

    public void GetAll(@NonNull DBCallback<List<Owner>> cb) {
        db.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Owner> owners = new ArrayList<>();
                for (DataSnapshot d : task.getResult().getChildren())
                    if (d.exists())
                        owners.add(d.getValue(Owner.class));
                cb.OnData(owners);
            } else {
                cb.OnData(null);
            }
        });
    }

    public void Get(String id, DBCallback<Owner> cb) {
        db.child(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                cb.OnData(task.getResult().getValue(Owner.class));
            } else {
                cb.OnData(null);
            }
        });
    }
}
