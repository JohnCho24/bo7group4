package com.b07group4.auth.owner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.b07group4.DBHandler.OwnerAuthModel;
import com.b07group4.DataModels.Owner;
import com.b07group4.DataModels.User;
import com.b07group4.R;
import com.b07group4.Shop;
import com.b07group4.auth.AuthContract;

public class RegisterPage extends AppCompatActivity implements AuthContract.Register.View {
    AuthContract.Register.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
        presenter = new RegisterPresenter(this, OwnerAuthModel.getInstance());
    }

    // Back button
    public void onClickBack(View view){
        finish();
    }

    public void onClickHome(View view){
        finish();
    }

    public void onClickSignup(View view){
        presenter.onClickRegister();
    }

    @Override
    public void showLoading() {
        Toast.makeText(com.b07group4.auth.owner.RegisterPage.this, "Registering...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onSuccess(User u) {
        Toast.makeText(com.b07group4.auth.owner.RegisterPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Shop.class);
        i.putExtra("OWNER_NAME", u.getUsername());
        i.putExtra("STORE_NAME", ((Owner)u).getStoreName());
        startActivity(i);
    }

    @Override
    public void onFailure() {
        Toast.makeText(com.b07group4.auth.owner.RegisterPage.this, "Registration failed!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public User getUser() {
        Owner o = new Owner();

        EditText userText = (EditText) findViewById(R.id.registerUsername);
        String username = userText.getText().toString();

        EditText userPass = (EditText) findViewById(R.id.registerPassword);
        String password = userPass.getText().toString();

        EditText userStoreName = (EditText) findViewById(R.id.ownerStoreName) ;
        String storeName = userStoreName.getText().toString();

        o.setUsername(username);
        o.setPassword(password);
        o.setStoreName(storeName);

        return o;
    }
}