package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activity.RegisterActivity;
import com.example.project.data.RegionData;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;
import com.example.project.response.ListupResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBoard extends Fragment {

    private ListView dashboardListview;

    private FloatingActionButton writeButton;

    private Context context;

    private ServiceApi service;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment_board, container, false);
        dashboardListview = rootView.findViewById(R.id.dashboardListview);

        context= container.getContext();
        service = RetrofitClient.getClient().create(ServiceApi.class);

        listUP();

        writeButton = rootView.findViewById(R.id.writeButton);
        writeButton.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RegisterActivity.class);
            startActivityForResult(intent, 0);
        });

        return rootView;
    }

    //지역명 리스트 호출
    public void listUP(){
        service.ListUP().enqueue(new Callback<ListupResponse>() {
            @Override
            public void onResponse(Call<ListupResponse> call, Response<ListupResponse> response) {
                ListupResponse result = response.body();
                List<RegionData> boardList = result.getBoardList();

                List<String> nameList = new ArrayList<>();
                for(RegionData item : boardList){
                    nameList.add(item.getName());
                }

                // 지역 셋팅 리스트뷰 좌측 띄우기
                ArrayAdapter<String> adpater = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, nameList);
                dashboardListview.setAdapter(adpater);

                dashboardListview.setOnClickListener(v -> {

                });
            }

            @Override
            public void onFailure(Call<ListupResponse> call, Throwable t) {
                Toast.makeText(context, "게시판 목록을 불러오는 과정에 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                Log.e("게시판 목록 에러!", t.getMessage());
            }
        });
    }

}