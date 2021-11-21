package com.example.project.data.board;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CommentData {

    @SerializedName("id")
    private int id;

    @SerializedName("writer")
    private String writer;

    @SerializedName("comment")
    private String comment;

    @SerializedName("createdAt")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public CommentData(int id, String writer, String comment) {
        this.id = id;
        this.writer = writer;
        this.comment = comment;
    }
}
