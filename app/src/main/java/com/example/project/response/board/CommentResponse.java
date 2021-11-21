package com.example.project.response.board;

import com.example.project.data.board.CommentData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {

    @SerializedName("commentList")
    List<CommentData> commentList;

    public List<CommentData> getCommentList() {
        return commentList;
    }
}
