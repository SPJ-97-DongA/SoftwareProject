package com.example.project.network;

import com.example.project.data.JoinData;
import com.example.project.data.PostRegData;
import com.example.project.response.JoinResponse;
import com.example.project.data.LoginData;
import com.example.project.response.ListupResponse;
import com.example.project.response.LoginResponse;
import com.example.project.data.QrData;
import com.example.project.response.PostResponse;
import com.example.project.response.QrResponse;
import com.example.project.data.RegionData;
import com.example.project.response.RegionResponse;
import com.example.project.response.SubregionResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApi {

    //회원가입, 로그인
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);


    // QR
    @POST("/qr/addpoint")
    Call<QrResponse> qrSuccess(@Body QrData data);


    //지도 지역
    @POST("/map/regions")
    Call<RegionResponse> callRegionList();

    @POST("/map/subRegions")
    Call<SubregionResponse> callSubregionList(@Body RegionData data);


    //게시판
    @GET("/board")
    Call<ListupResponse> ListUP(@Query("type") String type, @Query("end") int end);

    @POST("/board/write")
    Call<ResponseBody> writePOST(@Body PostRegData data);

    @POST("/board/posts")
    Call<PostResponse> viewPOST(@Body int post_id);


}
