package com.example.project.response.board;

import com.example.project.data.board.PostData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListupResponse {

    @SerializedName("boardList")
    List<PostData> boardList;

    public List<PostData> getBoardList() {
        return boardList;
    }
}
