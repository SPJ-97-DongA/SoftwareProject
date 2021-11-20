package com.example.project.activity.board;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.data.CommentData;
import com.example.project.data.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.CommentResponse;
import com.example.project.response.PostResponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardListActivity extends AppCompatActivity {

    private TextView mUsername;
    private TextView mTitle;
    private TextView mDate;
    private TextView mContents;
    private TextView mBoardName;

    private ListView commentListView;

    private EditText mComment;

    private Button commentSubmit;

    private ImageButton boardlistThreedots;

    private int post_id;
    private UserData userInfo;
    private CommentAdapter commentAdapter;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        userInfo = (UserData) getIntent().getSerializableExtra("userInfo");
        commentAdapter = new CommentAdapter(this);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        Intent intent = getIntent();
        post_id = intent.getIntExtra("post_id", 0);

        mBoardName = findViewById(R.id.boardlistName);
        mUsername = findViewById(R.id.boardlistId);
        mTitle = findViewById(R.id.boardlistTitle);
        mDate = findViewById(R.id.boardlistTime);
        mContents = findViewById(R.id.boardlistContent);
        boardlistThreedots = findViewById(R.id.boardlistThreedots);

        boardlistThreedots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(BoardListActivity.this, boardlistThreedots);
                MenuInflater inf = popupMenu.getMenuInflater();
                inf.inflate(R.menu.menu_boardlist, popupMenu.getMenu());
                popupMenu.show();
            }
        });


        commentListView = findViewById(R.id.commentListview);
        commentListView.setAdapter(commentAdapter);

        mComment = findViewById(R.id.boardlistComment);
        commentSubmit = findViewById(R.id.commentSubmit);

        viewPOST(post_id);

        commentSubmit.setOnClickListener(v-> writeComment(new CommentData(post_id, userInfo.getName(), mComment.getText().toString())) );


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_boardlist, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menuBoardlist1:
                return true;
            case R.id.menuBoardlist2:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewPOST(int post_id){
        service.viewPOST(post_id).enqueue(new Callback<PostResponse>() {

            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse result = response.body();

                String boardName;

                switch(result.getType()){
                    case "region": boardName = "지역 정보 게시판"; break;
                    case "point": boardName = "포인트 게시판"; break;
                    default: boardName = "자유 게시판"; break;
                }

                mBoardName.setText(boardName);
                mUsername.setText(result.getWriter());
                mTitle.setText(result.getTitle());
                mDate.setText(result.getDateTime());
                mContents.setText(result.getContents());

                commentUpdate(post_id);
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e("글 로딩에러", t.getMessage());
            }

        });
    }

    public void writeComment(CommentData data){
        service.writeComment(data).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                commentUpdate(data.getId());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("댓글 작성에러", t.getMessage());
            }
        });
    }

    public void commentUpdate(int post_id){
        service.commentUpdate(post_id).enqueue(new Callback<CommentResponse>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                CommentResponse result = response.body();

                List<CommentData> commentList = result.getCommentList();


                if(!commentList.isEmpty()) {
                    Collections.sort(commentList, Comparator.comparing(CommentData::getCreatedAt));

                    for(CommentData item : commentList) {
                        if(!commentAdapter.findDup(item))
                            commentAdapter.addItem(item);
                    }

                    commentAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.e("댓글 로딩에러", t.getMessage());
            }
        });
    }

}