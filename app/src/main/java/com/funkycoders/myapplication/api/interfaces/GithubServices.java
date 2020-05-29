package com.funkycoders.myapplication.api.interfaces;

import com.funkycoders.myapplication.api.models.UserResponse;
import com.funkycoders.myapplication.api.models.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubServices {

    @GET("/users")
    Call<List<UsersResponse>> getUsers(@Query("per_page") int perPage, @Query("page") int page);

    @GET("/users/{username}")
    Call<UserResponse> getUser(@Path("username") String username);

    @GET("/users/{username}/followers")
    Call<List<UsersResponse>> getFollowers(@Path("username") String username);

    @GET("/users/{username}/following")
    Call<List<UsersResponse>> getFollowings(@Path("username") String username);

}
