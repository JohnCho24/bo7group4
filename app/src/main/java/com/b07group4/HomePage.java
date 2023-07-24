package com.b07group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void clickOwner(View view){
        Intent intent = new Intent(this, LoginOwner.class);
        startActivity(intent);
    }

    public void clickShopper(View view){
        Intent intent = new Intent(this, LoginShopper.class);
        startActivity(intent);
    }
}