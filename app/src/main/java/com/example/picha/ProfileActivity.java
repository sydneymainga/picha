package com.example.picha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    //instantiate widgets
    private ImageView logoutUser;
    private TextView textViewUserEmail;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //calling firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //session checking
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        //getting info for current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //finding view
        textViewUserEmail = findViewById(R.id.profile_user_email);
        //putting user info in the view
        textViewUserEmail.setText("Welcome " + user.getEmail());
        //finding view
        logoutUser = findViewById(R.id.imageView_profile_logout);
        //logging out user
        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

}
