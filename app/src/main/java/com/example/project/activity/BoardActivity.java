package com.example.project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
    }
}