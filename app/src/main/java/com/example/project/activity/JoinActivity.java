package com.example.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.data.JoinData;
import com.example.project.data.JoinResponse;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordViewConfirm;
    private EditText mNameView;
    private Button mJoinButton;
    private Button mCancelButton;
    private ServiceApi service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mEmailView = (EditText) findViewById(R.id.join_email);
        mPasswordView = (EditText) findViewById(R.id.join_password);
        mPasswordViewConfirm = (EditText) findViewById(R.id.join_password_confirm);
        mNameView = (EditText) findViewById(R.id.join_name);
        mJoinButton = (Button) findViewById(R.id.join_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        mJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptJoin();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void attemptJoin() {
        mNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordViewConfirm.setError(null);

        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConfirm = mPasswordViewConfirm.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.setError("유효한 이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mPasswordView.setError("비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        } else if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", password)) {
            mPasswordView.setError("8~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            focusView = mPasswordView;
            cancel = true;
        }


        if(passwordConfirm.isEmpty()) {
            mPasswordView.setError("필수 정보입니다.");
            focusView = mPasswordViewConfirm;
            cancel = true;
        }
        else if(!password.equals(passwordConfirm)){
            mPasswordViewConfirm.setError("비밀번호를 다시 확인해주세요.");
            focusView = mPasswordViewConfirm;
            cancel = true;
        }


        // 이름의 유효성 검사
        if (name.isEmpty()) {
            mNameView.setError("이름을 입력해주세요.");
            focusView = mNameView;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(name, email, password));
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {

            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                    JoinResponse result = response.body();
                    Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                    if (result.getCode() == 200) {
                        finish();
                    }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

}
