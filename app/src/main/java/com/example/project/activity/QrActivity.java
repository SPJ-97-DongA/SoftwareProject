package com.example.project.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.project.data.RegionDetailData;
import com.example.project.data.user.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.SubregionResponse;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QrActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    private Intent intent;

    private UserData userInfo;

    private ServiceApi service;

    private LocationManager locationManager;
    private Location lastKnownLocation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        intent = getIntent();

        userInfo = (UserData) intent.getSerializableExtra("userInfo");

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

            int point = userInfo.getPoint();

            if (result.getContents() == null) {
                Toast.makeText(QrActivity.this, "취소!", Toast.LENGTH_SHORT).show();

            } else {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(result.getContents());

                    double mlat, mlng;

                    double lat = Double.parseDouble(obj.getString("latitude"));
                    double lng = Double.parseDouble(obj.getString("longitude"));

                    if(lastKnownLocation != null){
                        mlat = lastKnownLocation.getLatitude();
                        mlng = lastKnownLocation.getLongitude();

                        double distMeter = distance(mlat, mlng, lat, lng);

                        if(distMeter <= 200.0) {
                            point += Integer.parseInt(obj.getString("point"));
                            Toast.makeText(QrActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(QrActivity.this, "비정상 접근입니다.", Toast.LENGTH_SHORT).show();
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            if(data != null) {
                userInfo.setPoint(point);
                data.putExtra("userInfo", userInfo);
            }

            setResult(resultCode, data);
            finish();

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private static double distance(double lat1, double lng1, double lat2, double lng2){
        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist *= 60 * 1.1515;
        dist *= 1609.344;

        return (dist);
    }

    private static double deg2rad(double deg){
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}