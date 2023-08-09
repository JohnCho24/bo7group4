package com.b07group4.auth.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.b07group4.R;
import com.b07group4.auth.shopper.LoginPage;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void clickOwner(View view){
        Intent intent = new Intent(this, com.b07group4.auth.owner.LoginPage.class);
        startActivity(intent);
    }

    public void clickShopper(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}