package com.example.picha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private Button btnSignup;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //calling fire libraries
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        //getting view by id
        btnSignup = findViewById(R.id.signup_continue);
        editTextEmail = findViewById(R.id.edit_text_signup_email);
        editTextPassword = findViewById(R.id.edit_text_signup_password);
        //what happens when button is clicked?
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();//user is registered
            }
        });
    }

    private void registerUser() {
        //get user input from edit text
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check if fields are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please fill out email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill out password", Toast.LENGTH_SHORT).show();
            return;
        }

        // if validations are OK we first show a progress dialog
        progressDialog.setMessage("Registering user");
        progressDialog.show();
        //registering user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // user is registered successfully
                            Toast.makeText(
                                    SignupActivity.this,
                                    "User registered successfully",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            Toast.makeText(
                                    SignupActivity.this,
                                    "Could not register user, please try again",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
