package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("messageKh")
    private String messageKh;

    @SerializedName("code")
    private String code;

    public String getMessageKh() {
        return messageKh;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
