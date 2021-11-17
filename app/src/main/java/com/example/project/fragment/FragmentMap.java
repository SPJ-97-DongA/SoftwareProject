package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.R;
import com.google.android.gms.maps.GoogleMap;
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

public class FragmentMap extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private Context context;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.activity_fragment_map, container, false);
        context = container.getContext();


        ListView listView = rootView.findViewById(R.id.regionListview);
        List<String> list = new ArrayList<>();
        // 지역 추가
        list.add("경주");
        list.add("중구");
        list.add("서구");
        list.add("동구");
        list.add("영도구");
        list.add("부산진구");
        list.add("동래구");
        list.add("남구");
        list.add("북구");
        list.add("해운대구");
        list.add("사하구");
        list.add("금정구");
        list.add("강서구");
        list.add("연제구");
        list.add("수영구");
        list.add("사상구");
        list.add("기장군");
        //
        ArrayAdapter<String> adpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);//지역셋팅
        ListView regionListview = rootView.findViewById(R.id.regionListview);
        List<String> list1 = new ArrayList<>();
        list1.add("불국사");
        list1.add("석굴암");
        ArrayAdapter<String> Koungjuadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list1);
        List<String> list2 = new ArrayList<>();
        list2.add("용두산공원");
        list2.add("부산근대역사관");
        list2.add("부산영화체험박물관");
        ArrayAdapter<String> jungguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list2);
        List<String> list3 = new ArrayList<>();
        list3.add("송도해수욕장");
        list3.add("송도해상케이블카");
        list3.add("암남공원");
        ArrayAdapter<String> seoguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list3);
        List<String> list4 = new ArrayList<>();
        list4.add("중앙공원");
        list4.add("차이나타운");
        list4.add("이바구길모노레일");
        ArrayAdapter<String> dongguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list4);
        List<String> list5 = new ArrayList<>();
        list5.add("태종대");
        list5.add("흰여울문화마을");
        list5.add("국립해양박물관");
        ArrayAdapter<String> youngdoguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list5);
        List<String> list6 = new ArrayList<>();
        list6.add("부산시민공원");
        list6.add("황령산봉수대");
        list6.add("전포카페거리");
        ArrayAdapter<String> jinguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list6);
        List<String> list7 = new ArrayList<>();
        list7.add("허심청");
        list7.add("금강공원");
        list7.add("부산해양자연사박물관");
        ArrayAdapter<String> dongreguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list7);
        List<String> list8 = new ArrayList<>();
        list8.add("오륙도스카이워크");
        list8.add("이기대수변공원");
        list8.add("우암동도시숲");
        ArrayAdapter<String> namguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list8);
        List<String> list9 = new ArrayList<>();
        list9.add("백양산웰빙숲");
        list9.add("화명수목원");
        list9.add("덕천동젊음의거리");
        ArrayAdapter<String> bokguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list9);
        List<String> list10 = new ArrayList<>();
        list10.add("해운대해수욕장");
        list10.add("동백섬");
        list10.add("BEXCO");
        ArrayAdapter<String> heundeguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list10);
        List<String> list11 = new ArrayList<>();
        list11.add("감천문화마을");
        list11.add("을숙도생태공원");
        list11.add("장림포구");
        ArrayAdapter<String> sahaguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list11);
        List<String> list12 = new ArrayList<>();
        list12.add("스포원파크");
        list12.add("오륜대전망대");
        list12.add("금강식물원");
        ArrayAdapter<String> gemjungguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list12);
        List<String> list13 = new ArrayList<>();
        list13.add("정거마을문화거리");
        list13.add("대항전망대");
        list13.add("가덕도등대");
        ArrayAdapter<String> gangseoadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list13);
        List<String> list14 = new ArrayList<>();
        list14.add("온천시민공원");
        list14.add("대영해수온천");
        list14.add("녹음광장");
        ArrayAdapter<String> yuenjeguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list14);
        List<String> list15 = new ArrayList<>();
        list15.add("민락수변공원");
        list15.add("광안리해수욕장");
        list15.add("수영사적공원");
        ArrayAdapter<String> soyoungguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list15);
        List<String> list16 = new ArrayList<>();
        list16.add("삼락생태공원");
        list16.add("사상근린공원");
        list16.add("사상생활사박물관");
        ArrayAdapter<String> sasangguadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list16);
        List<String> list17 = new ArrayList<>();
        list17.add("워터하우스");
        list17.add("치유의숲");
        list17.add("국립부산과학관");
        ArrayAdapter<String> gijanggunadpater = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list17);//명소셋팅
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                String data = (String) adapterView.getItemAtPosition(position);
                Marker Mark = new Marker();
                if(data == "경주")
                {

                    regionListview.setAdapter(Koungjuadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.85616, 129.22479))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "불국사")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.79100, 129.33229))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.79100, 129.33229));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "석굴암")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.79513, 129.34956))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.79513, 129.34956));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "중구")
                {
                    regionListview.setAdapter(jungguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10611, 129.03228))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "용두산공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10065, 129.03263))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.10065, 129.03263));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "부산근대역사관")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10267, 129.03119))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.10267, 129.03119));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "부산영화체험박물관")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10168, 129.03375))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.10168, 129.03375));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "서구")
                {
                    regionListview.setAdapter(seoguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.09792, 129.02421))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "송도해수욕장")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.07542, 129.01677))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.07542, 129.01677));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "송도해상케이블카")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.07638, 129.02361))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.07638, 129.02361));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "암남공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.06025, 129.01737))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.06025, 129.01737));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "동구")
                {
                    regionListview.setAdapter(dongguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.12927, 129.04549))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "중앙공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.11608, 129.02818))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.11608, 129.02818));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "차이나타운")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.11446, 129.03821))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.11446, 129.03821));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "이바구길모노레일")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.11716, 129.03557))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.11716, 129.03557));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "영도구")
                {
                    regionListview.setAdapter(youngdoguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.09118, 129.06791))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "태종대")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.05306, 129.08722))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.05306, 129.08722));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "흰여울문화마을")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.07825, 129.04533))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.07825, 129.04533));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "국립해양박물관")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.07846, 129.08028))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.07846, 129.08028));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "부산진구")
                {
                    regionListview.setAdapter(jinguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.16312, 129.05302))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "부산시민공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.16818, 129.05714))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.16818, 129.05714));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "황령산봉수대")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15708, 129.08205))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15708, 129.08205));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "전포카페거리")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15540, 129.06397))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15540, 129.06397));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "동래구")
                {
                    regionListview.setAdapter(dongreguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.19652, 129.09310))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "허심청")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.22112, 129.08265))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.22112, 129.08265));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "금강공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.22009, 129.07387))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.22009, 129.07387));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "부산해양자연사박물관")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.22180, 129.07592))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.22180, 129.07592));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "남구")
                {
                    regionListview.setAdapter(namguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.13657, 129.08425))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "오륙도스카이워크")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10055, 129.12456))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.10055, 129.12456));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "이기대수변공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.11544, 129.12344))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.11544, 129.12344));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "우암동도시숲")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.12733, 129.07418))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.12733, 129.07418));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "북구")
                {
                    regionListview.setAdapter(bokguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.19726, 128.99029))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "백양산웰빙숲")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.18495, 129.00938))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.18495, 129.00938));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "화명수목원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.25105, 129.04250))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.25105, 129.04250));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "덕천동젊음의거리")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.20987, 129.00722))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.20987, 129.00722));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "해운대구")
                {
                    regionListview.setAdapter(heundeguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.16289, 129.16381))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "해운대해수욕장")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15866, 129.16032))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15866, 129.16032));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "동백섬")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15450, 129.15260))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15450, 129.15260));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "BEXCO")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.16898, 129.13604))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.16898, 129.13604));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "사하구")
                {
                    regionListview.setAdapter(sahaguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10445, 128.97481))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "감천문화마을")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.09736, 129.01058))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.09736, 129.01058));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "을숙도생태공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.10374, 128.94303))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.10374, 128.94303));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "장림포구")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.08154, 128.95811))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.08154, 128.95811));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "금정구")
                {
                    regionListview.setAdapter(gemjungguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.24285, 129.09219))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "스포원파크")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.29194, 129.10309))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.29194, 129.10309));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "오륜대전망대")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.25023, 129.11292))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.25023, 129.11292));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "금강식물원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.22630, 129.07704))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.22630, 129.07704));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "강서구")
                {
                    regionListview.setAdapter(gangseoadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.21219, 128.98062))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "정거마을문화거리")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.06653, 128.84868))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.06653, 128.84868));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "대항전망대")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.01652, 128.82583))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.01652, 128.82583));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "가덕도등대")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.01017, 128.82650))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.01017, 128.82650));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "연제구")
                {
                    regionListview.setAdapter(yuenjeguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.17620, 129.07964))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "온천시민공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.19258, 129.09508))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.19258, 129.09508));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "대영해수온천")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.18978, 129.10695))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.18978, 129.10695));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "녹음광장")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.18080, 129.07392))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.18080, 129.07392));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "수영구")
                {
                    regionListview.setAdapter(soyoungguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.14554, 129.11320))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "민락수변공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15450, 129.13320))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15450, 129.13320));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "광안리해수욕장")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15312, 129.11863))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15312, 129.11863));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "수영사적공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.17074, 129.11446))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.17074, 129.11446));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "사상구")
                {
                    regionListview.setAdapter(sasangguadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15259, 128.99121))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "삼락생태공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.16876, 128.97352))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.16876, 128.97352));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "사상근린공원")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.15918, 128.99497))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.15918, 128.99497));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "사상생활사박물관")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.17204, 128.97863))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.17204, 128.97863));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }
                else if(data == "기장군")
                {
                    regionListview.setAdapter(gijanggunadpater);
                    CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.24458, 129.22226))
                            .animate(CameraAnimation.Fly, 1000);
                    naverMap.moveCamera(cameraUpdate);
                    regionListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                        {
                            String data = (String) adapterView.getItemAtPosition(position);
                            if(data == "워터하우스")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.19738, 129.22879))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.19738, 129.22879));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "치유의숲")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.27036, 129.12932))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.27036, 129.12932));
                                Mark.setMap(naverMap);
                            }
                            else if(data == "국립부산과학관")
                            {
                                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(35.20442, 129.21270))
                                        .animate(CameraAnimation.Fly, 1000);
                                naverMap.moveCamera(cameraUpdate);
                                Mark.setPosition(new LatLng(35.20442, 129.21270));
                                Mark.setMap(naverMap);
                            }
                        }// 관광지로 카메라이동
                    });
                }

            }// 다른 광광지 리스트
        });
        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        return inflater.inflate(R.layout.activity_fragment_map, container, false);
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
    }
}