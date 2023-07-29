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

public class LoginOwner extends AppCompatActivity {

    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_owner);
        db = FirebaseDatabase.getInstance();
    }

    public void clickHome(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void clickSignup(View view){
        Intent intent = new Intent(this, RegisterOwner.class);
        startActivity(intent);
    }

    public void onClickLogin(View view) {

        // Username
        EditText userText = (EditText) findViewById(R.id.ownerUsername);
        String username = userText.getText().toString();

        // Password
        EditText userPass = (EditText) findViewById(R.id.ownerPassword);
        String password = userPass.getText().toString();

        // Make sure fields are filled
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter username & password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Database stuff
        DatabaseReference usersRef = db.getReference("Owners");
        usersRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // User exists
                if (dataSnapshot.exists()) {

                    // Check if password is correct
                    if (dataSnapshot.child("password").getValue(String.class).equals(password)) {
                        Toast.makeText(LoginOwner.this, "Login Successful ", Toast.LENGTH_SHORT).show();

                        // Blank
                        userText.setText("");
                        userPass.setText("");

                        // Redirect to the Shop activity and pass the owner's name
                        Intent intent = new Intent(LoginOwner.this, Shop.class);
                        intent.putExtra("OWNER_NAME", username); // Pass the owner's name to the next activity
                        startActivityForResult(intent, 1);

                    } else {
                        Toast.makeText(LoginOwner.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        userPass.setText("");
                    }
                }

                // User not found
                else {
                    Toast.makeText(LoginOwner.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    // Add this method to handle the result from the Shop activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Handle the result from the Shop activity, if needed
            } else if (resultCode == RESULT_CANCELED) {
                // Handle the case where the Shop activity was cancelled, if needed
            }
        }
    }
}