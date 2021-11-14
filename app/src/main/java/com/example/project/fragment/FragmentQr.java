package com.example.project.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentQr extends Fragment {
    String point = "10";
    int sum;
    private Button qrButton;
    private TextView qrPoint;
    private IntentIntegrator qrScan;
    private Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_qr, container, false);
        qrButton = view.findViewById(R.id.qrButton);
        qrPoint = view.findViewById(R.id.qrPoint);
        qrPoint.setText(point);

        mContext = container.getContext();
        qrScan = new IntentIntegrator((Activity) mContext);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.setPrompt("Scanning...");
                qrScan.initiateScan();
            }
        });

        return view;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(mContext, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(mContext, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    sum = Integer.valueOf(obj.getString("point"))+Integer.valueOf(point);
                    point = Integer.toString(sum);
                    qrPoint.setText(point);
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}