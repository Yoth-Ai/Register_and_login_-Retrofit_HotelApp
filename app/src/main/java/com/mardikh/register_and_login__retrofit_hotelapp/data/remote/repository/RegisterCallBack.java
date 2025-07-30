package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.repository;

import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.RegisterResponse;

public interface RegisterCallBack {
    void onLoading();
    void onSuccess(RegisterResponse response);
    void onError(String message);
}
