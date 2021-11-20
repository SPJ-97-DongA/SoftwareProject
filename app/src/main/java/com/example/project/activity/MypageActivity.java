package com.example.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.data.UserData;
import com.example.project.network.ServiceApi;

public class MypageActivity extends AppCompatActivity {

    private TextView name, email, point, mypageChange;
    private Button pSubmit;

    private ServiceApi service;

    private UserData userInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        userInfo = (UserData) getIntent().getSerializableExtra("userInfo");

        name = (TextView) findViewById(R.id.mypageName);
        email = (TextView) findViewById(R.id.mypageEmail);
        point = (TextView) findViewById(R.id.mypagePoint);
        pSubmit = findViewById(R.id.mypageSubmit);

        mypageChange = findViewById(R.id.mypageChange);
        mypageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberActivity.class);
                startActivity(intent);
            }
        });

        infoReload();


        pSubmit.setOnClickListener(v->{

        });
    }

    public void infoReload(){
        name.setText(userInfo.getName());
        email.setText(userInfo.getEmail());
        point.setText(userInfo.getPoint() + "P");
    }
}