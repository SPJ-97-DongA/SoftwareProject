package com.example.project.data;

import com.google.gson.annotations.SerializedName;

public class RegionDetailData {

    @SerializedName("code")
    private int code;

    @SerializedName("place_title")
    String place_title;

    @SerializedName("place_content")
    String place_content;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;



    public int getCode() {
        return code;
    }

    public String getPlace_title() {
        return place_title;
    }

    public String getPlace_content() {
        return place_content;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
