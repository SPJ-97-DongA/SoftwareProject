package com.example.project.network;

import com.example.project.data.JoinData;
import com.example.project.data.JoinResponse;
import com.example.project.data.LoginData;
import com.example.project.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}
