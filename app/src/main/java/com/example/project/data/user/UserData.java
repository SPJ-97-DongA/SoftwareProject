package com.example.project.data.user;

import java.io.Serializable;

public class UserData implements Serializable {

    private String name;

    private String email;

    private int point;


    public UserData(String name, String email, int point) {
        this.name = name;
        this.email = email;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
