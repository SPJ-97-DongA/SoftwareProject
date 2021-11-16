package com.example.project.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.R;
import com.example.project.fragment.FragmentHome;
import com.example.project.fragment.FragmentMap;
import com.example.project.fragment.FragmentMypage;
import com.example.project.fragment.FragmentBoard;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends FragmentActivity {

    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentBoard fragmentBoard = new FragmentBoard();
    private FragmentMypage fragmentMypage = new FragmentMypage();
    private FragmentMap fragmentMap = new FragmentMap();

    private Intent intent;
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();


        bundle.putString("name", intent.getStringExtra("name"));
        bundle.putString("email", intent.getStringExtra("email"));
        bundle.putInt("point", intent.getIntExtra("point", 0));

        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentHome).commit();
        fragmentHome.setArguments(bundle);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homeItem:
                        return connectTofragment(fragmentHome);
                    case R.id.boardItem:
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
        fragment.setArguments(bundle);

        return true;
    }

}