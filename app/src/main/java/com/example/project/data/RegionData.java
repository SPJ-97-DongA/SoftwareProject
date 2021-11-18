package com.example.project.data;

import com.google.gson.annotations.SerializedName;

public class RegionData {

    @SerializedName("code")
    private int code;

    @SerializedName("name")
    private String name;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;


    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
