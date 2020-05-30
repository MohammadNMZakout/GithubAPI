package com.funkycoders.myapplication.api;

import com.funkycoders.myapplication.api.interfaces.GithubServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.funkycoders.myapplication.utils.Constants.BASE_URL;


public class RetrofitInstance {
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
