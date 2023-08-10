package com.b07group4.DBHandler;

import android.text.TextUtils;
import android.util.Log;

import com.b07group4.DataModels.Shopper;
import com.b07group4.DataModels.User;
import com.b07group4.auth.AuthContract;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.atomic.AtomicBoolean;

public class ShopperAuthModel implements AuthContract.Login.Model, AuthContract.Register.Model {
    private static ShopperAuthModel instance_;
    private DatabaseReference db;

    private ShopperAuthModel() {}

    public static ShopperAuthModel getInstance() {
        if (instance_ == null)
            instance_ = new ShopperAuthModel();
        return instance_;
    }
    @Override
    public void login(User u, DBCallback<User> cb) {
        db.child(u.getUsername()).get().addOnCompleteListener(d -> {
            if (!d.isSuccessful()) {
                cb.OnData(null);
            } else {
                try {
                    Shopper s = d.getResult().getValue(Shopper.class);
                    if (s == null || !s.getPassword().equals(u.getPassword())) {
                        cb.OnData(null);
                    }

                    cb.OnData(s);
                } catch (Exception e) {
                    Log.d("DBG", e.getMessage());
                    cb.OnData(null);
                }
            }
        });
    }

    @Override
    public void register(User u, DBCallback<User> cb) {
        if (!(u instanceof Shopper)) {
            Log.d("DBG", "Not a shopper!");
            cb.OnData(null);
            return;
        }
        String password = u.getPassword();
        String username = u.getUsername();

        // Password requirements
        if(password.length() < 4){
            cb.OnData(null);
            return;
        }

        // Make sure all fields are filled
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            cb.OnData(null);
            return;
        }

        AtomicBoolean created = new AtomicBoolean(false);

        DatabaseReference ref = db.child(username);
        ref.get().addOnCompleteListener(d -> {
            if (!created.get()) {
                if (!d.isSuccessful()) {
                    ref.setValue(u);
                    created.set(true);
                }
                else {
                    cb.OnData(null);
                }
            }
        });
    }
}
