package com.example.project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;
import com.example.project.fragment.FragmentHome;
import com.example.project.fragment.FragmentMap;
import com.example.project.fragment.FragmentMypage;
import com.example.project.fragment.FragmentBoard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentBoard fragmentBoard = new FragmentBoard();
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
                        return connectTofragment(fragmentHome);
                    case R.id.qrItem:
                        return connectTofragment(fragmentBoard);
                    case R.id.mypageItem:
                        return connectTofragment(fragmentMypage);
                    case R.id.mapItem:
                        return connectTofragment(fragmentMap);
                }

                return false;
            }
        });
    }

    private boolean connectTofragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
        return true;
    }

}