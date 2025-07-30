package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.repository;

import com.mardikh.register_and_login__retrofit_hotelapp.data.network.APIClient;
import com.mardikh.register_and_login__retrofit_hotelapp.data.network.APINetwork;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request.LoginRequest;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request.RegisterRequest;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.LoginResponse;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final APINetwork apiNetwork;

    public AuthRepository() {
        apiNetwork = APIClient.getClient().create(APINetwork.class);
    }

    public void login(String phoneNumber, String password, LoginCallBack loginCallBack) {
        loginCallBack.onLoading();
        LoginRequest loginRequest = new LoginRequest(phoneNumber, password);
        apiNetwork.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginCallBack.onSuccess(response.body());
                } else {
                    loginCallBack.onError("Invalid phone number or password");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginCallBack.onError("Login failed: " + t.getMessage());
            }
        });
    }

    public void register(RegisterRequest request, RegisterCallBack registerCallBack) {
        registerCallBack.onLoading();
        apiNetwork.register(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    registerCallBack.onSuccess(response.body());
                } else {
                    registerCallBack.onError("Register failed"+ response.code());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerCallBack.onError("Network error: " + t.getMessage());
            }
        });
    }
}
