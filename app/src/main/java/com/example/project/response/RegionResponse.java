package com.example.project.response;

import com.example.project.data.RegionData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionResponse {

    @SerializedName("regionList")
    List<RegionData> regionList;

    public List<RegionData> getRegionList() {
        return regionList;
    }
}
