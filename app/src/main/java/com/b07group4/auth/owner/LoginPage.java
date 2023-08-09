package com.b07group4.auth.owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.DataModels.Owner;
import com.b07group4.DataModels.User;
import com.b07group4.R;
import com.b07group4.Shop;
import com.b07group4.auth.AuthContract;
import com.b07group4.auth.home.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity implements AuthContract.Login.View {

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
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void onClickOwnerLogin(View view){

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
                        Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        // Blank
                        userText.setText("");
                        userPass.setText("");

                        String storeName = dataSnapshot.child("store_name").getValue(String.class);
                        // Redirect to the Shop activity and pass the owner's name
                        Intent intent = new Intent(LoginPage.this, Shop.class);
                        intent.putExtra("OWNER_NAME", username); // Pass the owner's name to the next activity
                        intent.putExtra("STORE_NAME", storeName);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginPage.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        userPass.setText("");
                    }
                }

                // User not found
                else {
                    Toast.makeText(LoginPage.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void showLoading() {
        Toast.makeText(com.b07group4.auth.owner.LoginPage.this, "Logging in...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onSuccess() {
        Toast.makeText(com.b07group4.auth.owner.LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(com.b07group4.auth.owner.LoginPage.this, "Login failed!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public User getUser() {
        Owner o = new Owner();

        EditText userText = (EditText) findViewById(R.id.ownerUsername);
        String username = userText.getText().toString();

        EditText userPass = (EditText) findViewById(R.id.ownerPassword);
        String password = userPass.getText().toString();

        o.setUsername(username);
        o.setPassword(password);

        return o;
    }
}