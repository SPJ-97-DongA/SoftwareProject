package com.example.project.response;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostResponse {

    @SerializedName("writer")
    String writer;

    @SerializedName("email")
    String email;

    @SerializedName("title")
    String title;

    @SerializedName("contents")
    String contents;

    @SerializedName("dateTime")
    Date dateTime;

    @SerializedName("type")
    String type;

    public String getWriter() {
        return writer;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getType() {
        return type;
    }

    public String getDateTime() {
        String result = new SimpleDateFormat("MM/dd HH:mm").format(dateTime);
        return result;
    }
}
