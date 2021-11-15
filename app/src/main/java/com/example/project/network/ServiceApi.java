package com.example.project.network;

import com.example.project.data.BoardResponse;
import com.example.project.data.JoinData;
import com.example.project.data.JoinResponse;
import com.example.project.data.LoginData;
import com.example.project.data.LoginResponse;
import com.example.project.data.QrData;
import com.example.project.data.QrResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

//    @POST("/board")
//    Call<BoardResponse> boardList

    @POST("/qr/update")
    Call<QrResponse> qrSuccess(@Body QrData data);

}
