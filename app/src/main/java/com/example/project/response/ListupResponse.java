package com.example.project.response;

import com.example.project.data.RegionData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListupResponse {


    @SerializedName("boardList")
    List<RegionData> boardList;

    public List<RegionData> getBoardList() {
        return boardList;
    }
}
