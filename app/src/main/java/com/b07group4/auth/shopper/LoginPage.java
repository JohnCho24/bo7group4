package com.b07group4.auth.shopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.DataModels.Shopper;
import com.b07group4.DataModels.User;
import com.b07group4.R;
import com.b07group4.ShopperPage;
import com.b07group4.auth.AuthContract;
import com.b07group4.auth.home.HomePage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity implements AuthContract.Login.View {
    private AuthContract.Login.Presenter presenter;
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
        Intent intent = new Intent(this, RegisterPage.class);
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
                        Toast.makeText(LoginPage.this, "Login Successful ", Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);// helps you remember login info when in different pages
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("currentUser", username);
                        editor.apply();

                        // Redirect to Shopper page after login
                        Intent intent = new Intent(LoginPage.this, ShopperPage.class);
                        startActivity(intent);

                        // Blank
                        userText.setText("");
                        userPass.setText("");

                        //

                    }
                    else{
                        Toast.makeText(LoginPage.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        userPass.setText("");
                    }
                }

                // User not found
                else {
                    Toast.makeText(LoginPage.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
            public void clearData() {
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void showLoading() {
        Toast.makeText(com.b07group4.auth.shopper.LoginPage.this, "Logging in...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onSuccess() {
        Toast.makeText(com.b07group4.auth.shopper.LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(com.b07group4.auth.shopper.LoginPage.this, "Login Failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public User getUser() {
        Shopper s = new Shopper();

        EditText userText = (EditText) findViewById(R.id.shopperUsername);
        String username = userText.getText().toString();

        EditText userPass = (EditText) findViewById(R.id.shopperPassword);
        String password = userPass.getText().toString();

        s.setUsername(username);
        s.setPassword(password);

        return s;
    }
}