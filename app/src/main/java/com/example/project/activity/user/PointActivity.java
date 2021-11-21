package com.example.project.activity.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activity.QrCreateActivity;
import com.example.project.data.user.UserData;

public class PointActivity extends AppCompatActivity {

    private TextView havePoint;
    private EditText usePoint;
    private Button mSubmit;

    private UserData userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_point);

        Intent preIntent = getIntent();
        userInfo = (UserData) preIntent.getSerializableExtra("userInfo");

        int hvPoint = userInfo.getPoint();

        havePoint = findViewById(R.id.pointHavepoint);
        havePoint.setText(String.valueOf(hvPoint));

        usePoint = findViewById(R.id.pointUsepoint);

        mSubmit = findViewById(R.id.pointSubmit);
        mSubmit.setOnClickListener(v -> {

            String pointText = usePoint.getText().toString();
            if(pointText.equals("")){
                usePoint.setError("사용할 포인트를 입력해주세요.");
                usePoint.requestFocus();
            }else {

                int paypoint = Integer.parseInt(pointText);

                if (paypoint > hvPoint || paypoint < 0) {
                    usePoint.setError("현재 가진 포인트를 다시 확인해주세요.");
                    usePoint.requestFocus();
                } else {
                    Intent intent = new Intent(this, QrCreateActivity.class);
                    intent.putExtra("paypoint", paypoint);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }


//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }


}