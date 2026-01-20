package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private boolean isProceeded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Fail-safe: If nothing happens in 7 seconds, go to Login
        new Handler().postDelayed(() -> {
            if (!isProceeded) {
                Log.d("Splash", "Fail-safe triggered, going to login");
                goToLogin();
            }
        }, 7000);

        try {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                fetchUserData(currentUser.getUid());
            } else {
                new Handler().postDelayed(this::goToLogin, 2000);
            }
        } catch (Exception e) {
            Log.e("Splash", "Firebase Error: " + e.getMessage());
            goToLogin();
        }
    }

    private void fetchUserData(String uid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (isProceeded) return;
                
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    DataManager.currentUser = user;
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    isProceeded = true;
                    finish();
                } else {
                    Log.d("Splash", "User data not found in database");
                    goToLogin();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("Splash", "Database Error: " + error.getMessage());
                if (!isProceeded) goToLogin();
            }
        });
    }

    private void goToLogin() {
        if (isProceeded) return;
        isProceeded = true;
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
