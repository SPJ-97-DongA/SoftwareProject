package com.example.project.data.user;

import com.google.gson.annotations.SerializedName;

public class UpdateData {

    @SerializedName("email")
    String email;

    @SerializedName("name")
    String name;

    @SerializedName("PW")
    String PW;

    @SerializedName("newPW")
    String newPW;

    public UpdateData(String email, String name, String PW, String newPW) {
        this.email = email;
        this.name = name;
        this.PW = PW;
        this.newPW = newPW;
    }
}
