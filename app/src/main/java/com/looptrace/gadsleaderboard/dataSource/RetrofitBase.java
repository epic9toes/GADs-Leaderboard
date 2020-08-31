package com.looptrace.gadsleaderboard.dataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBase {

    private static RetrofitBase instance = null;
    public static final String BASE_URL = "https://gadsapi.herokuapp.com/api/";

    public static RetrofitBase getInstance() {
        if (instance == null) {
            instance = new RetrofitBase();
        }

        return instance;
    }

    public Retrofit buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
