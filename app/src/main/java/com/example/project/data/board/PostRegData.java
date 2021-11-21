package com.example.project.data.board;

import com.google.gson.annotations.SerializedName;

public class PostRegData {

    @SerializedName("type")
    String type;

    @SerializedName("title")
    String title;

    @SerializedName("contents")
    String contents;

    @SerializedName("writer")
    String writer;

    @SerializedName("email")
    String email;

    public PostRegData(String type, String title, String contents, String writer, String email) {
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.email = email;
    }
}
