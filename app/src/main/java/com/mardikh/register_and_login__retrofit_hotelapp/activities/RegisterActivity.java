package com.mardikh.register_and_login__retrofit_hotelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mardikh.register_and_login__retrofit_hotelapp.R;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request.RegisterRequest;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.ErrorResponse;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.RegisterResponse;
import com.mardikh.register_and_login__retrofit_hotelapp.data.network.APIClient;
import com.mardikh.register_and_login__retrofit_hotelapp.data.network.APINetwork;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtUsername, edtEmail, edtPhone, edtPassword, edtConfirmPassword;
    Button btnRegister;
    TextView tvLogin;
    APINetwork api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin); // Make sure you have this TextView in your XML

        api = APIClient.getClient().create(APINetwork.class);

        btnRegister.setOnClickListener(v -> registerUser());

        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String firstName = edtFirstName.getText().toString().trim();
        String lastName = edtLastName.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty()
                || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest request = new RegisterRequest(
                firstName, lastName, username, email, phone, password, confirmPassword, "NON", "USER"
        );

        api.register(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, response.body().getMessageKh(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("RegisterError", errorBody);
                        Gson gson = new Gson();
                        ErrorResponse error = gson.fromJson(errorBody, ErrorResponse.class);
                        Toast.makeText(RegisterActivity.this, error.getMessageKh(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error parsing error response", Toast.LENGTH_SHORT).show();
                        Log.e("RegisterCatch", e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Connection failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("RegisterFailure", t.getMessage());
            }
        });
    }
}
