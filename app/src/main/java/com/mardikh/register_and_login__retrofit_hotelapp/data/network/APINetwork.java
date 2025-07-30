package com.mardikh.register_and_login__retrofit_hotelapp.data.network;

import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request.LoginRequest;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request.RegisterRequest;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.LoginResponse;
import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APINetwork {

    @POST("/api/oauth/token")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/api/oauth/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);
}
