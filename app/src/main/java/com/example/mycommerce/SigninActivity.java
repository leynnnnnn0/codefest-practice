package com.example.mycommerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mycommerce.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button signinButton;
    private EditText signinEmail, signinPassword;
    private TextView gotToSignupPageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);

        if(getSupportActionBar() != null) getSupportActionBar().hide();

        signinButton = findViewById(R.id.signinButton);
        signinEmail = findViewById(R.id.signinEmail);
        signinPassword = findViewById(R.id.signinPassword);
        auth = FirebaseAuth.getInstance();
        gotToSignupPageButton = findViewById(R.id.gotToSignupPageButton);

        signinButton.setOnClickListener(view -> {
            String email = signinEmail.getText().toString().trim();
            String password = signinPassword.getText().toString().trim();

            if(email.isEmpty()){
                signinEmail.setError("Email cannot be empty.");
            }
           if(password.isEmpty()){
               signinPassword.setError("Password cannot be emtpy");
           }else {
               auth.signInWithEmailAndPassword(email, password)
                       .addOnSuccessListener(authResult -> {
                   Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(this, MainActivity.class));
                   finish();
               }).addOnFailureListener(e -> {
                           Toast.makeText(this, "Login Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
               });
           }
        });
        gotToSignupPageButton.setOnClickListener(view -> startActivity(new Intent(this, SigninActivity.class)));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}