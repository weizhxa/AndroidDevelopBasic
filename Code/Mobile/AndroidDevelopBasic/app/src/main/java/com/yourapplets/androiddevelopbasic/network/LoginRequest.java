package com.yourapplets.androiddevelopbasic.network;

import com.yourapplets.androiddevelopbasic.LoginResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRequest {

    @POST("/login")
    Call<LoginResponse> login(@Query("name") String name);
}
