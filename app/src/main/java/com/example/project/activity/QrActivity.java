package com.example.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class QrActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    private Intent intent;

    private String email;

    private int point;

    TextView mUserPoint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserPoint = (TextView) findViewById(R.id.userPoint);

        intent = getIntent();

        email = intent.getStringExtra("email");
        point = intent.getIntExtra("point", 0);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setPrompt("QR 스캔중..");

        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            //qrcode 가 없으면

            if (result.getContents() == null) {
                Toast.makeText(QrActivity.this, "취소!", Toast.LENGTH_SHORT).show();

            } else {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(result.getContents());

                    System.out.println("before : " + point);

                    point += Integer.parseInt(obj.getString("point"));

                    System.out.println("after : " + point);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(QrActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
            }



            if(data != null) {
                data.putExtra("email", email);
                data.putExtra("point", point);
            }

            setResult(resultCode, data);
            finish();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}