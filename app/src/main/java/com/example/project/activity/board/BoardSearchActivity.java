package com.example.project.activity.board;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.activity.adapter.SearchAdapter;
import com.example.project.data.board.PostData;
import com.example.project.data.user.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.board.ListupResponse;
import com.example.project.response.board.PostResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardSearchActivity extends AppCompatActivity {

    private ServiceApi service;
    private SearchAdapter SearchAdapter;

    private EditText editSearch;

    private UserData userInfo;
    private List<PostData> boardList = new ArrayList<>();
    private List<PostData> copyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_search);

        service = RetrofitClient.getClient().create(ServiceApi.class);


        Intent intent = getIntent();
        userInfo = (UserData) intent.getSerializableExtra("userInfo");
        boardList = (List<PostData>) intent.getSerializableExtra("boardList");

        SearchAdapter = new SearchAdapter(boardList, this);
        copyList.addAll(boardList);

        ListView listView = findViewById(R.id.searchList);
        listView.setAdapter(SearchAdapter);



        //검색창 입력
        editSearch = findViewById(R.id.searchText);
        editSearch.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        // 글 클릭
        listView.setOnItemClickListener((parent, view, position, id) -> {
            PostData data = (PostData) parent.getItemAtPosition(position);

            Intent post_intent = new Intent(getApplicationContext(), BoardListActivity.class);
            post_intent.putExtra("post_id", data.getId());
            post_intent.putExtra("userInfo", userInfo);

            startActivity(post_intent);
        });


        //뒤로가기
        findViewById(R.id.searchBack).setOnClickListener( v -> finish());

    }


    //검색 수행
    public void search(String targetText){
        boardList.clear();

        if(targetText.length() == 0){
            boardList.addAll(copyList);
        }else{
            for(PostData item : copyList){
                if(item.getTitle().toLowerCase().contains(targetText)){
                    boardList.add(item);
                }
            }
        }

        SearchAdapter.notifyDataSetChanged();
    }

}
