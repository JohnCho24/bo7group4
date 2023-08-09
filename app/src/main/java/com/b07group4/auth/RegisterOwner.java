package com.b07group4.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOwner extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
        db = FirebaseDatabase.getInstance();
    }

    // Back button
    public void onClickBack(View view){
        Intent intent = new Intent(this, LoginOwner.class);
        startActivity(intent);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void onClickSignup(View view){
        DatabaseReference ref = db.getReference();

        // Store username
        EditText userText = (EditText) findViewById(R.id.registerUsername);
        String username = userText.getText().toString();

        // Store name
        EditText userStoreName = (EditText) findViewById(R.id.ownerStoreName) ;
        String storeName = userStoreName.getText().toString();

        // Password
        EditText userPass = (EditText) findViewById(R.id.registerPassword);
        String password = userPass.getText().toString();

        // Password requirements
        if(password.length() < 4){
            Toast.makeText(RegisterOwner.this, "Password needs to be at least 5 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make sure all fields are filled
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(storeName)){
            Toast.makeText(RegisterOwner.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference query = ref.child("Owners").child(username);

        query.addValueEventListener(new ValueEventListener() {
            private boolean isAccountCreated = false;
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                // Check if account is not created yet
                if (!isAccountCreated) {

                    // Account doesn't exist yet
                    if (!snapshot.exists()) {
                        // Blank
                        userText.setText("");
                        userPass.setText("");
                        userStoreName.setText("");

                        // Store
                        ref.child("Owners").child(username).child("password").setValue(password);
                        ref.child("Owners").child(username).child("store_name").setValue(storeName);


                        // Account created message
                        Toast.makeText(RegisterOwner.this, "Account created", Toast.LENGTH_SHORT).show();

                        // Go to next screen
                        Intent intent = new Intent(RegisterOwner.this, LoginOwner.class);
                        startActivity(intent);

                        // Set the flag to true to indicate account creation
                        isAccountCreated = true;
                    }
                    // Account already exists
                    else {
                        // Account already exists
                        Toast.makeText(RegisterOwner.this, "User already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}