package com.example.project.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.example.project.activity.user.LoginActivity;
import com.example.project.data.user.LoginData;
import com.example.project.data.user.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.user.LoginResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCreateActivity extends AppCompatActivity {
    private ImageView iv;
    private String text;

    private int afterPoint;

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_createqr);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        int paypoint = intent.getIntExtra("paypoint", 0);
        UserData userInfo = (UserData) intent.getSerializableExtra("userInfo");

        iv = findViewById(R.id.qrcode);

        text = "{userEmail: " + userInfo.getEmail() + ",paypoint: " + paypoint + "}";

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}