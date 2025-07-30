package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("password")
    private String password;

    @SerializedName("confirmPassword")
    private String confirmPassword;

    @SerializedName("profile")
    private String profile;

    @SerializedName("role")
    private String role;

    public RegisterRequest(String firstName, String lastName, String username, String email,
                           String phoneNumber, String password, String confirmPassword,
                           String profile, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.profile = profile;
        this.role = role;
    }
}
