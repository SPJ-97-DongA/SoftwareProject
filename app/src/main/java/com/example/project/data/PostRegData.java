package com.example.project.data;

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

    public PostRegData(String type, String title, String contents, String writer) {
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
}
