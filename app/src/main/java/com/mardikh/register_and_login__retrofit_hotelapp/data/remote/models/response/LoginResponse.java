package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("expiresIn")
    private int expiresIn;

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("tokenType")
    private String tokenType;

    @SerializedName("user")
    private User user;

    @SerializedName("refreshToken")
    private String refreshToken;

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public User getUser() {
        return user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
