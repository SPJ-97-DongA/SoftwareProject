package com.example.project.response;

import com.example.project.data.RegionData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubregionResponse {

    @SerializedName("subregionList")
    private List<RegionData> subregionList;

    public List<RegionData> getSubregionList() {
        return subregionList;
    }
}
