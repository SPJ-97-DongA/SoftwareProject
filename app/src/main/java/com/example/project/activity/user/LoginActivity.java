package com.example.project.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.activity.MainActivity;
import com.example.project.data.user.LoginData;
import com.example.project.data.user.UserData;
import com.example.project.response.user.LoginResponse;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mEmailLoginButton;
    private Button mJoinButton;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        mEmailLoginButton = findViewById(R.id.login_button);
        mJoinButton = findViewById(R.id.join_button);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mEmailLoginButton.setOnClickListener(view -> attemptLogin());

        mJoinButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);

            startActivity(intent);
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mEmailView.setText(null);
        mPasswordView.setText(null);

        mEmailView.requestFocus();
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }


        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mPasswordView.setError("비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(email, password));
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();

                UserData userInfo = new UserData(result.getUserName(), mEmailView.getText().toString(), result.getPoint());

                if(result.getCode() == 200) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    intent.putExtra("userInfo", userInfo);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }

}