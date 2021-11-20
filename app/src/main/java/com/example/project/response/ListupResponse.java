package com.example.project.response;

import com.example.project.data.PostData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListupResponse {

    @SerializedName("boardList")
    List<PostData> boardList;

    public List<PostData> getBoardList() {
        return boardList;
    }
}
