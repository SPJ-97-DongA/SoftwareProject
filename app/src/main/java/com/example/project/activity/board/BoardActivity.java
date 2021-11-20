package com.example.project.activity.board;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.data.PostData;
import com.example.project.data.RegionData;
import com.example.project.data.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.ListupResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardActivity extends AppCompatActivity {

    private ImageButton mRefresh;
    private FloatingActionButton mWrite;
    private TextView mboardType;
    private ImageButton mSearch;

    private ServiceApi service;

    private UserData userInfo;
    private String type;
    private ListView listView;

    private BoardListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent intent = getIntent();
        userInfo = (UserData) intent.getSerializableExtra("userInfo");

        type = intent.getStringExtra("type");

        adapter = new BoardListViewAdapter(this);

        listView = findViewById(R.id.boardList);
        listView.setAdapter(adapter);

        mboardType = findViewById(R.id.boardType);
        mWrite = findViewById(R.id.boardWrite);
        mRefresh = findViewById(R.id.boardRefresh);
        mSearch = findViewById(R.id.boardSearch);

        switch(type){
            case "point": mboardType.setText("포인트 정보 게시판"); break;
            case "region": mboardType.setText("지역 정보 게시판"); break;
            default: mboardType.setText("자유게시판"); break;
        }

        service = RetrofitClient.getClient().create(ServiceApi.class);

        //글쓰기
        mWrite.setOnClickListener(v->{
            Intent write_intent = new Intent(getApplicationContext(), RegisterActivity.class);
            write_intent.putExtra("userInfo", userInfo);
            write_intent.putExtra("type", type);

            startActivity(write_intent);
        });

        //검색
        mSearch.setOnClickListener( v -> {

        });

        //새로고침
        mRefresh.setOnClickListener(v -> {
            int end_id = adapter.getSize() - 1;
            listUP(type, end_id);
        });

        //뒤로가기
        findViewById(R.id.boardBack).setOnClickListener( v -> finish());

        //리스트뷰
        listView.setOnItemClickListener((parent, view, position, id) -> {
            PostData data = (PostData) parent.getItemAtPosition(position);

            Intent post_intent = new Intent(getApplicationContext(), BoardListActivity.class);
            post_intent.putExtra("post_id", data.getId());

            startActivity(post_intent);
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        int end_id = adapter.getSize() - 1;
        listUP(type, end_id);
    }

    // 게시글 목록
    public void listUP(String type, int end){
        service.ListUP(type, end).enqueue(new Callback<ListupResponse>() {
            @Override
            public void onResponse(Call<ListupResponse> call, Response<ListupResponse> response) {
                ListupResponse result = response.body();
                List<PostData> boardList = result.getBoardList();


                for(PostData item : boardList){
                    adapter.addItem(item);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListupResponse> call, Throwable t) {
                Log.e("글목록 호출 문제", t.getMessage());
            }
        });
    }

}