package com.mardikh.register_and_login__retrofit_hotelapp.data.remote.repository;

import com.mardikh.register_and_login__retrofit_hotelapp.data.remote.models.response.LoginResponse;

public interface LoginCallBack {
    void onLoading();
    void onSuccess(LoginResponse loginResponse);
    void onError(String message);
}
