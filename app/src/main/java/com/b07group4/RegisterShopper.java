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

public class RegisterShopper extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shopper);
        db = FirebaseDatabase.getInstance();
    }

    public void clickShopperHome(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void clickShopperLogin(View view){
        Intent intent = new Intent(this, LoginShopper.class);
        startActivity(intent);
    }

    public void onClickShopperSignup(View view){
        DatabaseReference ref = db.getReference();

        // Store username as all lowercase
        EditText userText = (EditText) findViewById(R.id.registerUsername);
        String username = userText.getText().toString();

        // Password
        EditText userPass = (EditText) findViewById(R.id.registerPassword);
        String password = userPass.getText().toString();

        // Password requirements
        if(password.length() < 4){
            Toast.makeText(RegisterShopper.this, "Password needs to be at least 5 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make sure all fields are filled
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(RegisterShopper.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference query = ref.child("Shoppers").child(username);

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

                        String currOrderID = ref.push().getKey();

                        // Store
                        ref.child("Shoppers").child(username).child("password").setValue(password);
                        ref.child("Shoppers").child(username).child("currOrderID").setValue(currOrderID);

                        // Account created message
                        Toast.makeText(RegisterShopper.this, "Account created", Toast.LENGTH_SHORT).show();

                        // Go to next screen
                        Intent intent = new Intent(RegisterShopper.this, LoginShopper.class);
                        startActivity(intent);

                        // Set the flag to true to indicate account creation
                        isAccountCreated = true;
                    }
                    // Account already exists
                    else {
                        // Account already exists
                        Toast.makeText(RegisterShopper.this, "User already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }

        });
    }
}
