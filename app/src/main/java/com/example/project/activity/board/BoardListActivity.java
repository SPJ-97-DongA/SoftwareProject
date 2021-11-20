package com.example.project.activity.board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.PostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardListActivity extends AppCompatActivity {

    private TextView mUsername;
    private TextView mTitle;
    private TextView mDate;
    private TextView mContents;

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

//        service = RetrofitClient.getClient().create(ServiceApi.class);
//
//        Intent intent = getIntent();
//        int post_id = intent.getIntExtra("post_id", 0);
//
//        mUsername = findViewById(R.id.boardlistId);
//        mTitle = findViewById(R.id.boardlistTitle);
//        mDate = findViewById(R.id.boardlistTime);
//        mContents = findViewById(R.id.boardlistContent);
//
//        viewPOST(post_id);
    }

//    public void viewPOST(int post_id){
//        service.viewPOST(post_id).enqueue(new Callback<PostResponse>() {
//            @Override
//            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
//                PostResponse result = response.body();
//
//                mUsername.setText(result.getWriter());
//                mTitle.setText(result.getTitle());
//                mDate.setText(result.getDateTime());
//                mContents.setText(result.getContents());
//            }
//
//            @Override
//            public void onFailure(Call<PostResponse> call, Throwable t) {
//                Log.e("글 읽기 에러", t.getMessage());
//            }
//        });
//    }
}