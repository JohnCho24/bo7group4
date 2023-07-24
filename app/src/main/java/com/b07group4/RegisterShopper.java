package com.b07group4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.b07group4.DataModels.Shopper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterShopper extends AppCompatActivity {

    TextInputEditText editTextUsernameShopper, editTextPasswordShopper;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shopper);
        mAuth = FirebaseAuth.getInstance();
        editTextUsernameShopper = findViewById(R.id.UsernameShopper);
        editTextPasswordShopper = findViewById(R.id.PasswordShopper);
        buttonReg = findViewById(R.id.RegisterShopperButton);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById((R.id.loginNow));
        textView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginShopper.class);
                startActivity(intent);
                finish();
            }
        }));

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility((View.VISIBLE));
                String username = editTextUsernameShopper.getText().toString();
                String password = editTextPasswordShopper.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterShopper.this, "Fill in all the fields please", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Shopper newShopper = new Shopper(username, password);
                    DBHandler dbHandler = new DBHandler();
                    dbHandler.addShopper(newShopper);
                    Intent intent = new Intent(getApplicationContext(), LoginShopper.class);
                    startActivity(intent);
                    finish();
                }

                /* mAuth.createUserWithEmailAndPassword(username, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility((View.GONE));
                                if (task.isSuccessful()) {
                                    Log.d("RegisterShopper", "Account created successfully.");
                                    Toast.makeText(RegisterShopper.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                    Shopper newShopper = new Shopper(username, password);
                                    DBHandler dbHandler = new DBHandler();
                                    dbHandler.addShopper(newShopper);
                                    Intent intent = new Intent(getApplicationContext(), LoginShopper.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Log.e("RegisterShopper", "Error creating account: " + task.getException());
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterShopper.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }); */
            }
        });
    }
}