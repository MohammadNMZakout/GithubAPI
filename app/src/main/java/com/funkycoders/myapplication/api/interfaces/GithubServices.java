package com.funkycoders.myapplication.api.interfaces;

import com.funkycoders.myapplication.api.models.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubServices {

    @GET("/users")
    Call<List<UsersResponse>> getUsers(@Query("page") int page);

    @GET("/users/{username}")
    Call<UsersResponse> getUser(@Path("username") String username);
}
