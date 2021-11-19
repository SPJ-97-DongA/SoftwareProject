package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.activity.QrActivity;
import com.example.project.data.QrData;
import com.example.project.response.QrResponse;
import com.example.project.network.RetrofitClient;
import com.example.project.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {


    private Context context;

    private Bundle bundle;

    private ServiceApi service;

    private ImageButton mQrButton;

    private TextView mUserPoint;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_fragment_home, container, false);

        bundle = getArguments();

        context = container.getContext();

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mUserPoint = rootView.findViewById(R.id.userPoint);
        mUserPoint.setText(String.valueOf(bundle.getInt("point")));

        mQrButton = rootView.findViewById(R.id.qrButton);
        mQrButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), QrActivity.class);

            intent.putExtra("email", bundle.getString("email"));
            intent.putExtra("point", bundle.getInt("point"));

            startActivityForResult(intent, 0);
        });

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            QrData qrData = new QrData(data.getStringExtra("email"), data.getIntExtra("point", 0));
            infoUpdate(qrData);

            mUserPoint.setText(String.valueOf(data.getIntExtra("point", 0)));
        }
    }


    public void infoUpdate(QrData qrdata){
        service.qrSuccess(qrdata).enqueue(new Callback<QrResponse>() {
            @Override
            public void onResponse(Call<QrResponse> call, Response<QrResponse> response) {
                QrResponse result = response.body();
                bundle.putInt("point", result.getPoint());
            }

            @Override
            public void onFailure(Call<QrResponse> call, Throwable t) {
                Toast.makeText(context, "포인트 갱신 실패!", Toast.LENGTH_SHORT).show();
                Log.e("포인트 갱신 실패!", t.getMessage());
            }
        });
    }

}