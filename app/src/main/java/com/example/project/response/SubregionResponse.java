package com.example.project.response;

import com.example.project.data.RegionData;
import com.example.project.data.RegionDetailData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubregionResponse {

    @SerializedName("subregionList")
    private List<RegionDetailData> subregionList;

    public List<RegionDetailData> getSubregionList() {
        return subregionList;
    }
}
