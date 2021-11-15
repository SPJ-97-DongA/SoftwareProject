package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project.R;
import com.example.project.activity.QrActivity;

public class FragmentHome extends Fragment {

    private Button mQrButton;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment_home, container, false);

        Bundle bundle = getArguments();

        mQrButton = rootView.findViewById(R.id.qrButton);
        mQrButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), QrActivity.class);

            intent.putExtra("point", bundle.getInt("point"));

            startActivity(intent);
        });

        return rootView;
    }
}