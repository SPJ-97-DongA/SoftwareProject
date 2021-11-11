package com.example.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;
import com.example.project.fragment.FragmentHome;
import com.example.project.fragment.FragmentMap;
import com.example.project.fragment.FragmentMypage;
import com.example.project.fragment.FragmentQr;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentQr fragmentQr = new FragmentQr();
    private FragmentMypage fragmentMypage = new FragmentMypage();
    private FragmentMap fragmentMap = new FragmentMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentHome).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homeItem:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentHome).commit();
                        return true;
                    case R.id.qrItem:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentQr).commit();
                        return true;
                    case R.id.mypageItem:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentMypage).commit();
                        return true;
                    case R.id.mapItem:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentMap).commit();
                        return true;
                }
                return false;
            }
        });



    }
}