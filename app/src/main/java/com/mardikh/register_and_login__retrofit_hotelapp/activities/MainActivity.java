package com.mardikh.register_and_login__retrofit_hotelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.mardikh.register_and_login__retrofit_hotelapp.R;
import com.mardikh.register_and_login__retrofit_hotelapp.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(v -> {
            new SessionManager(this).logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
