package com.example.project.data.board;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class PostData implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String contents;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}