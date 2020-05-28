package com.funkycoders.myapplication.api.interfaces;

import com.funkycoders.myapplication.api.models.GithubUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubServices {

    @GET("/users")
    Call<List<GithubUserResponse>> getUsers(@Query("page") int page);

    @GET("/users/{username}")
    Call<GithubUserResponse> getUser(@Path("username") String username);
}
