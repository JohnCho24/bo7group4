package com.b07group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginShopper extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shopper);
        db = FirebaseDatabase.getInstance();
    }

    public void clickShopperHome(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void clickShopperSignup(View view){
        Intent intent = new Intent(this, RegisterShopper.class);
        startActivity(intent);
    }

    public void onClickShopperLogin(View view){

        // Username
        EditText userText = (EditText) findViewById(R.id.shopperUsername);
        String username = userText.getText().toString();

        // Password
        EditText userPass = (EditText) findViewById(R.id.shopperPassword);
        String password = userPass.getText().toString();

        // Blank
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter username & password", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference usersRef = db.getReference("Shoppers");
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // User exists
                if (dataSnapshot.exists()) {

                    // Check if password is correct
                    if(dataSnapshot.child("password").getValue(String.class).equals(password)){
                        Toast.makeText(LoginShopper.this, "Login Successful ", Toast.LENGTH_SHORT).show();

                        // Blank
                        userText.setText("");
                        userPass.setText("");

                        //

                    }
                    else{
                        Toast.makeText(LoginShopper.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        userPass.setText("");
                    }
                }

                // User not found
                else {
                    Toast.makeText(LoginShopper.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}