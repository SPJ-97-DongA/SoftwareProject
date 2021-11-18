

package com.example.project.fragment;

import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.content.Context;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.project.R;
        import com.example.project.data.RegionData;
        import com.example.project.data.RegionResponse;
import com.example.project.data.SubregionResponse;
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



        public class FragmentMap extends Fragment implements OnMapReadyCallback {

            private List<RegionData> regionlist;

            private List<String> list;

            private ListView placeListview;

            private MapView mapView;

            private Context context;

            private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

            private FusedLocationSource locationSource;

            private NaverMap naverMap;

            private ServiceApi service;


            @RequiresApi(api = Build.VERSION_CODES.N)
            public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment_map, container, false);
                context = container.getContext();
                service = RetrofitClient.getClient().create(ServiceApi.class);

                list = new ArrayList<>();
                regionUpdate();

                ListView listView = rootView.findViewById(R.id.regionListview);

                // 지역 셋팅 리스트뷰 좌측 띄우기
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(adapter);
                placeListview = rootView.findViewById(R.id.placeListview);


                // 다른 광광지 리스트
                listView.setOnItemClickListener((adapterView, view, position, l) -> {
                    String data = (String) adapterView.getItemAtPosition(position);
                    Marker Mark = new Marker();

                    Optional<RegionData> region = regionlist.stream().filter(s -> s.getName().equals(data)).findFirst();
                    RegionData rg = region.get();


                    subregionUpdate(Mark, rg, rg.getLatitude(), rg.getLongitude());

                });
                mapView = rootView.findViewById(R.id.mapView);
                mapView.onCreate(savedInstanceState);
                mapView.getMapAsync(this);

                locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
                return rootView;
            }

            //위치정보 권한 설정
            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
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
                    @Override
                    public void onResponse(Call<RegionResponse> call, Response<RegionResponse> response) {
                        RegionResponse result = response.body();
                        regionlist = result.getRegionList();

                        for (RegionData data : regionlist) {
                            list.add(data.getName());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegionResponse> call, Throwable t) {
                        Toast.makeText(context, "지도 정보를 불러오는 과정에 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.e("지도 에러!", t.getMessage());
                    }
                });
            }
            
            // 지역별 명소위치 호출
            public void subregionUpdate(Marker mark, RegionData data, double latitude, double longitude){
                service.callSubregionList(data).enqueue(new Callback<SubregionResponse>() {

                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<SubregionResponse> call, Response<SubregionResponse> response) {
                        SubregionResponse result = response.body();
                        List<RegionData> subregionList = result.getSubregionList();
                       List<String> subregion = new ArrayList<>();

                        for(RegionData item : subregionList){
                            subregion.add(item.getName());
                        }

                        ArrayAdapter<String> subAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, subregion);
                        placeListview.setAdapter(subAdapter);

                        onCamSetting(latitude, longitude);

                        placeListview.setOnItemClickListener((adapterView1, view1, position1, l1) -> {
                            String regionName = (String) adapterView1.getItemAtPosition(position1);

                            Optional<RegionData> item = subregionList.stream().filter(s-> s.getName().equals(regionName)).findFirst();
                            onMapMove(mark, item.get().getLatitude(), item.get().getLongitude());
                        });

                    }

                    @Override
                    public void onFailure(Call<SubregionResponse> call, Throwable t) {
                        Toast.makeText(context, "지역별 정보를 불러오는 과정에 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                        Log.e("지역별 정보 에러!", t.getMessage());
                    }
                });
            }
        }