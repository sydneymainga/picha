package com.example.picha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView resetPassword;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //calling fire base libraries & progress dialogue
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //finding view by id
        btnLogin = findViewById(R.id.login_continue);
        editTextEmail = findViewById(R.id.edit_text_login_email);
        editTextPassword = findViewById(R.id.edit_text_login_password);
        resetPassword = findViewById(R.id.login_forgot);
        //session checking/checking if there is current user
        if (firebaseAuth.getCurrentUser() != null) {
            // profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
            }
        });

    }

    private void loginUser() {
        //getting user input
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if fields are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(
                    this, "Please fill out email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(
                    this, "Please fill out password", Toast.LENGTH_SHORT).show();
            return;
        }

        // if validations are OK we first show a progress dialog
        progressDialog.setMessage("Logging in");
        progressDialog.show();

        //login user

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // user is registered successfully
                            Toast.makeText(
                                    LoginActivity.this,
                                    "Login successful",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            Toast.makeText(
                                    LoginActivity.this,
                                    "Could not login user, please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}

