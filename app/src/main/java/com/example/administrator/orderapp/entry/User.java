package com.example.administrator.orderapp.entry;

/**
 * Created by Administrator on 2016/12/25 0025.
 */

public class User {
    private String name;
    private String opaw;
    private String npaw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpaw() {
        return opaw;
    }

    public void setOpaw(String opaw) {
        this.opaw = opaw;
    }

    public String getNpaw() {
        return npaw;
    }

    public void setNpaw(String npaw) {
        this.npaw = npaw;
    }

    public User(String name, String opaw, String npaw) {
        this.name = name;
        this.opaw = opaw;
        this.npaw = npaw;
    }

    public User() {
    }
}
