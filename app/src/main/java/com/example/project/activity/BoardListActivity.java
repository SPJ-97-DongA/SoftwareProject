package com.example.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.project.R;

public class BoardListActivity extends AppCompatActivity {
    private ImageButton boardlistThreedots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_boardlist,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuBoardlist1:
                return true;
            case R.id.menuBoardlist2:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}