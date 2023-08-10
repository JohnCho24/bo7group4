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

public class LoginPage extends AppCompatActivity implements AuthContract.Login.View {
    private AuthContract.Login.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_owner);
        presenter = new LoginPresenter(this, OwnerAuthModel.getInstance());
    }

    public void clickHome(View view){
        finish();
    }

    public void clickSignup(View view){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void onClickOwnerLogin(View view){
        presenter.onClickLogin();
    }

    @Override
    public void showLoading() {
        Toast.makeText(com.b07group4.auth.owner.LoginPage.this, "Logging in...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onSuccess(User u) {
        Toast.makeText(com.b07group4.auth.owner.LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Shop.class);
        i.putExtra("OWNER_NAME", u.getUsername());
        i.putExtra("STORE_NAME", ((Owner)u).getStoreName());
        startActivity(i);
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