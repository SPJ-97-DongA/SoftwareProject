package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentBoard extends Fragment {
    private ListView dashboardListview;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment_board, container, false);
        dashboardListview = rootView.findViewById(R.id.dashboardListview);
        List<String> list = new ArrayList<>();
        list.add("경주");
        list.add("중구");
        list.add("서구");
        list.add("동구");
        list.add("영도구");
        list.add("부산진구");
        list.add("동래구");
        list.add("남구");
        list.add("북구");
        list.add("해운대구");
        list.add("사하구");
        list.add("금정구");
        list.add("강서구");
        list.add("연제구");
        list.add("수영구");
        list.add("사상구");
        list.add("기장군");

        // 지역 셋팅 리스트뷰 좌측 띄우기
        ArrayAdapter<String> adpater = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        dashboardListview.setAdapter(adpater);

        return rootView;
    }

}