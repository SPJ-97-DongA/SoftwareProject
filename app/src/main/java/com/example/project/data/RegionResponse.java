package com.example.project.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionResponse {

    @SerializedName("regionList")
    List<RegionData> regionList;

    public List<RegionData> getRegionList() {
        return regionList;
    }
}
