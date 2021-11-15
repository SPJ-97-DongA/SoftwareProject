package com.example.project.activity;

import android.content.Intent;
import android.os.Bundle;
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

    private int point;

    TextView textViewpoint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        point = intent.getIntExtra("point", 0);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false);
        qrScan.setPrompt("QR 스캔중..");

        qrScan.initiateScan();
        textViewpoint = (TextView) findViewById(R.id.textView2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QrActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(QrActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject obj = new JSONObject(result.getContents());


                    if(obj.getString("difficulty") == "1")
                    {
                        point += 1000;
                    }
                    else if(obj.getString("difficulty") == "2")
                    {
                        point += 2000;
                    }
                    else if(obj.getString("difficulty") == "3")
                    {
                        point += 3000;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }



}
//package com.example.project.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//
//public class QrActivity extends AppCompatActivity {
//
//    private IntentIntegrator qrScan;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        qrScan = new IntentIntegrator(this);
//        qrScan.setOrientationLocked(false);
//        qrScan.setPrompt("QR 스캔중..");
//
//        qrScan.initiateScan();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//
//        if (result != null) {
//            //qrcode 가 없으면
//            if (result.getContents() == null) {
//                Toast.makeText(QrActivity.this, "취소!", Toast.LENGTH_SHORT).show();
//            } else {
//                //qrcode 결과가 있으면
//                Toast.makeText(QrActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
//
//                try {
//                    JSONObject obj = new JSONObject(result.getContents());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//
//        finish();
//    }
//
//
//
//}
