package com.example.nmaptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        List<String> list = new ArrayList<>();
        list.add("서울");
        list.add("부산");
        list.add("경주");
        list.add("속초");
        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);//지역셋팅
        ListView listView1 = findViewById(R.id.listView1);
        List<String> list1 = new ArrayList<>();
        list1.add("불국사");
        list1.add("석굴암");
        ArrayAdapter<String> Koungjuadpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list1);
        List<String> list2 = new ArrayList<>();
        list2.add("뉴턴나무");
        list2.add("에덴공원");
        ArrayAdapter<String> busanadpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);//명소셋팅
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                String data = (String) adapterView.getItemAtPosition(position);
                if(data == "서울")
                {

                }
                else if(data == "부산")
                {
                    listView1.setAdapter(busanadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.17972, 129.07494))
                        .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "뉴턴나무")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.11524, 128.96671))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                            }
                            else if(data == "에덴공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10921, 128.96329))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "경주")
                {

                    listView1.setAdapter(Koungjuadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.85616, 129.22479))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "불국사")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.79100, 129.33229))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                            }
                            else if(data == "석굴암")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.79513, 129.34956))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "속초")
                {

                }
                }// 다른 광광지 리스트
        });
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }
    //위치정보 권한 설정
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setCompassEnabled(true); // 나침반
        uiSettings.setScaleBarEnabled(true); // 거리
        uiSettings.setZoomControlEnabled(true); // 줌
        uiSettings.setLocationButtonEnabled(true); // 내가 있는곳
        Marker endenparkM = new Marker();
        endenparkM.setPosition(new LatLng(35.10921, 128.96329));
        endenparkM.setMap(naverMap);
        Marker newterntreeM = new Marker();
        newterntreeM.setPosition(new LatLng(35.11524, 128.96671));
        newterntreeM.setMap(naverMap);
        Marker bulkuksaM = new Marker();
        bulkuksaM.setPosition(new LatLng(35.79100, 129.33229));
        bulkuksaM.setMap(naverMap);
        Marker sukgulamM = new Marker();
        sukgulamM.setPosition(new LatLng(35.79513, 129.34956));
        sukgulamM.setMap(naverMap);
    }
}
