package com.mardikh.register_and_login__retrofit_hotelapp.data.network;

import com.mardikh.register_and_login__retrofit_hotelapp.BuildConfig; // ✅ Import this

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL) // ✅ Use BuildConfig value
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
