package com.mardikh.register_and_login__retrofit_hotelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mardikh.register_and_login__retrofit_hotelapp.R;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request.LoginRequest;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.ErrorResponse;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.LoginResponse;
import com.mardikh.register_and_login__retrofit_hotelapp.data.network.APIClient;
import com.mardikh.register_and_login__retrofit_hotelapp.data.network.APINetwork;
import com.mardikh.register_and_login__retrofit_hotelapp.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etPhone, etPassword;
    Button btnLogin;
    TextView tvRegister;
    SessionManager sessionManager;
    APINetwork api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        sessionManager = new SessionManager(this);
        api = APIClient.getClient().create(APINetwork.class);

        // Auto login
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(v -> login());

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void login() {
        String phone = etPhone.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (phone.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest();
        request.setPhoneNumber(phone);
        request.setPassword(pass);

        api.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    sessionManager.saveToken(response.body().getAccessToken());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    try {
                        Gson gson = new Gson();
                        ErrorResponse error = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                        Toast.makeText(LoginActivity.this, error.getMessageKh(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
