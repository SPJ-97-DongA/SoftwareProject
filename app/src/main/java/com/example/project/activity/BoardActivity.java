package com.example.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;
import com.example.project.data.RegionData;
import com.example.project.network.ServiceApi;
import com.example.project.response.ListupResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardActivity extends AppCompatActivity {

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


    }

    // 게시글 목록
    public void listUP(String type){
        service.ListUP(type).enqueue(new Callback<ListupResponse>() {
            @Override
            public void onResponse(Call<ListupResponse> call, Response<ListupResponse> response) {
                ListupResponse result = response.body();
                List<RegionData> postList = result.getBoardList();

            }

            @Override
            public void onFailure(Call<ListupResponse> call, Throwable t) {

            }
        });
    }


    // 글쓰기
    public void wirtePOST(){
        service.writePOST("test").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}