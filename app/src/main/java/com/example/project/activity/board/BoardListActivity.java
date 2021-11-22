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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activity.adapter.CommentAdapter;
import com.example.project.data.board.CommentData;
import com.example.project.data.user.UserData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.board.CommentResponse;
import com.example.project.response.board.PostResponse;

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

    private ImageButton mRefresh;
    private ImageButton boardlistThreedots;

    private int post_id;
    private UserData userInfo;
    private CommentAdapter commentAdapter;
    private ServiceApi service;

    private String postOwner;


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
        mRefresh = findViewById(R.id.boardlistRefresh);

        //글 불러오기
        viewPOST(post_id);

        //새로고침
        mRefresh.setOnClickListener(v -> {
            viewPOST(post_id);
        });

        //뒤로가기
        findViewById(R.id.boardlistBack).setOnClickListener(v -> finish());

        //댓글 작성
        commentListView = findViewById(R.id.commentListview);
        commentListView.setAdapter(commentAdapter);

        mComment = findViewById(R.id.boardlistComment);
        commentSubmit = findViewById(R.id.commentSubmit);

        commentSubmit.setOnClickListener(v -> writeComment(new CommentData(post_id, userInfo.getName(), mComment.getText().toString())));


        //글 삭제, 수정
        boardlistThreedots.setOnClickListener(v -> {
            if (postOwner.equals(userInfo.getEmail())) {
                PopupMenu popupMenu = new PopupMenu(BoardListActivity.this, boardlistThreedots);
                MenuInflater inf = popupMenu.getMenuInflater();
                inf.inflate(R.menu.menu_boardlist, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.post_update){
                            Intent intent1 = new Intent(getApplicationContext(), RegisterActivity.class);
                            intent1.putExtra("title", mTitle.getText().toString());
                            intent1.putExtra("content", mContents.getText().toString());
                            intent1.putExtra("userInfo", userInfo);
                            intent1.putExtra("post_id", post_id);

                            String type = getIntent().getStringExtra("type");
                            intent1.putExtra("type", type);

                            startActivity(intent1);

                            return true;
                        }else if(item.getItemId() == R.id.post_delete){
                            deletePost(post_id);
                            finish();
                            return true;
                        }

                        return false;
                    }
                });
                popupMenu.show();

            } else Toast.makeText(this, "올바른 접근권한이 아닙니다.", Toast.LENGTH_SHORT).show();
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        viewPOST(post_id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_boardlist, menu);
        return true;
    }


    // 글 불러오기
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

                postOwner = result.getEmail();
                mUsername.setText(result.getWriter());
                mTitle.setText(result.getTitle());
                mDate.setText(result.getDateTime());
                mContents.setText(result.getContents());

                commentUpdate(post_id, false);
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e("글 로딩에러", t.getMessage());
            }

        });
    }

    //댓글 작성
    public void writeComment(CommentData data){
        service.writeComment(data).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                commentUpdate(data.getId(), true);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("댓글 작성에러", t.getMessage());
            }
        });
    }

    //댓글 목록 갱신
    public void commentUpdate(int post_id, boolean WRITE){
        service.commentUpdate(post_id).enqueue(new Callback<CommentResponse>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                CommentResponse result = response.body();

                List<CommentData> commentList = result.getCommentList();


                if(!commentList.isEmpty()) {
                    Collections.sort(commentList, Comparator.comparing(CommentData::getCreatedAt));

                    for(CommentData item : commentList) {
                        if(!commentAdapter.findDup(item)) {
                            commentAdapter.addItem(item);
                        }
                    }

                    commentAdapter.notifyDataSetChanged();
                    if(WRITE)commentListView.setSelection(commentAdapter.getCount() - 1);
                    else commentListView.setSelection(0);
                }

            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                Log.e("댓글 로딩에러", t.getMessage());
            }
        });
    }


    public void deletePost(int post_id){
        service.deletePost(post_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "글 삭제가 완료되었습니다.", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("글 삭제에러", t.getMessage());
            }
        });
    }

}