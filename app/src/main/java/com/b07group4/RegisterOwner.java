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

    public void onClickSignup(View view){
        DatabaseReference ref = db.getReference();

        // Store username as all lowercase
        EditText userText = (EditText) findViewById(R.id.registerUsername);
        String username = userText.getText().toString();

        // Store
        EditText userStore = (EditText) findViewById(R.id.registerStoreName);
        String storeID = userStore.getText().toString();

        // Password
        EditText userPass = (EditText) findViewById(R.id.registerPassword);
        String password = userPass.getText().toString();

        // Password requirements
        if(password.length() < 4){
            Toast.makeText(RegisterOwner.this, "Password needs to be at least 5 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make sure all fields are filled
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(storeID) || TextUtils.isEmpty(password)){
            Toast.makeText(RegisterOwner.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference query = ref.child("Owners").child(username);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(!snapshot.exists())
                {
                    // Blank
                    userText.setText("");
                    userStore.setText("");
                    userPass.setText("");

                    //
                    //Owner newOwner = new Owner(username, storeID, password);

                    // Store
                    ref.child("Owners").child(username).child("StoreID").setValue(storeID);
                    ref.child("Owners").child(username).child("password").setValue(password);

                    // Account created message
                    Toast.makeText(RegisterOwner.this, "Account created", Toast.LENGTH_SHORT).show();

                    // Go to next screen
                    Intent intent = new Intent(RegisterOwner.this, LoginOwner.class);
                    startActivity(intent);
                }

                // Account already exists
                else{
                    Toast.makeText(RegisterOwner.this, "User already exists.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}