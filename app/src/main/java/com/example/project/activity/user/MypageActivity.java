package com.example.project.activity.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.user.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageActivity extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView point;
    private TextView mypageChange;
    private TextView mypageLogout;
    private TextView exit;

    private Button pUsePoint;

    private ServiceApi service;

    private UserData userInfo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        userInfo = (UserData) getIntent().getSerializableExtra("userInfo");

        name = findViewById(R.id.mypageName);
        email = findViewById(R.id.mypageEmail);
        point = findViewById(R.id.mypagePoint);
        pUsePoint = findViewById(R.id.mypagePointUse);

        infoReload();

        mypageChange = findViewById(R.id.mypageChange);
        mypageChange.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
            intent.putExtra("userInfo", userInfo);
            startActivity(intent);
        });

        mypageLogout = findViewById(R.id.mypageLogout);
        mypageLogout.setOnClickListener(v -> GotoHome());

        exit = findViewById(R.id.exitService);
        exit.setOnClickListener(v->{
            ServiceExit(userInfo.getEmail());
            GotoHome();
        });

        pUsePoint.setOnClickListener(v->{
            Intent intent = new Intent(this, PointActivity.class);
            startActivity(intent);
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    public void ServiceExit(String email){
        service.userExit(email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(MypageActivity.this, "회원탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("회원탈퇴 에러", t.getMessage());
            }
        });
    }

    public void GotoHome(){
        Intent intentHome = new Intent(this, LoginActivity.class);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intentHome);
        finish();
    }

    public void infoReload(){
        name.setText(userInfo.getName());
        email.setText(userInfo.getEmail());
        point.setText(userInfo.getPoint() + "P");
    }
}