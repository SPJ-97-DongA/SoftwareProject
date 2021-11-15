package com.example.project.data;

import com.google.gson.annotations.SerializedName;

public class QrData {

    @SerializedName("email")
    private String email;

    @SerializedName("point")
    private int point;

    public QrData(String email, int point) {
        this.email = email;
        this.point = point;
    }
}
