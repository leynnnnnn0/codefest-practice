package com.example.mycommerce.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import com.example.mycommerce.R;
import com.example.mycommerce.SigninActivity;
import com.example.mycommerce.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword, signupConfirmPassword;
    private Button signupButton;
    private TextView gotToLoginPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        if(getSupportActionBar() != null) getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.signupEmail);
        signupPassword = findViewById(R.id.signupPassword);
        signupConfirmPassword = findViewById(R.id.signupConfirmPassword);
        signupButton = findViewById(R.id.signupButton);
        gotToLoginPageButton = findViewById(R.id.gotToLoginPageButton);

        Log.d("success", "Test 1");
        signupButton.setOnClickListener(view -> {
            Log.d("success", "Test 2");

            String user = signupEmail.getText().toString().trim();
            String pass = signupPassword.getText().toString().trim();

            if(user.isEmpty()){
                signupEmail.setError("Email cannot be empty");
            }

            if(pass.isEmpty()){
                signupPassword.setError("Password cannot be empty");
            }else {
                auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, HomeFragment.class));
                    }else {
                        Toast.makeText(SignupActivity.this, "Signup Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        gotToLoginPageButton.setOnClickListener(view -> {
            startActivity(new Intent(this, SigninActivity.class));
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}