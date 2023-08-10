package com.b07group4.DBHandler;

import android.text.TextUtils;
import android.util.Log;

import com.b07group4.DataModels.Owner;
import com.b07group4.DataModels.User;
import com.b07group4.auth.AuthContract;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.atomic.AtomicBoolean;

public class OwnerAuthModel implements AuthContract.Login.Model, AuthContract.Register.Model {
    private static OwnerAuthModel instance_;
    private DatabaseReference db;

    private OwnerAuthModel() {
        db = FirebaseDatabase.getInstance().getReference("Owners");
    }

    public static OwnerAuthModel getInstance() {
        if (instance_ == null)
            instance_ = new OwnerAuthModel();
        return instance_;
    }
    @Override
    public void login(User u, DBCallback<User> cb) {
        db.child(u.getUsername()).get().addOnCompleteListener(d -> {
            if (!d.isSuccessful()) {
                cb.OnData(null);
            } else {
                try {
                    Owner o = d.getResult().getValue(Owner.class);
                    if (o == null || !o.getPassword().equals(u.getPassword())) {
                        cb.OnData(null);
                        return;
                    }

                    o.setUsername(u.getUsername());
                    cb.OnData(o);
                } catch (Exception e) {
                    Log.d("DBG", e.getMessage());
                    cb.OnData(null);
                }
            }
        });
    }

    @Override
    public void register(User u, DBCallback<User> cb) {
        if (!(u instanceof Owner)) {
            Log.d("DBG", "Not an owner!");
            cb.OnData(null);
            return;
        }
        Owner o = (Owner) u;
        String password = o.getPassword();
        String username = o.getUsername();
        String storeName = o.getStoreName();

        // Password requirements
        if(password.length() < 4){
            cb.OnData(null);
            return;
        }

        // Make sure all fields are filled
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(storeName)){
            cb.OnData(null);
            return;
        }

        AtomicBoolean created = new AtomicBoolean(false);

        DatabaseReference ref = db.child(username);
        ref.get().addOnCompleteListener(d -> {
            if (!created.get()) {
                if (d.isSuccessful() && !d.getResult().exists()) {
                    ref.setValue(o);
                    created.set(true);
                    cb.OnData(o);
                }
                else {
                    cb.OnData(null);
                }
            }
        });
    }
}
