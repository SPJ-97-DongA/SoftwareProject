package com.example.project.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.QrData;
import com.example.project.data.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.QrResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {

    private ImageButton mSearch;
    private ImageButton mMap;
    private ImageButton mQr;
    private ImageButton mMyPage;

    private UserData userInfo;

    private Intent intent;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        intent = getIntent();
        userInfo = (UserData) intent.getSerializableExtra("userInfo");


        mSearch = findViewById(R.id.mainSearch);
        mMap = findViewById(R.id.mainMap);
        mQr = findViewById(R.id.mainQr);
        mMyPage = findViewById(R.id.mainMypage);

        mSearch.setOnClickListener(v -> {
        });

        mMap.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        });

        mQr.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), QrActivity.class);
            intent.putExtra("userInfo", userInfo);

            startActivityForResult(intent, 0);
        });

        mMyPage.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), MypageActivity.class);
            intent.putExtra("userInfo", userInfo);
            startActivity(intent);
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            UserData userInfo = (UserData) data.getSerializableExtra("userInfo");

            this.userInfo.setPoint(userInfo.getPoint());
            QrData qrData = new QrData(userInfo.getEmail(), userInfo.getPoint());
            infoUpdate(qrData);
        }
    }


    public void infoUpdate(QrData qrdata){
        service.qrSuccess(qrdata).enqueue(new Callback<QrResponse>() {
            @Override
            public void onResponse(Call<QrResponse> call, Response<QrResponse> response) {
                QrResponse result = response.body();

                getIntent().putExtra("userInfo", userInfo);
            }

            @Override
            public void onFailure(Call<QrResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "포인트 갱신 실패!", Toast.LENGTH_SHORT).show();
                Log.e("포인트 갱신 실패!", t.getMessage());
            }
        });
    }

}