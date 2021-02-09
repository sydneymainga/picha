package com.example.picha;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    //instantiate widgets
    //private ImageView logoutUser;
    //private TextView textViewUserEmail;
    private TextView tv_create_account;

    private FirebaseAuth firebaseAuth;

    private Button btn_commleader;
    private Button btn_healthworker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_commleader = findViewById(R.id.btn_commleader);
        btn_healthworker = findViewById(R.id.btn_healthworker);

        tv_create_account = findViewById(R.id.tv_create_account);


        //calling firebase
        firebaseAuth = FirebaseAuth.getInstance();
        //session checking
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }



        /**
        //getting info for current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //finding view
        //textViewUserEmail = findViewById(R.id.profile_user_email);
        //putting user info in the view
        textViewUserEmail.setText("Welcome " + user.getEmail());
        //finding view
        //logoutUser = findViewById(R.id.imageView_profile_logout);
        //logging out user
        logoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });**/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar actions click
        switch (item.getItemId()) {

            case R.id.action_logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
