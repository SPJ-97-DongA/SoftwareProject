package com.example.project.network;

import com.example.project.data.JoinData;
import com.example.project.data.JoinResponse;
import com.example.project.data.LoginData;
import com.example.project.data.LoginResponse;
import com.example.project.data.QrData;
import com.example.project.data.QrResponse;
import com.example.project.data.RegionData;
import com.example.project.data.RegionResponse;
import com.example.project.data.SubregionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {

    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

//    @POST("/board")
//    Call<BoardResponse> boardList

    @POST("/qr/addpoint")
    Call<QrResponse> qrSuccess(@Body QrData data);

    @POST("/map/regions")
    Call<RegionResponse> callRegionList();

    @POST("/map/subRegions")
    Call<SubregionResponse> callSubregionList(@Body RegionData data);
}
