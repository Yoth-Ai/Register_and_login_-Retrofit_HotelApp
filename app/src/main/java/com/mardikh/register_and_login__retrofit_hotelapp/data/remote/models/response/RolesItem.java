package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response;

import com.google.gson.annotations.SerializedName;

public class RolesItem {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
