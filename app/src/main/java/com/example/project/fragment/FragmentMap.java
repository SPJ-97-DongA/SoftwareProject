

        package com.example.project.fragment;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;

        import android.content.Context;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import com.example.project.R;
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

        // 지역 셋팅 리스트뷰 좌측 띄우기
        ArrayAdapter<String> adpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);
        ListView placeListview = rootView.findViewById(R.id.placeListview);

        List<String> list1 = new ArrayList<>();
        list1.add("불국사");
        list1.add("석굴암");
        ArrayAdapter<String> Koungjuadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list1);

        List<String> list2 = new ArrayList<>();
        list2.add("용두산공원");
        list2.add("부산근대역사관");
        list2.add("부산영화체험박물관");
        ArrayAdapter<String> jungguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list2);

        List<String> list3 = new ArrayList<>();
        list3.add("송도해수욕장");
        list3.add("송도해상케이블카");
        list3.add("암남공원");
        ArrayAdapter<String> seoguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list3);

        List<String> list4 = new ArrayList<>();
        list4.add("중앙공원");
        list4.add("차이나타운");
        list4.add("이바구길모노레일");
        ArrayAdapter<String> dongguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list4);

        List<String> list5 = new ArrayList<>();
        list5.add("태종대");
        list5.add("흰여울문화마을");
        list5.add("국립해양박물관");
        ArrayAdapter<String> youngdoguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list5);

        List<String> list6 = new ArrayList<>();
        list6.add("부산시민공원");
        list6.add("황령산봉수대");
        list6.add("전포카페거리");
        ArrayAdapter<String> jinguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list6);

        List<String> list7 = new ArrayList<>();
        list7.add("허심청");
        list7.add("금강공원");
        list7.add("부산해양자연사박물관");
        ArrayAdapter<String> dongreguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list7);

        List<String> list8 = new ArrayList<>();
        list8.add("오륙도스카이워크");
        list8.add("이기대수변공원");
        list8.add("우암동도시숲");
        ArrayAdapter<String> namguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list8);

        List<String> list9 = new ArrayList<>();
        list9.add("백양산웰빙숲");
        list9.add("화명수목원");
        list9.add("덕천동젊음의거리");
        ArrayAdapter<String> bokguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list9);

        List<String> list10 = new ArrayList<>();
        list10.add("해운대해수욕장");
        list10.add("동백섬");
        list10.add("BEXCO");
        ArrayAdapter<String> heundeguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list10);

        List<String> list11 = new ArrayList<>();
        list11.add("감천문화마을");
        list11.add("을숙도생태공원");
        list11.add("장림포구");
        ArrayAdapter<String> sahaguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list11);

        List<String> list12 = new ArrayList<>();
        list12.add("스포원파크");
        list12.add("오륜대전망대");
        list12.add("금강식물원");
        ArrayAdapter<String> gemjungguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list12);

        List<String> list13 = new ArrayList<>();
        list13.add("정거마을문화거리");
        list13.add("대항전망대");
        list13.add("가덕도등대");
        ArrayAdapter<String> gangseoadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list13);

        List<String> list14 = new ArrayList<>();
        list14.add("온천시민공원");
        list14.add("대영해수온천");
        list14.add("녹음광장");
        ArrayAdapter<String> yuenjeguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list14);

        List<String> list15 = new ArrayList<>();
        list15.add("민락수변공원");
        list15.add("광안리해수욕장");
        list15.add("수영사적공원");
        ArrayAdapter<String> soyoungguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list15);

        List<String> list16 = new ArrayList<>();
        list16.add("삼락생태공원");
        list16.add("사상근린공원");
        list16.add("사상생활사박물관");
        ArrayAdapter<String> sasangguadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list16);

        List<String> list17 = new ArrayList<>();
        list17.add("워터하우스");
        list17.add("치유의숲");
        list17.add("국립부산과학관");
        ArrayAdapter<String> gijanggunadpater = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list17);//명소셋팅


        // 다른 광광지 리스트
        listView.setOnItemClickListener((adapterView, view, position, l) -> {
            String data = (String) adapterView.getItemAtPosition(position);
            Marker Mark = new Marker();
            if(data == "경주")
            {

                placeListview.setAdapter(Koungjuadpater);
                onCamSetting(35.85616, 129.22479);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView1, view1, position1, l1) -> {
                    String data1 = (String) adapterView1.getItemAtPosition(position1);
                    if(data1 == "불국사")
                        onMapMove(Mark, 35.79100, 129.33229);
                    else if(data1 == "석굴암")
                        onMapMove(Mark, 35.79513, 129.34956);
                });
            }
            else if(data == "중구")
            {
                placeListview.setAdapter(jungguadpater);
                onCamSetting(35.10611, 129.03228);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView12, view12, position12, l12) -> {
                    String data12 = (String) adapterView12.getItemAtPosition(position12);
                    if(data12 == "용두산공원")
                        onMapMove(Mark, 35.10065, 129.03263);
                    else if(data12 == "부산근대역사관")
                        onMapMove(Mark, 35.10267, 129.03119);
                    else if(data12 == "부산영화체험박물관")
                        onMapMove(Mark,35.10168, 129.03375);
                });
            }
            else if(data == "서구")
            {
                placeListview.setAdapter(seoguadpater);
                onCamSetting(35.09792, 129.02421);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView13, view13, position13, l13) -> {
                    String data13 = (String) adapterView13.getItemAtPosition(position13);
                    if(data13 == "송도해수욕장")
                        onMapMove(Mark,35.07542, 129.01677);
                    else if(data13 == "송도해상케이블카")
                        onMapMove(Mark, 35.07638, 129.02361);
                    else if(data13 == "암남공원")
                        onMapMove(Mark, 35.06025, 129.01737);
                });
            }
            else if(data == "동구")
            {
                placeListview.setAdapter(dongguadpater);
                onCamSetting(35.12927, 129.04549);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView14, view14, position14, l14) -> {
                    String data14 = (String) adapterView14.getItemAtPosition(position14);
                    if(data14 == "중앙공원")
                        onMapMove(Mark, 35.11608, 129.02818);
                    else if(data14 == "차이나타운")
                        onMapMove(Mark, 35.11446, 129.03821);
                    else if(data14 == "이바구길모노레일")
                        onMapMove(Mark, 35.11716, 129.03557);
                });
            }
            else if(data == "영도구")
            {
                placeListview.setAdapter(youngdoguadpater);
                onCamSetting(35.09118, 129.06791);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView15, view15, position15, l15) -> {
                    String data15 = (String) adapterView15.getItemAtPosition(position15);
                    if(data15 == "태종대")
                        onMapMove(Mark, 35.05306, 129.08722);
                    else if(data15 == "흰여울문화마을")
                        onMapMove(Mark, 35.07825, 129.04533);
                    else if(data15 == "국립해양박물관")
                        onMapMove(Mark, 35.07846, 129.08028);
                });
            }
            else if(data == "부산진구")
            {
                placeListview.setAdapter(jinguadpater);
                onCamSetting(35.16312, 129.05302);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView16, view16, position16, l16) -> {
                    String data16 = (String) adapterView16.getItemAtPosition(position16);
                    if(data16 == "부산시민공원")
                        onMapMove(Mark, 35.16818, 129.05714);
                    else if(data16 == "황령산봉수대")
                        onMapMove(Mark, 35.15708, 129.08205);
                    else if(data16 == "전포카페거리")
                        onMapMove(Mark, 35.15540, 129.06397);
                });
            }
            else if(data == "동래구")
            {
                placeListview.setAdapter(dongreguadpater);
                onCamSetting(35.19652, 129.09310);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView17, view17, position17, l17) -> {
                    String data17 = (String) adapterView17.getItemAtPosition(position17);
                    if(data17 == "허심청")
                        onMapMove(Mark, 35.22112, 129.08265);
                    else if(data17 == "금강공원")
                        onMapMove(Mark, 35.22009, 129.07387);
                    else if(data17 == "부산해양자연사박물관")
                        onMapMove(Mark, 35.22180, 129.07592);
                });
            }
            else if(data == "남구")
            {
                placeListview.setAdapter(namguadpater);
                onCamSetting(35.13657, 129.08425);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView18, view18, position18, l18) -> {
                    String data18 = (String) adapterView18.getItemAtPosition(position18);
                    if(data18 == "오륙도스카이워크")
                        onMapMove(Mark, 35.10055, 129.12456);
                    else if(data18 == "이기대수변공원")
                        onMapMove(Mark, 35.11544, 129.12344);
                    else if(data18 == "우암동도시숲")
                        onMapMove(Mark, 35.12733, 129.07418);
                });
            }
            else if(data == "북구")
            {
                placeListview.setAdapter(bokguadpater);
                onCamSetting(35.19726, 128.99029);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView19, view19, position19, l19) -> {
                    String data19 = (String) adapterView19.getItemAtPosition(position19);
                    if(data19 == "백양산웰빙숲")
                        onMapMove(Mark, 35.18495, 129.00938);
                    else if(data19 == "화명수목원")
                        onMapMove(Mark, 35.25105, 129.04250);
                    else if(data19 == "덕천동젊음의거리")
                        onMapMove(Mark, 35.20987, 129.00722);
                });
            }
            else if(data == "해운대구")
            {
                placeListview.setAdapter(heundeguadpater);
                onCamSetting(35.16289, 129.16381);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView110, view110, position110, l110) -> {
                    String data110 = (String) adapterView110.getItemAtPosition(position110);
                    if(data110 == "해운대해수욕장")
                        onMapMove(Mark, 35.15866, 129.16032);
                    else if(data110 == "동백섬")
                        onMapMove(Mark, 35.15450, 129.15260);
                    else if(data110 == "BEXCO")
                        onMapMove(Mark, 35.16898, 129.13604);

                });
            }
            else if(data == "사하구")
            {
                placeListview.setAdapter(sahaguadpater);
                onCamSetting(35.10445, 128.97481);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView111, view111, position111, l111) -> {
                    String data111 = (String) adapterView111.getItemAtPosition(position111);
                    if(data111 == "감천문화마을")
                        onMapMove(Mark, 35.09736, 129.01058);
                    else if(data111 == "을숙도생태공원")
                        onMapMove(Mark, 35.10374, 128.94303);
                    else if(data111 == "장림포구")
                        onMapMove(Mark, 35.08154, 128.95811);
                });
            }
            else if(data == "금정구")
            {
                placeListview.setAdapter(gemjungguadpater);
                onCamSetting(35.24285, 129.09219);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView112, view112, position112, l112) -> {
                    String data112 = (String) adapterView112.getItemAtPosition(position112);
                    if(data112 == "스포원파크")
                        onMapMove(Mark, 35.29194, 129.10309);
                    else if(data112 == "오륜대전망대")
                        onMapMove(Mark, 35.25023, 129.11292);
                    else if(data112 == "금강식물원")
                        onMapMove(Mark, 35.22630, 129.07704);
                });
            }
            else if(data == "강서구")
            {
                placeListview.setAdapter(gangseoadpater);
                onCamSetting(35.21219, 128.98062);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView113, view113, position113, l113) -> {
                    String data113 = (String) adapterView113.getItemAtPosition(position113);
                    if(data113 == "정거마을문화거리")
                        onMapMove(Mark, 35.06653, 128.84868);
                    else if(data113 == "대항전망대")
                        onMapMove(Mark,35.01652, 128.82583);
                    else if(data113 == "가덕도등대")
                        onMapMove(Mark,35.01017, 128.82650);
                });
            }
            else if(data == "연제구")
            {
                placeListview.setAdapter(yuenjeguadpater);
                onCamSetting(35.17620, 129.07964);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView114, view114, position114, l114) -> {
                    String data114 = (String) adapterView114.getItemAtPosition(position114);
                    if(data114 == "온천시민공원")
                        onMapMove(Mark ,35.19258, 129.09508);
                    else if(data114 == "대영해수온천")
                        onMapMove(Mark ,35.18978, 129.10695);
                    else if(data114 == "녹음광장")
                        onMapMove(Mark, 35.18080, 129.07392);
                });
            }
            else if(data == "수영구")
            {
                placeListview.setAdapter(soyoungguadpater);
                onCamSetting(35.14554, 129.11320);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView115, view115, position115, l115) -> {
                    String data115 = (String) adapterView115.getItemAtPosition(position115);
                    if(data115 == "민락수변공원")
                        onMapMove(Mark, 35.15450, 129.13320);
                    else if(data115 == "광안리해수욕장")
                        onMapMove(Mark,35.15312, 129.11863);
                    else if(data115 == "수영사적공원")
                        onMapMove(Mark, 35.17074, 129.11446);
                });
            }
            else if(data == "사상구")
            {
                placeListview.setAdapter(sasangguadpater);
                onCamSetting(35.15259, 128.99121);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView116, view116, position116, l116) -> {
                    String data116 = (String) adapterView116.getItemAtPosition(position116);
                    if(data116 == "삼락생태공원")
                        onMapMove(Mark, 35.16876, 128.97352);
                    else if(data116 == "사상근린공원")
                        onMapMove(Mark, 35.15918, 128.99497);
                    else if(data116 == "사상생활사박물관")
                        onMapMove(Mark, 35.17204, 128.97863);
                });
            }
            else if(data == "기장군")
            {
                placeListview.setAdapter(gijanggunadpater);
                onCamSetting(35.24458, 129.22226);

                // 관광지로 카메라이동
                placeListview.setOnItemClickListener((adapterView117, view117, position117, l117) -> {
                    String data117 = (String) adapterView117.getItemAtPosition(position117);
                    if(data117 == "워터하우스")
                        onMapMove(Mark,35.19738, 129.22879);
                    else if(data117 == "치유의숲")
                        onMapMove(Mark, 35.27036, 129.12932);
                    else if(data117 == "국립부산과학관")
                        onMapMove(Mark, 35.20442, 129.21270);
                });
            }

        });
        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        return rootView;
    }

    //위치정보 권한 설정
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
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

    public void onCamSetting(double latitude, double longitude){
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(latitude, longitude))
                .animate(CameraAnimation.Fly, 1000);
        naverMap.moveCamera(cameraUpdate);
    }

    public void onMapMove(Marker Mark, double latitude, double longitude){
        onCamSetting(latitude, longitude);
        Mark.setPosition(new LatLng(latitude, longitude));
        Mark.setMap(naverMap);
    }
}