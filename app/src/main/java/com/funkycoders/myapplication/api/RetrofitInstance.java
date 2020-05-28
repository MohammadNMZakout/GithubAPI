package com.funkycoders.myapplication.api;

import com.funkycoders.myapplication.api.interfaces.GithubServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {
    private final static String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;

    public static GithubServices getService() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubServices.class);
    }


}
