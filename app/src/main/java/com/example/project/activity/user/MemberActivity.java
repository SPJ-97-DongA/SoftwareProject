package com.example.project.activity.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.user.UserData;
import com.example.project.data.user.UpdateData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.user.UserinfoResponse;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberActivity extends AppCompatActivity {

    private TextView mEmail;

    private EditText mName;
    private EditText mCurrentpw;
    private EditText mNewpw;

    private Button mSubmit;

    private UserData userInfo;

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        userInfo = (UserData) getIntent().getSerializableExtra("userInfo");

        //레이아웃에서 id 받아오기
        mEmail = findViewById(R.id.memberCurrentEmail);
        mName = findViewById(R.id.memberName);
        mCurrentpw = findViewById(R.id.memberCurrentPw);
        mNewpw = findViewById(R.id.memberNewPw);
        mSubmit = findViewById(R.id.memberSubmit);

        mEmail.setText(userInfo.getEmail());
        mName.setText(userInfo.getName());

        mSubmit.setOnClickListener(v -> {

                String name = mName.getText().toString();
                String PW = mCurrentpw.getText().toString();
                String nPW = mNewpw.getText().toString();

                if(PW.equals(nPW)){
                    mNewpw.setError("현재 비밀번호와 다른 비밀번호를 입력해주세요.");
                } else if(pwCheck(PW, mCurrentpw) && pwCheck(nPW, mNewpw)) {
                    userInfoUpdate(new UpdateData(userInfo.getEmail(), name, PW, nPW));
                }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public boolean pwCheck(String passwd, EditText v){
        // 패스워드의 유효성 검사
        if (passwd.isEmpty()) {
            v.setError("비밀번호를 입력해주세요.");
            return false;
        } else if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", passwd)) {
            v.setError("8~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            return false;
        }

        return true;
    }

    public void userInfoUpdate(UpdateData data){
        service.userInfoUpdate(data).enqueue(new Callback<UserinfoResponse>() {
            @Override
            public void onResponse(Call<UserinfoResponse> call, Response<UserinfoResponse> response) {
                UserinfoResponse result = response.body();

                Toast.makeText(MemberActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                if(result.getCode() == 200){
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserinfoResponse> call, Throwable t) {
                Toast.makeText(MemberActivity.this, "유저정보 업데이트에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                Log.e("유저정보 업데이트 에러", t.getMessage());
            }
        });
    }
}