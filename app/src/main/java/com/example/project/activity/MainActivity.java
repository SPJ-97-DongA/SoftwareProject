package com.example.project.activity;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activity.board.BoardActivity;
import com.example.project.activity.board.BoardListActivity;
import com.example.project.activity.board.BoardListViewAdapter;
import com.example.project.data.PostData;
import com.example.project.data.QrData;
import com.example.project.data.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.ListupResponse;
import com.example.project.response.QrResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends FragmentActivity {

    private ImageButton mSearch;
    private ImageButton mMap;
    private ImageButton mQr;
    private ImageButton mMyPage;

    private ImageButton mfreeBD;
    private ImageButton mregionInfoBD;
    private ImageButton mpointBD;

    private BoardListViewAdapter freeAdapter;
    private BoardListViewAdapter regionAdapter;
    private BoardListViewAdapter pointAdapter;

    ListView freeList;
    ListView regionList;
    ListView pointList;

    private UserData userInfo;

    private Context context;
    private Intent intent;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Setting();

        listUP("free", 0, freeAdapter);
        listUP("region", 0, regionAdapter);
        listUP("point", 0, pointAdapter);

        mMap.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(intent);
        });

        mQr.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), QrActivity.class);
            intent.putExtra("userInfo", userInfo);

            startActivityForResult(intent, 0);
        });

        //마이페이지 이동
        mMyPage.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), MypageActivity.class);
            intent.putExtra("userInfo", userInfo);
            startActivity(intent);
        });

        // 자유 게시판 이동
        mfreeBD.setOnClickListener(v->{
            BoardIntentSet("free");
            startActivity(intent);
        });

        // 지역정보 게시판 이동
        mregionInfoBD.setOnClickListener(v->{
            BoardIntentSet("region");
            startActivity(intent);
        });

        // 포인트 게시판 이동
        mpointBD.setOnClickListener(v->{
            BoardIntentSet("point");
            startActivity(intent);
        });

        /* 리스트뷰 클릭 이벤트 */
        freeList.setOnItemClickListener((parent, view, position, id) -> listClickEvent(parent, position));
        regionList.setOnItemClickListener((parent, view, position, id) -> listClickEvent(parent, position));
        pointList.setOnItemClickListener((parent, view, position, id) -> listClickEvent(parent, position));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            UserData userInfo = (UserData) data.getSerializableExtra("userInfo");

            this.userInfo.setPoint(userInfo.getPoint());
            QrData qrData = new QrData(userInfo.getEmail(), userInfo.getPoint());
            infoUpdate(qrData);
        }
    }

    public void BoardIntentSet(String type){
        intent = new Intent(getApplicationContext(), BoardActivity.class);
        intent.putExtra("userInfo", userInfo);
        intent.putExtra("type", type);
    }


    public void infoUpdate(QrData qrdata){
        service.qrSuccess(qrdata).enqueue(new Callback<QrResponse>() {
            @Override
            public void onResponse(Call<QrResponse> call, Response<QrResponse> response) {
                QrResponse result = response.body();

                getIntent().putExtra("userInfo", userInfo);
            }

            @Override
            public void onFailure(Call<QrResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "포인트 갱신 실패!", Toast.LENGTH_SHORT).show();
                Log.e("포인트 갱신 실패!", t.getMessage());
            }
        });
    }

    public void listUP(String type, int end, BoardListViewAdapter adapter){
        service.ListUP(type, end).enqueue(new Callback<ListupResponse>() {
            @Override
            public void onResponse(Call<ListupResponse> call, Response<ListupResponse> response) {
                ListupResponse result = response.body();
                List<PostData> boardList = result.getBoardList();

                for(int i = 1; i <= 2; i++) {
                    if(boardList.size() - i >= 0) {
                        adapter.addItem(boardList.get(boardList.size() - i));
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListupResponse> call, Throwable t) {
                Log.e("글목록 호출 문제", t.getMessage());
            }
        });
    }

    // 기본세팅
    public void Setting(){
        service = RetrofitClient.getClient().create(ServiceApi.class);

        intent = getIntent();
        userInfo = (UserData) intent.getSerializableExtra("userInfo");
        context = getApplicationContext();

        freeAdapter = new BoardListViewAdapter(context);
        regionAdapter = new BoardListViewAdapter(context);
        pointAdapter = new BoardListViewAdapter(context);

        freeList = findViewById(R.id.freeBoard);
        freeList.setAdapter(freeAdapter);

        regionList = findViewById(R.id.regionBoard);
        regionList.setAdapter(regionAdapter);

        pointList = findViewById(R.id.pointBoard);
        pointList.setAdapter(pointAdapter);


        mSearch = findViewById(R.id.mainSearch);
        mMap = findViewById(R.id.mainMap);
        mQr = findViewById(R.id.mainQr);
        mMyPage = findViewById(R.id.mainMypage);


        mfreeBD = findViewById(R.id.freePlus);
        mregionInfoBD = findViewById(R.id.regionPlus);
        mpointBD = findViewById(R.id.pointPlus);
    }


    public void listClickEvent(AdapterView<?> parent, int position){
        PostData data = (PostData) parent.getItemAtPosition(position);

        Intent post_intent = new Intent(getApplicationContext(), BoardListActivity.class);
        post_intent.putExtra("post_id", data.getId());

        startActivity(post_intent);
    }
}