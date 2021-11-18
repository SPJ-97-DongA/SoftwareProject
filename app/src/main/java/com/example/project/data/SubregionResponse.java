package com.example.project.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubregionResponse {

    @SerializedName("subregionList")
    private List<RegionData> subregionList;

    public List<RegionData> getSubregionList() {
        return subregionList;
    }
}
