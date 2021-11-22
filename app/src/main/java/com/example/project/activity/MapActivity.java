package com.example.project.activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activity.adapter.RecycleViewAdapter;
import com.example.project.data.RegionData;
import com.example.project.data.RegionDetailData;
import com.example.project.response.RegionResponse;
import com.example.project.response.SubregionResponse;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraAnimation;
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
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private List<String> list;

    private ListView placeListview;
    private MapView mapView;

    private Marker mark;

    private Context context;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private ServiceApi service;

    private RecycleViewAdapter recycleViewAdapter;

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        Intent preIntent = getIntent();
        onMapMove(mark, preIntent.getDoubleExtra("latitude", 0.0), preIntent.getDoubleExtra("longitude", 0.0));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        context = getApplicationContext();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        list = new ArrayList<>();
        regionUpdate();

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }

    //위치정보 권한 설정
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    }


    // 카메라 세팅
    public void onCamSetting(double latitude, double longitude) {
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(latitude, longitude))
                .animate(CameraAnimation.Fly, 1000);
        naverMap.moveCamera(cameraUpdate);
    }

    // 지도 이동
    public void onMapMove(Marker Mark, double latitude, double longitude) {
        onCamSetting(latitude, longitude);
        Mark.setPosition(new LatLng(latitude, longitude));
        Mark.setMap(naverMap);
    }

    //지역 리스트 호출
    public void regionUpdate() {
        service.callRegionList().enqueue(new Callback<RegionResponse>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                RegionResponse result = response.body();
                List<RegionData> regionlist = result.getRegionList();

                List<String> list = new ArrayList<>();

                for(RegionData item : regionlist){
                    list.add(item.getName());
                }

                ListView listView = findViewById(R.id.regionListview);

                // 지역 셋팅 리스트뷰 좌측 띄우기
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);


                // 다른 관광지 리스트
                listView.setOnItemClickListener((adapterView, view, position, l) -> {
                    String data = (String) adapterView.getItemAtPosition(position);
                    mark = new Marker();

                    Optional<RegionData> region = regionlist.stream().filter(s -> s.getName().equals(data)).findFirst();
                    RegionData rg = region.get();

                    subregionUpdate(rg, rg.getLatitude(), rg.getLongitude());
                });

            }

            @Override
            public void onFailure(Call<RegionResponse> call, Throwable t) {
                Toast.makeText(context, "지도 정보를 불러오는 과정에 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                Log.e("지도 에러!", t.getMessage());
            }
        });
    }

    // 지역별 명소위치 호출
    public void subregionUpdate(RegionData data, double latitude, double longitude){
        service.callSubregionList(data).enqueue(new Callback<SubregionResponse>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<SubregionResponse> call, Response<SubregionResponse> response) {
                onCamSetting(latitude, longitude);

                SubregionResponse result = response.body();
                List<RegionDetailData> subregionList = result.getSubregionList();

                RecyclerView recyclerView = findViewById(R.id.placeListview);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MapActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);

                recycleViewAdapter = new RecycleViewAdapter(subregionList, mark, naverMap);
                recyclerView.setAdapter(recycleViewAdapter);
            }

            @Override
            public void onFailure(Call<SubregionResponse> call, Throwable t) {
                Toast.makeText(context, "지역별 정보를 불러오는 과정에 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                Log.e("지역별 정보 에러!", t.getMessage());
            }
        });
    }
}

