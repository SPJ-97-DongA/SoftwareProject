package com.example.project.response;

import com.google.gson.annotations.SerializedName;

public class QrResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("point")
    private int point;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getPoint() {
        return point;
    }
}
