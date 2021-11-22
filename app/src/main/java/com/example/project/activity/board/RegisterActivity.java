package com.example.project.activity.board;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.board.PostData;
import com.example.project.data.board.PostRegData;
import com.example.project.data.user.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ServiceApi service;

    private EditText mTitle;
    private EditText mContents;
    private Button mSubmit;

    private ImageButton mBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mTitle = findViewById(R.id.post_Title);
        mContents = findViewById(R.id.post_Content);

        Intent intent = getIntent();

        String utitle = intent.getStringExtra("title");
        String ucontent = intent.getStringExtra("content");

        if(utitle != null) mTitle.setText(utitle);
        if(ucontent != null) mContents.setText(ucontent);

        mSubmit = findViewById(R.id.submitButton);
        mSubmit.setOnClickListener(v -> {
            String title = mTitle.getText().toString();
            String contents = mContents.getText().toString();

            UserData userInfo = (UserData) getIntent().getSerializableExtra("userInfo");
            String type = getIntent().getStringExtra("type");

            if(utitle != null && ucontent != null){
                int id = intent.getIntExtra("post_id", 0);
                updatePOST(new PostData(id, title, contents));
            }else {
                wirtePOST(new PostRegData(type, title, contents, userInfo.getName(), userInfo.getEmail()));
            }
            finish();
        });

        mBack = findViewById(R.id.crossButton);
        mBack.setOnClickListener(v-> finish());


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // 글쓰기
    public void wirtePOST(PostRegData data){
        service.writePOST(data).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "글 작성이 완료되었습니다.", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("글쓰기 에러", t.getMessage());
            }
        });
    }

    public void updatePOST(PostData data){
        service.updatePost(data).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "글 수정이 완료되었습니다.", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("글수정 에러", t.getMessage());
            }
        });

    }
}