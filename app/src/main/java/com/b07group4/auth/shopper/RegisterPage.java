package com.b07group4.auth.shopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.DataModels.Owner;
import com.b07group4.DataModels.Shopper;
import com.b07group4.DataModels.User;
import com.b07group4.R;
import com.b07group4.auth.AuthContract;
import com.b07group4.auth.home.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterPage extends AppCompatActivity implements AuthContract.Register.View {

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
        Intent intent = new Intent(this, LoginPage.class);
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
            Toast.makeText(RegisterPage.this, "Password needs to be at least 5 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make sure all fields are filled
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(RegisterPage.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RegisterPage.this, "Account created", Toast.LENGTH_SHORT).show();

                        // Go to next screen
                        Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                        startActivity(intent);

                        // Set the flag to true to indicate account creation
                        isAccountCreated = true;
                    }
                    // Account already exists
                    else {
                        // Account already exists
                        Toast.makeText(RegisterPage.this, "User already exists.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }

        });
    }

    @Override
    public void showLoading() {
        Toast.makeText(com.b07group4.auth.shopper.RegisterPage.this, "Registering...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onSuccess() {
        Toast.makeText(com.b07group4.auth.shopper.RegisterPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(com.b07group4.auth.shopper.RegisterPage.this, "Registration failed!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public User getUser() {
        Shopper s = new Shopper();

        EditText userText = (EditText) findViewById(R.id.registerUsername);
        String username = userText.getText().toString();

        EditText userPass = (EditText) findViewById(R.id.registerPassword);
        String password = userPass.getText().toString();

        s.setUsername(username);
        s.setPassword(password);

        return s;
    }
}
