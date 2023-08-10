package com.b07group4.auth.shopper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.DBHandler.ShopperAuthModel;
import com.b07group4.DataModels.Owner;
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

public class RegisterPage extends AppCompatActivity implements AuthContract.Register.View {
    AuthContract.Register.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shopper);
        presenter = new RegisterPresenter(this, ShopperAuthModel.getInstance());
    }

    public void clickShopperHome(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void clickShopperLogin(View view){
        finish();
    }

    public void onClickShopperSignup(View view){
        presenter.onClickRegister();
    }

    @Override
    public void showLoading() {
        Toast.makeText(com.b07group4.auth.shopper.RegisterPage.this, "Registering...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onSuccess(User u) {
        Toast.makeText(com.b07group4.auth.shopper.RegisterPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, ShopperPage.class);
        i.putExtra("OWNER_NAME", u.getUsername());
        startActivity(i);
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
