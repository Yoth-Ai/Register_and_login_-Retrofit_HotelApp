package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("profile")
    private String profile;

    @SerializedName("status")
    private String status;

    @SerializedName("roles")
    private List<RolesItem> roles;

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getProfile() { return profile; }
    public String getStatus() { return status; }
    public List<RolesItem> getRoles() { return roles; }
}
